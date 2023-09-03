import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';
import { ITdsDeclaration } from 'app/shared/model/tds-declaration.model';
import { AccountService } from 'app/core/auth/account.service';
import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { TdsQueryService } from './tds-query.service';
import { ITdsDeclarationSearch, TdsDeclarationSearch } from 'app/shared/model/tds-declaration-search.model';
import { IEmployeeView } from 'app/shared/model/employee-view.model';
import { ITdsYearMaster } from 'app/shared/model/tds-year-master.model';
import { TdsYearMasterService } from 'app/entities/tds-year-master';
import { SnotifyPosition, SnotifyService } from 'ng-snotify';
import { toastConfig } from 'app/core/toast/toast-config';
import * as FileSaver from 'file-saver';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { TdsDeclarationUploadQryUpdateComponent } from './tds-declaration-upload-qry-update.component';
import { TdsDeclarationUploadSearch } from 'app/shared/model/tds-declaration-upload-search.model';

@Component({
  selector: 'jhi-tds-query',
  templateUrl: './tds-query.component.html'
})
export class TdsQueryComponent implements OnInit, OnDestroy {
  currentAccount: any;
  tdsDeclarations: IEmployeeView[];
  tdsDeclarationSearch: ITdsDeclarationSearch;
  tdsyearmasters: ITdsYearMaster[];
  error: any;
  success: any;
  eventSubscriber: Subscription;
  routeData: any;
  links: any;
  totalItems: any;
  queryCount: any;
  itemsPerPage: any;
  page: any;
  predicate: any;
  previousPage: any;
  reverse: any;
  currentYear: ITdsYearMaster;
  isDownload = false;
  protected ngbModalRef: NgbModalRef;

  constructor(
    protected tdsDeclarationService: TdsQueryService,
    protected parseLinks: JhiParseLinks,
    protected jhiAlertService: JhiAlertService,
    protected accountService: AccountService,
    protected activatedRoute: ActivatedRoute,
    protected router: Router,
    protected eventManager: JhiEventManager,
    protected snotifyService: SnotifyService,
    protected tdsYearMasterService: TdsYearMasterService,
    protected modalService: NgbModal
  ) {
    this.itemsPerPage = ITEMS_PER_PAGE;
    this.routeData = this.activatedRoute.data.subscribe(data => {
      this.page = data.pagingParams.page;
      this.previousPage = data.pagingParams.page;
      this.reverse = data.pagingParams.ascending;
      this.predicate = data.pagingParams.predicate;
    });
  }

  activeYear() {
    this.tdsyearmasters.forEach(years => {
      if (this.tdsDeclarationSearch.year === years.code) {
        this.currentYear = years;
      }
    });
  }

  loadAll() {
    this.tdsYearMasterService.query().subscribe(
      (res: HttpResponse<ITdsYearMaster[]>) => {
        this.tdsyearmasters = res.body;
        this.tdsDeclarationSearch.status = 'F';
        if (this.tdsyearmasters && this.tdsyearmasters.length > 0) {
          this.tdsDeclarationSearch.year = this.tdsyearmasters[0].code;
          this.currentYear = this.tdsyearmasters[0];
          this.search();
        }
      },
      (res: HttpErrorResponse) => this.onError(res.message)
    );
  }
  loadPage(page: number) {
    if (page !== this.previousPage) {
      this.previousPage = page;
      this.transition();
    }
  }

  search() {
    this.tdsDeclarationSearch.size = ITEMS_PER_PAGE;
    this.tdsDeclarationSearch.pageNo = 0;
    this.page = 0;
    this.tdsDeclarationService
      .query(this.tdsDeclarationSearch)
      .subscribe(
        (res: HttpResponse<IEmployeeView[]>) => this.paginateTdsDeclarations(res.body, res.headers),
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  generateReport() {
    this.isDownload = true;
    this.tdsDeclarationService.downloadPdf(this.tdsDeclarationSearch).subscribe(
      res => {
        FileSaver.saveAs(res, 'TDSReports.pdf');
        this.isDownload = false;
      },
      res => {
        this.isDownload = false;
      }
    );
  }

  generateXLS() {
    this.isDownload = true;
    this.tdsDeclarationService.downloadXlsx(this.tdsDeclarationSearch).subscribe(
      res => {
        FileSaver.saveAs(res, 'TDSReports.xlsx');
        this.isDownload = false;
      },
      res => {
        this.isDownload = false;
      }
    );
  }

  loadAllNew() {
    this.tdsDeclarationSearch.size = this.itemsPerPage;
    this.tdsDeclarationSearch.pageNo = this.page - 1;
    this.tdsDeclarationService
      .query(this.tdsDeclarationSearch)
      .subscribe(
        (res: HttpResponse<IEmployeeView[]>) => this.paginateTdsDeclarations(res.body, res.headers),
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }
  transition() {
    this.router.navigate(['/tds-query'], {
      queryParams: {
        page: this.page,
        size: this.itemsPerPage,
        sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
      }
    });
    this.loadAllNew();
  }

  lockAll() {
    this.snotifyService.confirm('Are you sure to Lock All?', 'Confirm', {
      timeout: 25000,
      showProgressBar: false,
      closeOnClick: false,
      pauseOnHover: true,
      position: SnotifyPosition.centerTop,
      buttons: [
        { text: 'Yes', action: toast => this.lock(toast), bold: false },
        { text: 'No', action: toast => this.snotifyService.remove(toast.id) }
      ]
    });
  }

  lock(toast) {
    this.tdsDeclarationService.lockAll(this.currentYear.code).subscribe(response => {
      this.snotifyService.remove(toast.id);
      this.search();
      this.snotifyService.success('Lock All successfully!', '', toastConfig);
    });
  }

  unlockAll() {
    this.snotifyService.confirm('Are you sure to Unlock All?', 'Confirm', {
      timeout: 25000,
      showProgressBar: false,
      closeOnClick: false,
      pauseOnHover: true,
      position: SnotifyPosition.centerTop,
      buttons: [
        { text: 'Yes', action: toast => this.unlock(toast), bold: false },
        { text: 'No', action: toast => this.snotifyService.remove(toast.id) }
      ]
    });
  }

  unlock(toast) {
    this.tdsDeclarationService.unlockAll(this.currentYear.code).subscribe(response => {
      this.snotifyService.remove(toast.id);
      this.search();
      this.snotifyService.success('Unlock All successfully!', '', toastConfig);
    });
  }

  lockSingle(employeeView: IEmployeeView) {
    this.snotifyService.confirm('Are you sure to lock ' + employeeView.name + '?', 'Confirm', {
      timeout: 25000,
      showProgressBar: false,
      closeOnClick: false,
      pauseOnHover: true,
      position: SnotifyPosition.centerTop,
      buttons: [
        { text: 'Yes', action: toast => this.lockSingleView(toast, employeeView), bold: false },
        { text: 'No', action: toast => this.snotifyService.remove(toast.id) }
      ]
    });
  }

  lockSingleView(toast, employeeView: IEmployeeView) {
    const query = new TdsDeclarationSearch();
    query.cardNo = employeeView.cardNo;
    query.year = this.tdsDeclarationSearch.year;
    this.tdsDeclarationService.lockSingle(query).subscribe(response => {
      this.snotifyService.remove(toast.id);
      this.search();
      this.snotifyService.success('Lock successfully!', '', toastConfig);
    });
  }

  unlockSingle(employeeView: IEmployeeView) {
    this.snotifyService.confirm('Are you sure to unlock ' + employeeView.name + '?', 'Confirm', {
      timeout: 25000,
      showProgressBar: false,
      closeOnClick: false,
      pauseOnHover: true,
      position: SnotifyPosition.centerTop,
      buttons: [
        { text: 'Yes', action: toast => this.unlockSingleView(toast, employeeView), bold: false },
        { text: 'No', action: toast => this.snotifyService.remove(toast.id) }
      ]
    });
  }

  unlockSingleView(toast, employeeView: IEmployeeView) {
    const query = new TdsDeclarationSearch();
    query.cardNo = employeeView.cardNo;
    query.year = this.tdsDeclarationSearch.year;
    this.tdsDeclarationService.unlockSingle(query).subscribe(response => {
      this.snotifyService.remove(toast.id);
      this.search();
      this.snotifyService.success('Unlock successfully!', '', toastConfig);
    });
  }

  clear() {
    this.page = 0;
    this.router.navigate([
      '/tds-declaration',
      {
        page: this.page,
        sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
      }
    ]);
    this.loadAll();
  }

  ngOnInit() {
    this.tdsDeclarationSearch = new TdsDeclarationSearch();
    this.loadAll();
    this.accountService.identity().then(account => {
      this.currentAccount = account;
    });
    this.registerChangeInTdsDeclarations();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: ITdsDeclaration) {
    return item.id;
  }

  registerChangeInTdsDeclarations() {
    this.eventSubscriber = this.eventManager.subscribe('tdsDeclarationListModification', response => this.loadAll());
  }

  sort() {
    const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected paginateTdsDeclarations(data: IEmployeeView[], headers: HttpHeaders) {
    this.links = this.parseLinks.parse(headers.get('link'));
    this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
    this.queryCount = this.totalItems;
    this.tdsDeclarations = data;
  }

  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }

  tdsDocumentUpload(year?: string, cardNo?: string) {
    const tdsDeclarationUploadSearch = new TdsDeclarationUploadSearch();
    tdsDeclarationUploadSearch.cardNo = cardNo;
    tdsDeclarationUploadSearch.financialYear = year;
    this.tdsDeclarationService.document(tdsDeclarationUploadSearch).subscribe(tdsDeclarationUploads => {
      this.ngbModalRef = this.modalService.open(TdsDeclarationUploadQryUpdateComponent as Component, {
        size: 'lg',
        backdrop: 'static',
        windowClass: 'xlModal'
      });
      this.ngbModalRef.componentInstance.tdsDeclarationUpload = tdsDeclarationUploads.body;
      this.ngbModalRef.componentInstance.tdsYearMaster = this.currentYear;
    });
  }
}
