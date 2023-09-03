import { Component, OnDestroy, OnInit, ViewChild } from '@angular/core';
import { FindocumentSearch, IFindocumentSearch } from '../../shared/db2/model/findocument-search.model';
import { IFindocument } from '../../shared/db2/model/findocument.model';
import { IViewfindocument } from '../../shared/db2/model/viewfindocument.model';
import { IMasterParameters, MasterParameters } from '../../shared/db2/model/master-parameters.model';
import { DebitNoteEntry, IDebitNoteEntry } from '../../shared/db2/model/debit-note-entry.model';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Observable, Subscription } from 'rxjs';
import { ITEMS_PER_PAGE } from '../../shared/constants/pagination.constants';
import { CompleterCmp, CompleterItem, CompleterService, RemoteData } from 'ng2-completer';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiAlertService, JhiEventManager, JhiParseLinks } from 'ng-jhipster';
import { AccountService } from 'app/core/auth/account.service';
import { ActivatedRoute, Router } from '@angular/router';
import { SnotifyPosition, SnotifyService } from 'ng-snotify';
import { FinDocumentGlChangeService } from 'app/entities/fin-document-gl-change/fin-document-gl-change.service';
import { HttpErrorResponse, HttpHeaders, HttpResponse } from '@angular/common/http';
import { IMrnBean } from 'app/shared/model/mrn-bean.model';
import { FinDocumentGlChange, IFinDocumentGlChange } from './fin-document-gl-change.model';
import { toastConfig } from 'app/core/toast/toast-config';
import { DebitNoteItemSelectionComponent } from 'app/entities/debit-note-entry/debit-note-item-selection.component';

@Component({
  selector: 'jhi-fin-document-gl-change',
  templateUrl: './fin-document-gl-change.component.html'
})
export class FinDocumentGlChangeComponent implements OnInit, OnDestroy {
  search?: IFindocumentSearch;
  findocuments: IFindocument[];
  viewfindocuments: IViewfindocument[];
  masterparameter: IMasterParameters;
  finDocumentGlChanges: IFinDocumentGlChange[];
  currentfinDocumentGlChange: IFinDocumentGlChange;
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
  public dataRemoteGlCode: RemoteData;
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
    protected finDocumentGlChangeService: FinDocumentGlChangeService,
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
    this.dataRemoteFindocument = this.completerService.remote(
      this.finDocumentGlChangeService.resourceUrlFindocumentAll,
      'id.code',
      'id.code'
    );
    this.dataRemoteGlCode = this.completerService.remote(this.finDocumentGlChangeService.resourceUrlGlMasterAll, 'id.code', 'id.code');
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
    this.finDocumentGlChangeService
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
      if (this.currentfinDocumentGlChange) {
        this.currentfinDocumentGlChange.itemtype = masterBean.itemtypeaficode;
        this.currentfinDocumentGlChange.description = masterBean.itemcode;
        this.currentfinDocumentGlChange.hsncode = masterBean.tariffcode;
        this.currentfinDocumentGlChange.uom = masterBean.uom;
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
      this.finDocumentGlChangeService.linedetailbyId(selected.originalObject.id).subscribe(viewfindoc => {
        this.viewfindocuments = viewfindoc.body;
        ++i;
      });
    }
  }

  onGLMasterSelected(selected?: CompleterItem, viewfindocument?: IViewfindocument): void {
    if (selected && !!this.remoteData) {
      viewfindocument.gldescription = selected.originalObject.longdescription;
    } else {
      viewfindocument.gldescription = undefined;
    }
  }

  loadAll() {
    this.finDocumentGlChanges = [];
    for (let i = 0; i < 5; i++) {
      const debitNoteEntry = new DebitNoteEntry();
      this.finDocumentGlChanges.push(debitNoteEntry);
    }
  }

  addRow() {
    if (this.finDocumentGlChanges) {
      const debitNoteEntry = new FinDocumentGlChange();
      debitNoteEntry.gstrate = 0;
      this.finDocumentGlChanges.push(debitNoteEntry);
    } else {
      this.finDocumentGlChanges = [];

      const debitNoteEntry = new DebitNoteEntry();
      debitNoteEntry.gstrate = 0;
      this.finDocumentGlChanges.push(debitNoteEntry);
    }
  }

  removeRow(index: any) {
    if (this.finDocumentGlChanges[index].id !== undefined) {
      this.deleteRow(this.finDocumentGlChanges[index].id, index);
    } else {
      this.finDocumentGlChanges.splice(index, 1);
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
    this.finDocumentGlChangeService.deleteDetail(id).subscribe(any => {
      this.snotifyService.remove(toast.id);
      this.finDocumentGlChanges.splice(index, 1);
    });
  }

  save() {
    if (this.viewfindocuments && this.viewfindocuments.length > 0) {
      if (this.validate() === true) {
        this.snotifyService.confirm('Are you sure to post in now?', 'Confirm', {
          timeout: 25000,
          showProgressBar: false,
          closeOnClick: false,
          pauseOnHover: true,
          position: SnotifyPosition.centerTop,
          buttons: [
            { text: 'Yes', action: toast => this.post(toast), bold: false },
            { text: 'No', action: toast => this.snotifyService.remove(toast.id) }
          ]
        });
      }
    }
  }

  post(toast: any): void {
    this.isSaving = true;
    this.snotifyService.remove(toast.id);
    this.subscribeToSaveResponse(this.finDocumentGlChangeService.create(this.viewfindocuments));
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IFinDocumentGlChange[]>>) {
    result.subscribe(data => this.onSaveSuccess(data.body), (error: HttpErrorResponse) => this.onSaveError(error));
  }

  validate(): any {
    if (this.editForm.controls['code'].value && this.editForm.controls['companycode'].value) {
      let ctr = 0;
      let error = 0;
      this.viewfindocuments.forEach(viewfindocument => {
        if (!viewfindocument.glcode || !viewfindocument.gldescription) {
          this.snotifyService.error('Gl Code must be enter in line ' + viewfindocument.id.linenumber, '', toastConfig);
          ++error;
          return false;
        } else if (
          ctr === this.viewfindocuments.length - 1 &&
          (!viewfindocument.narration || viewfindocument.narration.trim().length === 0)
        ) {
          this.snotifyService.error('Narration must not be empty!', '', toastConfig);
          ++error;
          return false;
        }
        ++ctr;
      });
      if (ctr === this.viewfindocuments.length && error === 0) {
        return true;
      } else if (ctr === this.viewfindocuments.length) {
        return false;
      }
    }
  }

  protected onSaveSuccess(finDocumentGlChanges: IFinDocumentGlChange[]) {
    this.finDocumentGlChanges = [];
    this.finDocumentGlChanges = finDocumentGlChanges;
    this.snotifyService.success('Save Successfully! ', '', toastConfig);
    this.isSaving = false;
  }

  protected onSaveError(res?: HttpErrorResponse) {
    this.isSaving = false;
    this.snotifyService.error(res.headers.get('x-vamaniportalapp-error'), '', toastConfig);
  }

  calculateTotal(finDocumentGlChange: IFinDocumentGlChange): void {
    if (finDocumentGlChange.qty && finDocumentGlChange.rate) {
      finDocumentGlChange.basicvalue = Number((Number(finDocumentGlChange.qty) * Number(finDocumentGlChange.rate)).toFixed(2));
      if (this.gsttypes === 'CGST' && finDocumentGlChange.gstrate) {
        const gstRate = Number(finDocumentGlChange.gstrate) / 2;
        const value = Number(((Number(finDocumentGlChange.basicvalue) * Number(gstRate)) / 100).toFixed(2));
        finDocumentGlChange.cgstvalue = value;
        finDocumentGlChange.sgstvalue = value;
        finDocumentGlChange.igstvalue = undefined;
        finDocumentGlChange.othercharges = finDocumentGlChange.othercharges ? finDocumentGlChange.othercharges : 0;
        finDocumentGlChange.totalValue =
          finDocumentGlChange.basicvalue + finDocumentGlChange.cgstvalue + finDocumentGlChange.sgstvalue + finDocumentGlChange.othercharges;
      } else if (this.gsttypes === 'IGST' && finDocumentGlChange.gstrate) {
        const gstRate = Number(finDocumentGlChange.gstrate);
        const value = Number(((Number(finDocumentGlChange.basicvalue) * Number(gstRate)) / 100).toFixed(2));
        finDocumentGlChange.cgstvalue = undefined;
        finDocumentGlChange.sgstvalue = undefined;
        finDocumentGlChange.igstvalue = value;
        finDocumentGlChange.othercharges = finDocumentGlChange.othercharges ? finDocumentGlChange.othercharges : 0;
        finDocumentGlChange.totalValue = finDocumentGlChange.basicvalue + finDocumentGlChange.igstvalue + finDocumentGlChange.othercharges;
      } else {
        finDocumentGlChange.cgstvalue = undefined;
        finDocumentGlChange.sgstvalue = undefined;
        finDocumentGlChange.igstvalue = undefined;
        finDocumentGlChange.othercharges = finDocumentGlChange.othercharges ? finDocumentGlChange.othercharges : 0;
        finDocumentGlChange.totalValue = finDocumentGlChange.basicvalue + finDocumentGlChange.othercharges;
      }
    }
  }

  clear(): void {
    this.gsttypes = undefined;
    this.viewfindocuments = [];
    this.finDocumentGlChanges = [];
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

  callDetails(finDocumentGlChange: IFinDocumentGlChange): void {
    if (this.editForm.controls['code'].value) {
      this.currentfinDocumentGlChange = finDocumentGlChange;
      this.masterparameter.parastring01 = this.editForm.controls['companycode'].value;
      this.masterparameter.parastring02 = this.editForm.controls['businessunitcode'].value;
      this.masterparameter.parastring03 = this.editForm.controls['financialyearcode'].value;
      this.masterparameter.parastring04 = this.editForm.controls['documenttemplatecode'].value;
      this.masterparameter.parastring05 = this.editForm.controls['code'].value;
      const modelRef = this.modalService.open(DebitNoteItemSelectionComponent, {
        size: 'lg',
        backdrop: 'static',
        windowClass: 'xlModal'
      });
      modelRef.componentInstance.masterParameters = this.masterparameter;
    }
  }
}
