import { Component, OnInit, OnDestroy, ViewChild } from '@angular/core';
import { HttpErrorResponse, HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Observable, Subscription } from 'rxjs';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';
import { AccountService } from 'app/core/auth/account.service';
import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { DebitNoteEntryService } from './debit-note-entry.service';
import { FindocumentSearch, IFindocumentSearch } from 'app/shared/db2/model/findocument-search.model';
import { IFindocument } from 'app/shared/db2/model/findocument.model';
import { CompleterCmp, CompleterItem, CompleterService, RemoteData } from 'ng2-completer';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { IViewfindocument } from 'app/shared/db2/model/viewfindocument.model';
import { IMasterParameters, MasterParameters } from 'app/shared/db2/model/master-parameters.model';
import { DebitNoteEntry, IDebitNoteEntry } from 'app/shared/db2/model/debit-note-entry.model';
import { SnotifyPosition, SnotifyService } from 'ng-snotify';
import { toastConfig } from 'app/core/toast/toast-config';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { DebitNoteItemSelectionComponent } from 'app/entities/debit-note-entry/debit-note-item-selection.component';
import { IMrnBean } from 'app/shared/model/mrn-bean.model';

@Component({
  selector: 'jhi-debit-note-entry',
  templateUrl: './debit-note-entry.component.html'
})
export class DebitNoteEntryComponent implements OnInit, OnDestroy {
  search?: IFindocumentSearch;
  findocuments: IFindocument[];
  viewfindocuments: IViewfindocument[];
  masterparameter: IMasterParameters;
  debitNoteEntries: IDebitNoteEntry[];
  currentDebitNoteEntry: IDebitNoteEntry;
  gsttypes: String;
  isSaving: boolean;
  listBoxForm: FormGroup;
  eventSubscriber?: Subscription;
  totalItems = 0;
  itemsPerPage = ITEMS_PER_PAGE;
  page!: number;
  predicate!: string;
  ascending!: boolean;
  ngbPaginationPage = 1;
  public dataRemoteFindocument: RemoteData;
  currentIndex?: number;

  editForm = this.fb.group({
    code: [],
    companycode: [],
    financialyearcode: [],
    businessunitcode: [],
    postingdate: [],
    documenttemplatecode: [],
    exchangerate: [],
    documentamount: [],
    suppliercode: [],
    gsttype: []
  });

  @ViewChild('remoteData', { static: false }) private remoteData: CompleterCmp | undefined;

  constructor(
    protected debitNoteEntryService: DebitNoteEntryService,
    protected modalService: NgbModal,
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
    this.dataRemoteFindocument = this.completerService.remote(this.debitNoteEntryService.resourceUrlFindocument, 'id.code', 'id.code');
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
    this.debitNoteEntryService
      .queryFilter(this.search)
      .subscribe((res: HttpResponse<IFindocument[]>) => this.onSuccess(res.body, res.headers, pageToLoad), () => this.onError());
  }

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(data => {
      this.page = data.pagingParams.page;
      this.ascending = data.pagingParams.ascending;
      this.predicate = data.pagingParams.predicate;
      this.ngbPaginationPage = data.pagingParams.page;
      this.loadPage();
    });
    this.masterparameter = new MasterParameters();
    this.registerChangeInfindocumentEntries();
    this.registerChangeInOrderFilter();
  }

  registerChangeInOrderFilter(): void {
    this.eventSubscriber = this.eventManager.subscribe('debitNoteMrnItemSelection', data => {
      const masterBean: IMrnBean = data.content;
      console.log(masterBean);
      if (this.currentDebitNoteEntry) {
        this.currentDebitNoteEntry.itemtype = masterBean.itemtypeaficode;
        this.currentDebitNoteEntry.description = masterBean.itemcode;
        this.currentDebitNoteEntry.hsncode = masterBean.tariffcode;
        this.currentDebitNoteEntry.uom = masterBean.uom;
      }
    });
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
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
      this.editForm.controls['code'].setValue(selected.originalObject.id.code);
      this.editForm.controls['companycode'].setValue(selected.originalObject.id.companycode);
      this.editForm.controls['financialyearcode'].setValue(selected.originalObject.id.financialyearcode);
      this.editForm.controls['businessunitcode'].setValue(selected.originalObject.id.businessunitcode);
      this.editForm.controls['postingdate'].setValue(selected.originalObject.postingdate);
      this.editForm.controls['documenttemplatecode'].setValue(selected.originalObject.id.documenttemplatecode);
      this.editForm.controls['exchangerate'].setValue(selected.originalObject.exchangerate);
      this.editForm.controls['documentamount'].setValue(selected.originalObject.documentamount);
      this.editForm.controls['suppliercode'].setValue(selected.originalObject.suppliercode);
      let i = 0;
      this.debitNoteEntryService.linedetailbyId(selected.originalObject.id).subscribe(viewfindoc => {
        this.viewfindocuments = viewfindoc.body;
        ++i;
      });

      this.masterparameter.parastring01 = this.editForm.controls['companycode'].value;
      this.masterparameter.parastring02 = this.editForm.controls['businessunitcode'].value;
      this.masterparameter.parastring03 = this.editForm.controls['financialyearcode'].value;
      this.masterparameter.parastring04 = this.editForm.controls['documenttemplatecode'].value;
      this.masterparameter.parastring05 = this.editForm.controls['code'].value;
      this.debitNoteEntryService.getgsttype(this.masterparameter).subscribe(res => {
        this.gsttypes = res.body.parastring06;
      });
      this.debitNoteEntryService.getDebitNoteDetail(this.masterparameter).subscribe(res => {
        this.debitNoteEntries = res.body;
        ++i;
        if (this.debitNoteEntries && this.debitNoteEntries.length > 0) {
        } else {
          this.loadAll();
        }
      });
    }
  }

  loadAll() {
    this.debitNoteEntries = [];
    for (let i = 0; i < 5; i++) {
      const debitNoteEntry = new DebitNoteEntry();
      this.debitNoteEntries.push(debitNoteEntry);
    }
  }

  addRow() {
    if (this.debitNoteEntries) {
      const debitNoteEntry = new DebitNoteEntry();
      debitNoteEntry.gstrate = 0;
      this.debitNoteEntries.push(debitNoteEntry);
    } else {
      this.debitNoteEntries = [];

      const debitNoteEntry = new DebitNoteEntry();
      debitNoteEntry.gstrate = 0;
      this.debitNoteEntries.push(debitNoteEntry);
    }
  }

  removeRow(index: any) {
    if (this.debitNoteEntries[index].id !== undefined) {
      this.deleteRow(this.debitNoteEntries[index].id, index);
    } else {
      this.debitNoteEntries.splice(index, 1);
    }
  }

  deleteRow(id, index) {
    this.snotifyService.confirm('Are you sure to delete row?', 'Confirm', {
      timeout: 25000,
      showProgressBar: false,
      closeOnClick: false,
      pauseOnHover: true,
      position: SnotifyPosition.centerTop,
      buttons: [
        { text: 'Yes', action: toast => this.delete(toast, id, index), bold: false },
        { text: 'No', action: toast => this.snotifyService.remove(toast.id) }
      ]
    });
  }

  delete(toast, id, index) {
    this.debitNoteEntryService.deleteDetail(id).subscribe(any => {
      this.snotifyService.remove(toast.id);
      this.debitNoteEntries.splice(index, 1);
    });
  }

  save() {
    if (this.debitNoteEntries && this.debitNoteEntries.length > 0) {
      let i = 0;
      this.debitNoteEntries.forEach(res => {
        res.companycode = this.editForm.controls['companycode'].value;
        res.businessunitcode = this.editForm.controls['businessunitcode'].value;
        res.financialyearcode = this.editForm.controls['financialyearcode'].value;
        res.documenttemplatecode = this.editForm.controls['documenttemplatecode'].value;
        res.code = this.editForm.controls['code'].value;
        ++i;
      });
      if (i === this.debitNoteEntries.length) {
        if (this.validate() === true) {
          this.isSaving = true;
          this.subscribeToSaveResponse(this.debitNoteEntryService.create(this.debitNoteEntries));
        }
      }
    }
  }

  lock() {
    if (this.debitNoteEntries && this.debitNoteEntries.length > 0) {
      let i = 0;
      this.debitNoteEntries.forEach(res => {
        res.companycode = this.editForm.controls['companycode'].value;
        res.businessunitcode = this.editForm.controls['businessunitcode'].value;
        res.financialyearcode = this.editForm.controls['financialyearcode'].value;
        res.documenttemplatecode = this.editForm.controls['documenttemplatecode'].value;
        res.code = this.editForm.controls['code'].value;
        ++i;
      });
      if (i === this.debitNoteEntries.length) {
        if (this.validate() === true) {
          this.isSaving = true;
          this.subscribeToSaveResponse(this.debitNoteEntryService.lock(this.debitNoteEntries));
        }
      }
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IDebitNoteEntry[]>>) {
    result.subscribe(data => this.onSaveSuccess(data.body), (error: HttpErrorResponse) => this.onSaveError(error));
  }

  validate(): any {
    if (this.editForm.controls['code'].value && this.editForm.controls['companycode'].value) {
      let totalValue = 0;
      let ctr = 0;
      this.debitNoteEntries.forEach((debitNoteEntry, index) => {
        if (debitNoteEntry.hsncode) {
          if (!debitNoteEntry.qty) {
            this.snotifyService.error('Quantity must be enter in line ' + (index + 1), '', toastConfig);
            return false;
          } else if (!debitNoteEntry.rate) {
            this.snotifyService.error('Rate must be enter in line ' + (index + 1), '', toastConfig);
            return false;
          } else if (debitNoteEntry.gstrate === undefined) {
            this.snotifyService.error('GST Rate must be enter in line ' + (index + 1), '', toastConfig);
            return false;
          } else {
            totalValue = totalValue + debitNoteEntry.totalValue;
            ++ctr;
          }
        } else {
          ++ctr;
        }
      });
      if (ctr === this.debitNoteEntries.length) {
        const totalValueTemp = Number(
          (Number(this.editForm.controls['documentamount'].value) * Number(this.editForm.controls['exchangerate'].value)).toFixed(2)
        );
        if (Number(totalValue.toFixed(2)) === totalValueTemp) {
          return true;
        } else if (Number(totalValue.toFixed(2)) - totalValueTemp <= 1) {
          return true;
        } else if (totalValueTemp - Number(totalValue.toFixed(2)) <= 1) {
          return true;
        } else {
          this.snotifyService.error('Breakup amount should be equals to document amount', '', toastConfig);
          return false;
        }
      }
    } else {
      this.snotifyService.error('Debit Note code must be enter', '', toastConfig);
      return false;
    }
  }

  protected onSaveSuccess(debitNoteEntries: IDebitNoteEntry[]) {
    this.debitNoteEntries = [];
    this.debitNoteEntries = debitNoteEntries;
    this.snotifyService.success('Save Successfully! ', '', toastConfig);
    this.isSaving = false;
  }

  protected onSaveError(res?: HttpErrorResponse) {
    this.isSaving = false;
    this.snotifyService.error(res.headers.get('x-vamaniportalapp-error'), '', toastConfig);
  }

  calculateTotal(debitNoteEntry: IDebitNoteEntry): void {
    if (debitNoteEntry.qty && debitNoteEntry.rate) {
      debitNoteEntry.basicvalue = Number((Number(debitNoteEntry.qty) * Number(debitNoteEntry.rate)).toFixed(2));
      if (this.gsttypes === 'CGST' && debitNoteEntry.gstrate) {
        const gstRate = Number(debitNoteEntry.gstrate) / 2;
        const value = Number(((Number(debitNoteEntry.basicvalue) * Number(gstRate)) / 100).toFixed(2));
        debitNoteEntry.cgstvalue = value;
        debitNoteEntry.sgstvalue = value;
        debitNoteEntry.igstvalue = undefined;
        debitNoteEntry.othercharges = debitNoteEntry.othercharges ? debitNoteEntry.othercharges : 0;
        debitNoteEntry.totalValue =
          debitNoteEntry.basicvalue + debitNoteEntry.cgstvalue + debitNoteEntry.sgstvalue + debitNoteEntry.othercharges;
      } else if (this.gsttypes === 'IGST' && debitNoteEntry.gstrate) {
        const gstRate = Number(debitNoteEntry.gstrate);
        const value = Number(((Number(debitNoteEntry.basicvalue) * Number(gstRate)) / 100).toFixed(2));
        debitNoteEntry.cgstvalue = undefined;
        debitNoteEntry.sgstvalue = undefined;
        debitNoteEntry.igstvalue = value;
        debitNoteEntry.othercharges = debitNoteEntry.othercharges ? debitNoteEntry.othercharges : 0;
        debitNoteEntry.totalValue = debitNoteEntry.basicvalue + debitNoteEntry.igstvalue + debitNoteEntry.othercharges;
      } else {
        debitNoteEntry.cgstvalue = undefined;
        debitNoteEntry.sgstvalue = undefined;
        debitNoteEntry.igstvalue = undefined;
        debitNoteEntry.othercharges = debitNoteEntry.othercharges ? debitNoteEntry.othercharges : 0;
        debitNoteEntry.totalValue = debitNoteEntry.basicvalue + debitNoteEntry.othercharges;
      }
    }
  }

  clear(): void {
    this.gsttypes = undefined;
    this.viewfindocuments = [];
    this.debitNoteEntries = [];
    this.editForm.controls['code'].setValue(undefined);
    this.editForm.controls['companycode'].setValue(undefined);
    this.editForm.controls['financialyearcode'].setValue(undefined);
    this.editForm.controls['businessunitcode'].setValue(undefined);
    this.editForm.controls['postingdate'].setValue(undefined);
    this.editForm.controls['documenttemplatecode'].setValue(undefined);
    this.editForm.controls['exchangerate'].setValue(undefined);
    this.editForm.controls['documentamount'].setValue(undefined);
    this.editForm.controls['suppliercode'].setValue(undefined);
    this.editForm.controls['gsttype'].setValue(undefined);
  }

  callDetails(debitNoteEntry: IDebitNoteEntry): void {
    if (this.editForm.controls['code'].value) {
      this.currentDebitNoteEntry = debitNoteEntry;
      this.masterparameter.parastring01 = this.editForm.controls['companycode'].value;
      this.masterparameter.parastring02 = this.editForm.controls['businessunitcode'].value;
      this.masterparameter.parastring03 = this.editForm.controls['financialyearcode'].value;
      this.masterparameter.parastring04 = this.editForm.controls['documenttemplatecode'].value;
      this.masterparameter.parastring05 = this.editForm.controls['code'].value;
      const modelRef = this.modalService.open(DebitNoteItemSelectionComponent, { size: 'lg', backdrop: 'static', windowClass: 'xlModal' });
      modelRef.componentInstance.masterParameters = this.masterparameter;
    }
  }
}
