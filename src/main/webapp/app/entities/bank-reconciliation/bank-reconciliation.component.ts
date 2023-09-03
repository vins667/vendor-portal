import { Component, OnInit, OnDestroy, ViewChild, ViewEncapsulation } from '@angular/core';
import { HttpErrorResponse, HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Observable, Subscription } from 'rxjs';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';
import { AccountService } from 'app/core/auth/account.service';
import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { BankReconciliationService } from './bank-reconciliation.service';
import { FindocumentSearch, IFindocumentSearch } from 'app/shared/db2/model/findocument-search.model';
import { IFindocument } from 'app/shared/db2/model/findocument.model';
import { CompleterCmp, CompleterItem, CompleterService, RemoteData } from 'ng2-completer';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { IViewfindocument } from 'app/shared/db2/model/viewfindocument.model';
import { IMasterParameters, MasterParameters } from 'app/shared/db2/model/master-parameters.model';
import { SnotifyPosition, SnotifyService } from 'ng-snotify';
import { DateTimeAdapter, OWL_DATE_TIME_FORMATS, OWL_DATE_TIME_LOCALE } from 'ng-pick-datetime';
import { MomentDateTimeAdapter } from 'ng-pick-datetime-moment';
import { MY_MOMENT_FORMATS } from 'app/directbooking/direct-booking-entry/direct-booking-entry-update.component';
import { BankReconciliation, IBankReconciliation } from 'app/shared/db2/model/bank-reconciliation.model';
import { IBankReconciliationdetail } from 'app/shared/db2/model/bank-reconciliationdetail.model';
import { IMarkerMasterEntry } from 'app/shared/model/marker-master-entry.model';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { IDirectBookingEntry } from 'app/shared/model/direct-booking-entry.model';
import { toastConfig } from 'app/core/toast/toast-config';
import { isMoment } from 'moment';

@Component({
  selector: 'jhi-bank-reconciliation',
  templateUrl: './bank-reconciliation.component.html',
  encapsulation: ViewEncapsulation.None,
  providers: [
    // `MomentDateTimeAdapter` and `OWL_MOMENT_DATE_TIME_FORMATS` can be automatically provided by importing
    // `OwlMomentDateTimeModule` in your applications root module. We provide it at the component level
    // here, due to limitations of our example generation script.
    { provide: DateTimeAdapter, useClass: MomentDateTimeAdapter, deps: [OWL_DATE_TIME_LOCALE] },
    { provide: OWL_DATE_TIME_FORMATS, useValue: MY_MOMENT_FORMATS }
  ]
})
export class BankReconciliationComponent implements OnInit, OnDestroy {
  search?: IFindocumentSearch;
  findocuments: IFindocument[];
  bankReconciliationDetails: IBankReconciliationdetail[];
  saveReconciliationDetails: IBankReconciliationdetail[];
  bankReconciliationDetailsFilter: IBankReconciliationdetail[];
  masterparameter: IMasterParameters;
  bankReconciliation: IBankReconciliation;
  gsttypes: String;
  isSaving: boolean;
  listBoxForm: FormGroup;
  eventSubscriber?: Subscription;
  totalItems = 0;
  exist?: boolean;
  itemsPerPage = ITEMS_PER_PAGE;
  page!: number;
  predicate!: string;
  ascending!: boolean;
  ngbPaginationPage = 1;
  public dataRemoteGlmaster: RemoteData;
  refNo?: string;
  sortby?: any;

  divisearch?: string;
  unitsearch?: string;
  doctypesearch?: string;
  findocsearch?: string;
  glcodesearch?: string;

  editForm = this.fb.group({
    id: [],
    code: [],
    reconcilationdatehead: [null, [Validators.required]],
    reconcilationdateline: [],
    bankcode: [null, [Validators.required]],
    bankname: [],
    postingdate: [],
    documentdate: [],
    documentno: [],
    documentbalance: [null, [Validators.required]],
    ledgerbalance: [],
    checkdepositnotclear: [],
    checkissuenotclear: [],
    bankbalance: [],
    balancedifference: [],
    unitsearch: [],
    findocsearch: [],
    glcodesearch: [],
    glnamesearch: [],
    chknosearch: [],
    chkdatesearch: [],
    reconsdatesearch: [],
    profitcentsearch: []
  });

  @ViewChild('remoteData', { static: false }) private remoteData: CompleterCmp | undefined;

  constructor(
    protected bankReconciliationService: BankReconciliationService,
    protected parseLinks: JhiParseLinks,
    protected jhiAlertService: JhiAlertService,
    protected accountService: AccountService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder,
    public completerService: CompleterService,
    protected router: Router,
    protected eventManager: JhiEventManager,
    protected snotifyService: SnotifyService
  ) {
    this.search = new FindocumentSearch();
    this.dataRemoteGlmaster = this.completerService.remote(
      this.bankReconciliationService.resourceUrlglcode,
      'longdescription',
      'longdescription'
    );
  }

  loadPage(page?: number): void {
    const pageToLoad: number = page || this.page;

    this.search.size = this.itemsPerPage;
    this.search.pageNo = pageToLoad - 1;
    const sort = this.sort();
    if (sort && sort.length > 0) {
      this.search.sort = sort[0].split(',')[0];
      this.search.sortType = sort[0].split(',')[1];
    } else {
      this.search.sort = 'style';
      this.search.sortType = 'asc';
    }
    // this.debitNoteEntryService.queryFilter(this.search).subscribe((res: HttpResponse<IFindocument[]>) => this.onSuccess(res.body, res.headers, pageToLoad), () => this.onError());
  }

  ngOnInit(): void {
    this.sortby = '4';
    this.activatedRoute.data.subscribe(bankReconciliation => {
      this.bankReconciliation = bankReconciliation;
      this.page = bankReconciliation.pagingParams.page;
      this.ascending = bankReconciliation.pagingParams.ascending;
      this.predicate = bankReconciliation.pagingParams.predicate;
      this.ngbPaginationPage = bankReconciliation.pagingParams.page;
      this.loadPage();
    });
    this.masterparameter = new MasterParameters();
    this.bankReconciliationDetails = [];
    this.registerChangeInfindocumentEntries();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  private createFromForm(): IBankReconciliation {
    return {
      ...new BankReconciliation(),
      id: this.editForm.get(['id']).value,
      reconciliationdate: this.editForm.get(['reconcilationdatehead'])!.value
        ? moment(this.editForm.get(['reconcilationdatehead'])!.value, DATE_TIME_FORMAT)
        : undefined,
      bankcode: this.editForm.get(['bankcode']).value,
      documentno: this.editForm.get(['documentno']).value,
      documentdate: this.editForm.get(['documentdate'])!.value
        ? moment(this.editForm.get(['documentdate'])!.value, DATE_TIME_FORMAT)
        : undefined,
      balance: this.editForm.get(['documentbalance']).value,
      ledgerbalance: this.editForm.get(['ledgerbalance']).value,
      checkdepositnotclear: this.editForm.get(['checkdepositnotclear']).value,
      checkissuenotclear: this.editForm.get(['checkissuenotclear']).value,
      bankbalance: this.editForm.get(['bankbalance']).value,
      balancedifference: this.editForm.get(['balancedifference']).value,
      bankReconciliationdetails: this.bankReconciliationDetails
    };
  }

  trackId(index: number, item: IFindocument): any {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id.code!;
  }

  registerChangeInfindocumentEntries() {
    this.eventSubscriber = this.eventManager.subscribe('findocumentModification', response => this.loadPage());
  }

  sort(): string[] {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'style') {
      result.push('style');
    }
    return result;
  }

  protected onSuccess(data: IFindocument[] | null, headers: HttpHeaders, page: number): void {
    this.totalItems = Number(headers.get('X-Total-Count'));
    this.page = page;
    this.findocuments = data;
  }

  protected onError(): void {
    this.ngbPaginationPage = this.page;
  }

  onProjectSelected(selected?: CompleterItem): void {
    if (selected && !!this.remoteData) {
      this.editForm.controls['bankcode'].setValue(selected.originalObject.id.code);
      this.editForm.controls['bankname'].setValue(selected.originalObject.longdescription);
    }
  }

  getDetailList(): void {
    this.saveReconciliationDetails = [];
    this.bankReconciliationDetails = [];
    this.bankReconciliationDetailsFilter = [];
    this.isSaving = true;
    this.masterparameter.parastring01 = this.editForm.controls['bankcode'].value;
    this.masterparameter.parastring02 = this.editForm.controls['reconcilationdatehead'].value;
    this.masterparameter.parastring03 = this.sortby;
    this.bankReconciliationService.getbankReconcileDetailList(this.masterparameter).subscribe(res => {
      this.isSaving = false;
      this.bankReconciliationDetails = res.body;
      let ledgerbalance = 0;
      let checkdepositnotclear = 0;
      let checkissuenotclear = 0;
      this.bankReconciliationDetailsFilter = [];
      let ctr = 0;
      this.bankReconciliationDetails.forEach(bankReconciliationDetail => {
        const bankReconciliationDetailTemp = Object.assign({}, bankReconciliationDetail);
        this.bankReconciliationDetailsFilter.push(bankReconciliationDetailTemp);
        ledgerbalance += bankReconciliationDetailTemp.debitamit - bankReconciliationDetailTemp.creditamt;
        checkdepositnotclear += bankReconciliationDetailTemp.debitamit;
        checkissuenotclear += bankReconciliationDetailTemp.creditamt;
        ++ctr;
      });
      if (ctr === this.bankReconciliationDetails.length) {
        this.editForm.controls['ledgerbalance'].setValue(ledgerbalance.toFixed(2));
        this.editForm.controls['checkdepositnotclear'].setValue(checkdepositnotclear.toFixed(2));
        this.editForm.controls['checkissuenotclear'].setValue(checkissuenotclear.toFixed(2));

        const bankbalance = ledgerbalance - checkdepositnotclear + checkissuenotclear;
        this.editForm.controls['bankbalance'].setValue(bankbalance.toFixed(2));

        const difference = Number(this.editForm.controls['documentbalance'].value) - bankbalance;
        this.editForm.controls['balancedifference'].setValue(difference.toFixed(2));
      }
    });
  }

  showHide(bankReconciliationDetil: IBankReconciliationdetail, value: boolean): void {
    bankReconciliationDetil.exist = value;
  }

  onchagetotalBalace(): void {
    if (this.editForm.controls['documentbalance'].value > 0) {
      const balA = this.editForm.controls['documentbalance'].value;
      const balE = this.editForm.controls['bankbalance'].value;
      const difference = balA - balE;
      this.editForm.controls['balancedifference'].setValue(difference.toFixed(2));
    }
  }

  searchBy(): void {
    if (this.editForm.controls['bankcode'].value && this.editForm.controls['reconcilationdatehead'].value) {
      this.isSaving = true;
      this.masterparameter.parastring01 = this.editForm.controls['unitsearch'].value;
      this.masterparameter.parastring02 = this.editForm.controls['findocsearch'].value;
      this.masterparameter.parastring03 = this.editForm.controls['glcodesearch'].value;
      this.masterparameter.parastring04 = this.editForm.controls['glnamesearch'].value;
      this.masterparameter.parastring05 = this.editForm.controls['chknosearch'].value;
      this.masterparameter.parastring06 = this.editForm.controls['chkdatesearch'].value;
      this.masterparameter.parastring08 = this.editForm.controls['profitcentsearch'].value;
      this.masterparameter.parastring09 = this.editForm.controls['bankcode'].value;
      this.masterparameter.parastring10 = this.editForm.controls['reconcilationdatehead'].value;
      this.bankReconciliationService.searchByparameters(this.masterparameter).subscribe(res => {
        this.isSaving = false;
        this.bankReconciliationDetails = res.body;
        let checkdepoit = 0;
        let checkissue = 0;
        let closingBalance = 0;
        let bbalance = 0;
        let difference = 0;
        this.bankReconciliationDetails.forEach(list => {
          checkdepoit += list.checkdepositnotclear;
          checkissue += list.checkissuenotclear;
          if (!list.reconciliationdate) {
            closingBalance += list.debitamit;
          }
        });
        this.editForm.controls['checkdepositnotclear'].setValue(checkdepoit.toFixed(2));
        this.editForm.controls['checkissuenotclear'].setValue(checkissue.toFixed(2));
        this.editForm.controls['ledgerbalance'].setValue(closingBalance.toFixed(2));
        bbalance = closingBalance - checkdepoit + checkissue;
        this.editForm.controls['bankbalance'].setValue(bbalance.toFixed(2));
        if (this.editForm.controls['ledgerbalance'].value > 0) {
          const bal = this.editForm.controls['ledgerbalance'].value;
          const docbal = this.editForm.controls['documentbalance'].value;
          difference = docbal - bbalance;
          this.editForm.controls['balancedifference'].setValue(difference.toFixed(2));
        }
      });
    }
  }

  save(): void {
    this.snotifyService.confirm('Are you sure to save Reference Details?', 'Confirm', {
      timeout: 25000,
      showProgressBar: false,
      closeOnClick: false,
      pauseOnHover: true,
      position: SnotifyPosition.centerTop,
      buttons: [
        { text: 'Yes', action: toast => this.saveToast(toast), bold: false },
        { text: 'No', action: toast => this.snotifyService.remove(toast.id) }
      ]
    });
  }

  saveToast(toast): void {
    this.snotifyService.remove(toast.id);
    this.isSaving = true;
    const bankReconciliation = this.createFromForm();
    this.bankReconciliationService.create(bankReconciliation).subscribe(bankReconciliationTemp => {
      this.isSaving = false;
      this.snotifyService.success('Save Successfully Reference#' + bankReconciliationTemp.body.id + '', '', toastConfig);
    });
    this.bankReconciliationService.post(this.saveReconciliationDetails).subscribe(saveReconciliationDetails => {
      this.isSaving = false;
      this.snotifyService.success('Post Successfully!', '', toastConfig);
    });
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<BankReconciliation>>): void {
    result.subscribe(() => this.onSaveSuccess(), (httpErrorResponse: HttpErrorResponse) => this.onSaveError(httpErrorResponse));
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    // this.previousState();
  }

  protected onSaveError(httpErrorResponse: HttpErrorResponse): void {
    this.snotifyService.error(httpErrorResponse.headers.get('x-vamaniportalapp-error'), '', toastConfig);
    this.isSaving = false;
  }

  filterSearch(): void {
    //
  }

  filterDiviSearch(): void {
    if (this.bankReconciliationDetails && this.bankReconciliationDetails.length > 0) {
      this.bankReconciliationDetailsFilter = [];
      this.bankReconciliationDetails.forEach(bankReconciliationDetail => {
        if (this.divisearch && this.divisearch.length > 0) {
          if (bankReconciliationDetail.companycode.includes(this.divisearch)) {
            const bankReconciliationDetailTemp = Object.assign({}, bankReconciliationDetail);
            this.bankReconciliationDetailsFilter.push(bankReconciliationDetailTemp);
          }
        } else {
          this.defaultConfig();
        }
      });
    }
  }

  filterUnitSearch(): void {
    if (this.bankReconciliationDetails && this.bankReconciliationDetails.length > 0) {
      this.bankReconciliationDetailsFilter = [];
      this.bankReconciliationDetails.forEach(bankReconciliationDetail => {
        if (this.unitsearch && this.unitsearch.length > 0) {
          if (bankReconciliationDetail.businessunitcode.includes(this.unitsearch.toUpperCase())) {
            const bankReconciliationDetailTemp = Object.assign({}, bankReconciliationDetail);
            this.bankReconciliationDetailsFilter.push(bankReconciliationDetailTemp);
          }
        } else {
          this.defaultConfig();
        }
      });
    }
  }

  filterDocTypeSearch(): void {
    if (this.bankReconciliationDetails && this.bankReconciliationDetails.length > 0) {
      this.bankReconciliationDetailsFilter = [];
      this.bankReconciliationDetails.forEach(bankReconciliationDetail => {
        if (this.doctypesearch && this.doctypesearch.length > 0) {
          if (bankReconciliationDetail.documentype.includes(this.doctypesearch.toUpperCase())) {
            const bankReconciliationDetailTemp = Object.assign({}, bankReconciliationDetail);
            this.bankReconciliationDetailsFilter.push(bankReconciliationDetailTemp);
          }
        } else {
          this.defaultConfig();
        }
      });
    }
  }

  filterFindocSearch(): void {
    if (this.bankReconciliationDetails && this.bankReconciliationDetails.length > 0) {
      this.bankReconciliationDetailsFilter = [];
      this.bankReconciliationDetails.forEach(bankReconciliationDetail => {
        if (this.findocsearch && this.findocsearch.length > 0) {
          if (bankReconciliationDetail.code.includes(this.findocsearch.toUpperCase())) {
            const bankReconciliationDetailTemp = Object.assign({}, bankReconciliationDetail);
            this.bankReconciliationDetailsFilter.push(bankReconciliationDetailTemp);
          }
        } else {
          this.defaultConfig();
        }
      });
    }
  }

  filterGlcodeSearch(): void {
    if (this.bankReconciliationDetails && this.bankReconciliationDetails.length > 0) {
      this.bankReconciliationDetailsFilter = [];
      this.bankReconciliationDetails.forEach(bankReconciliationDetail => {
        if (this.glcodesearch && this.glcodesearch.length > 0) {
          if (bankReconciliationDetail.glcode.includes(this.glcodesearch.toUpperCase())) {
            const bankReconciliationDetailTemp = Object.assign({}, bankReconciliationDetail);
            this.bankReconciliationDetailsFilter.push(bankReconciliationDetailTemp);
          }
        } else {
          this.defaultConfig();
        }
      });
    }
  }

  reconcileDetails(bankReconciliationDetail: IBankReconciliationdetail): void {
    if (this.saveReconciliationDetails && this.saveReconciliationDetails.length > 0) {
      if (bankReconciliationDetail.reconciliationdate) {
        let exist = false;
        let index = 0;
        this.saveReconciliationDetails.forEach(bankReconciliationDetailTemp => {
          if (bankReconciliationDetail.code === bankReconciliationDetailTemp.code) {
            exist = true;
            bankReconciliationDetailTemp.reconciliationdate = bankReconciliationDetail.reconciliationdate;
          }
          ++index;
        });
        if (this.saveReconciliationDetails.length === index && exist === false) {
          this.saveReconciliationDetails.push(bankReconciliationDetail);
        }
      } else {
        let exist = false;
        let index = 0;
        let dindex = -1;
        this.saveReconciliationDetails.forEach(bankReconciliationDetailTemp => {
          if (bankReconciliationDetail.code === bankReconciliationDetailTemp.code) {
            exist = true;
            dindex = index;
          }
          ++index;
        });
        if (this.saveReconciliationDetails.length === index && dindex !== -1) {
          this.saveReconciliationDetails.splice(dindex, 1);
        }
      }
    } else {
      this.saveReconciliationDetails = [];
      console.log(bankReconciliationDetail);
      if (bankReconciliationDetail.reconciliationdate) {
        this.saveReconciliationDetails.push(bankReconciliationDetail);
      }
    }
  }

  postReconciliation(): void {
    this.snotifyService.confirm('Are you sure to post Reconcilation Date?', 'Confirm', {
      timeout: 25000,
      showProgressBar: false,
      closeOnClick: false,
      pauseOnHover: true,
      position: SnotifyPosition.centerTop,
      buttons: [
        { text: 'Yes', action: toast => this.reconcile(toast), bold: false },
        { text: 'No', action: toast => this.snotifyService.remove(toast.id) }
      ]
    });
  }

  reconcile(toast): void {
    this.snotifyService.remove(toast.id);
    this.isSaving = true;
    this.bankReconciliationService.post(this.saveReconciliationDetails).subscribe(saveReconciliationDetails => {
      this.isSaving = false;
      this.snotifyService.success('Post Successfully!', '', toastConfig);
    });
  }

  clearscreen(): void {
    this.editForm.controls['id'].setValue(undefined);
    this.editForm.controls['code'].setValue(undefined);
    this.editForm.controls['reconcilationdatehead'].setValue(undefined);
    this.editForm.controls['reconcilationdateline'].setValue(undefined);
    this.editForm.controls['bankcode'].setValue(undefined);
    this.editForm.controls['bankname'].setValue(undefined);
    this.editForm.controls['postingdate'].setValue(undefined);
    this.editForm.controls['documentdate'].setValue(undefined);
    this.editForm.controls['documentno'].setValue(undefined);
    this.editForm.controls['documentbalance'].setValue(undefined);
    this.editForm.controls['ledgerbalance'].setValue(undefined);
    this.editForm.controls['checkdepositnotclear'].setValue(undefined);
    this.editForm.controls['checkissuenotclear'].setValue(undefined);
    this.editForm.controls['bankbalance'].setValue(undefined);
    this.editForm.controls['balancedifference'].setValue(undefined);
    this.editForm.controls['unitsearch'].setValue(undefined);
    this.editForm.controls['findocsearch'].setValue(undefined);
    this.editForm.controls['glcodesearch'].setValue(undefined);
    this.editForm.controls['glnamesearch'].setValue(undefined);
    this.editForm.controls['chknosearch'].setValue(undefined);
    this.editForm.controls['chkdatesearch'].setValue(undefined);
    this.editForm.controls['reconsdatesearch'].setValue(undefined);
    this.editForm.controls['profitcentsearch'].setValue(undefined);
    this.saveReconciliationDetails = [];
    this.bankReconciliationDetails = [];
    this.bankReconciliationDetailsFilter = [];
    this.sortby = '4';
  }

  arrangeDate(): void {
    if (this.editForm.controls['reconcilationdatehead'].value) {
      if (isMoment(this.editForm.controls['reconcilationdatehead'].value)) {
        this.editForm.controls['reconcilationdatehead'].setValue(moment(this.editForm.controls['reconcilationdatehead'].value)); //this.editForm.controls['reconcilationdatehead'].value.format("DD-MM-YYYY")
      }
    }
  }

  defaultConfig(): void {
    let ledgerbalance = 0;
    let checkdepositnotclear = 0;
    let checkissuenotclear = 0;
    this.bankReconciliationDetailsFilter = [];
    let ctr = 0;
    this.bankReconciliationDetails.forEach(bankReconciliationDetail => {
      const bankReconciliationDetailTemp = Object.assign({}, bankReconciliationDetail);
      this.bankReconciliationDetailsFilter.push(bankReconciliationDetailTemp);
      ledgerbalance += bankReconciliationDetailTemp.debitamit - bankReconciliationDetailTemp.creditamt;
      checkdepositnotclear += bankReconciliationDetailTemp.debitamit;
      checkissuenotclear += bankReconciliationDetailTemp.creditamt;
      ++ctr;
    });
    if (ctr === this.bankReconciliationDetails.length) {
      this.editForm.controls['ledgerbalance'].setValue(ledgerbalance.toFixed(2));
      this.editForm.controls['checkdepositnotclear'].setValue(checkdepositnotclear.toFixed(2));
      this.editForm.controls['checkissuenotclear'].setValue(checkissuenotclear.toFixed(2));

      const bankbalance = ledgerbalance - checkdepositnotclear + checkissuenotclear;
      this.editForm.controls['bankbalance'].setValue(bankbalance.toFixed(2));

      const difference = Number(this.editForm.controls['documentbalance'].value) - bankbalance;
      this.editForm.controls['balancedifference'].setValue(difference.toFixed(2));
    }
  }
}
