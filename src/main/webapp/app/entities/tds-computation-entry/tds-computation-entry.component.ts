import { Component, OnInit, OnDestroy } from '@angular/core';
import * as FileSaver from 'file-saver';
import { HttpErrorResponse, HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Observable, Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';
import { ITdsComputation } from 'app/shared/model/tds-computation.model';
import { AccountService } from 'app/core/auth/account.service';
import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { TdsComputationEntryService } from './tds-computation-entry.service';
import { ITdsYearMaster } from 'app/shared/model/tds-year-master.model';
import { TdsYearMasterService } from 'app/entities/tds-year-master';
import { ITdsDeclarationSearch, TdsDeclarationSearch } from 'app/shared/model/tds-declaration-search.model';
import { SnotifyPosition, SnotifyService } from 'ng-snotify';
import { toastConfig } from 'app/core/toast/toast-config';

@Component({
  selector: 'jhi-tds-computation',
  templateUrl: './tds-computation-entry.component.html'
})
export class TdsComputationEntryComponent implements OnInit, OnDestroy {
  currentAccount: any;
  tdsComputations: ITdsComputation[];
  tdsyearmasters: ITdsYearMaster[];
  currentYear: ITdsYearMaster;
  tdsDeclarationSearch: ITdsDeclarationSearch;
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
  isWait = false;
  isDownload = false;
  constructor(
    protected tdsComputationService: TdsComputationEntryService,
    protected parseLinks: JhiParseLinks,
    protected jhiAlertService: JhiAlertService,
    protected accountService: AccountService,
    protected activatedRoute: ActivatedRoute,
    protected router: Router,
    protected eventManager: JhiEventManager,
    protected snotifyService: SnotifyService,
    protected tdsYearMasterService: TdsYearMasterService
  ) {
    this.itemsPerPage = ITEMS_PER_PAGE;
    this.routeData = this.activatedRoute.data.subscribe(data => {
      this.page = data.pagingParams.page;
      this.previousPage = data.pagingParams.page;
      this.reverse = data.pagingParams.ascending;
      this.predicate = data.pagingParams.predicate;
    });
  }

  loadAll() {
    this.tdsYearMasterService.query().subscribe(
      (res: HttpResponse<ITdsYearMaster[]>) => {
        this.tdsyearmasters = res.body;
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

  loadAllNew() {
    this.tdsDeclarationSearch.size = this.itemsPerPage;
    this.tdsDeclarationSearch.pageNo = this.page - 1;
    this.tdsComputationService
      .query(this.tdsDeclarationSearch)
      .subscribe(
        (res: HttpResponse<ITdsComputation[]>) => this.paginateTdsComputations(res.body, res.headers),
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  transition() {
    this.router.navigate(['/tds-computation-qry'], {
      queryParams: {
        page: this.page,
        size: this.itemsPerPage,
        sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
      }
    });
    this.loadAllNew();
  }

  clear() {
    this.page = 0;
    this.router.navigate([
      '/tds-computation-qry',
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
    this.registerChangeInTdsComputations();
  }

  search() {
    this.tdsDeclarationSearch.size = ITEMS_PER_PAGE;
    this.tdsDeclarationSearch.pageNo = 0;
    this.page = 0;
    this.tdsComputationService
      .query(this.tdsDeclarationSearch)
      .subscribe(
        (res: HttpResponse<ITdsComputation[]>) => this.paginateTdsComputations(res.body, res.headers),
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  processSingle(tdsComputation: ITdsComputation) {
    this.snotifyService.confirm('Are you sure to process ' + tdsComputation.name + '?', 'Confirm', {
      timeout: 25000,
      showProgressBar: false,
      closeOnClick: false,
      pauseOnHover: true,
      position: SnotifyPosition.centerTop,
      buttons: [
        { text: 'Yes', action: toast => this.processOne(toast, tdsComputation), bold: false },
        { text: 'No', action: toast => this.snotifyService.remove(toast.id) }
      ]
    });
  }

  processOne(toast, tdsComputation: ITdsComputation) {
    this.snotifyService.remove(toast.id);
    this.isWait = true;
    this.subscribeToSaveResponse(this.tdsComputationService.process(tdsComputation.id));
  }

  processAll() {
    this.snotifyService.confirm('Are you sure to process All?', 'Confirm', {
      timeout: 25000,
      showProgressBar: false,
      closeOnClick: false,
      pauseOnHover: true,
      position: SnotifyPosition.centerTop,
      buttons: [
        { text: 'Yes', action: toast => this.process(toast), bold: false },
        { text: 'No', action: toast => this.snotifyService.remove(toast.id) }
      ]
    });
  }

  exportAll() {
    this.tdsComputationService.exportAll().subscribe(
      res => {
        FileSaver.saveAs(res, 'TdsComputationReportSummary.xlsx');
        this.isDownload = false;
      },
      res => {
        this.isDownload = false;
      }
    );
  }
  process(toast) {
    this.snotifyService.remove(toast.id);
    this.isWait = true;
    this.subscribeToSaveResponse(this.tdsComputationService.processAll());
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: ITdsComputation) {
    return item.id;
  }

  registerChangeInTdsComputations() {
    this.eventSubscriber = this.eventManager.subscribe('tdsComputationListModification', response => this.loadAll());
  }

  sort() {
    const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected paginateTdsComputations(data: ITdsComputation[], headers: HttpHeaders) {
    this.links = this.parseLinks.parse(headers.get('link'));
    this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
    this.queryCount = this.totalItems;
    this.tdsComputations = data;
  }

  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<any>>) {
    result.subscribe((res: HttpResponse<any>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError(res.headers));
  }

  protected onSaveSuccess() {
    this.search();
    this.snotifyService.success('Process run successfully!', '', toastConfig);
    this.isWait = false;
  }

  protected onSaveError(res: HttpHeaders) {
    this.isWait = false;
    this.snotifyService.error(res.get('X-vamaniportalApp-error'), '', toastConfig);
  }
}
