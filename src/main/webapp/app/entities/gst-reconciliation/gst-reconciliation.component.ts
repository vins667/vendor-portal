import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription, Observable } from 'rxjs';
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';
import { IGstReconciliation } from 'app/shared/model/gst-reconciliation.model';
import { AccountService } from 'app/core/auth/account.service';
import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { GstReconciliationService } from './gst-reconciliation.service';
import { IMessage } from 'app/shared/model/message.model';
import { SnotifyService, SnotifyPosition } from 'ng-snotify';
import { toastConfig } from 'app/core/toast/toast-config';
import { IParameterList, ParameterList } from 'app/shared/model/parameter-list';

@Component({
  selector: 'jhi-gst-reconciliation',
  templateUrl: './gst-reconciliation.component.html'
})
export class GstReconciliationComponent implements OnInit, OnDestroy {
  isSaving: boolean;
  currentAccount: any;
  gstReconciliations: IGstReconciliation[];
  parameterList: IParameterList;
  error: any;
  success: any;
  eventSubscriber: Subscription;
  routeData: any;
  isDownload = false;
  difference = 0;
  links: any;
  totalItems: any;
  itemsPerPage: any;
  page: any;
  predicate: any;
  previousPage: any;
  reverse: any;

  constructor(
    protected gstReconciliationService: GstReconciliationService,
    protected parseLinks: JhiParseLinks,
    protected jhiAlertService: JhiAlertService,
    protected accountService: AccountService,
    protected activatedRoute: ActivatedRoute,
    protected router: Router,
    protected eventManager: JhiEventManager,
    protected snotifyService: SnotifyService
  ) {
    this.itemsPerPage = ITEMS_PER_PAGE;
    this.routeData = this.activatedRoute.data.subscribe(data => {
      this.page = data.pagingParams.page;
      this.previousPage = data.pagingParams.page;
      this.reverse = data.pagingParams.ascending;
      this.predicate = data.pagingParams.predicate;
    });
  }

  loadAll() {}

  loadPage(page: number) {
    if (page !== this.previousPage) {
      this.previousPage = page;
      this.transition();
    }
  }

  transition() {
    this.router.navigate(['/gst-reconciliation'], {
      queryParams: {
        page: this.page,
        size: this.itemsPerPage,
        sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
      }
    });
    this.loadAll();
  }

  clear() {
    this.page = 0;
    this.router.navigate([
      '/gst-reconciliation',
      {
        page: this.page,
        sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
      }
    ]);
    this.loadAll();
  }

  ngOnInit() {
    this.parameterList = new ParameterList();
    this.loadAll();
    this.accountService.identity().then(account => {
      this.currentAccount = account;
    });
    this.registerChangeInGstReconciliations();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IGstReconciliation) {
    return item.id;
  }

  registerChangeInGstReconciliations() {
    this.eventSubscriber = this.eventManager.subscribe('gstReconciliationListModification', response => this.loadAll());
  }

  reconsileData() {
    if (!this.parameterList.parameter1 && this.parameterList.parameter1 !== 0) {
      this.snotifyService.error('Reconcile Amount cant blank', '', toastConfig);
    } else {
      this.snotifyService.confirm(
        'You choosed reconsile amount ' + this.parameterList.parameter1 + '. Are you sure to fetch data?',
        'Confirm',
        {
          timeout: 25000,
          showProgressBar: false,
          closeOnClick: false,
          pauseOnHover: true,
          position: SnotifyPosition.centerTop,
          buttons: [
            { text: 'Yes', action: toast => this.reconsile(toast), bold: false },
            { text: 'No', action: toast => this.snotifyService.remove(toast.id) }
          ]
        }
      );
    }
  }
  reconsile(toast: any) {
    this.isDownload = true;
    this.snotifyService.remove(toast.id);
    this.gstReconciliationService.reconsiles(this.parameterList).subscribe(res => {
      this.gstReconciliations = res.body;
      this.isDownload = false;
    });
  }

  SaveData() {
    this.isDownload = true;
    if (this.gstReconciliations && this.gstReconciliations.length > 0) {
      this.subscribeToSaveResponse(this.gstReconciliationService.saveReco(this.gstReconciliations));
    }
    this.isDownload = false;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMessage>>) {
    result.subscribe((res: HttpResponse<IMessage>) => this.onSaveSuccess(res), (res: HttpErrorResponse) => this.onSaveError(res.headers));
  }

  protected onSaveSuccess(res: HttpResponse<IMessage>) {
    this.loadAll();
    if (res.body.type === 'success') {
      this.snotifyService.success(res.body.msg, '', toastConfig);
      this.gstReconciliations = [];
    } else {
      this.snotifyService.error(res.body.msg, '', toastConfig);
      this.gstReconciliations = [];
    }
  }

  protected onSaveError(res: HttpHeaders) {
    // this.loadAll();
    this.snotifyService.error(res.get('X-vamaniportalApp-error'), '', toastConfig);
  }

  sort() {
    const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected paginateGstReconciliations(data: IGstReconciliation[], headers: HttpHeaders) {
    this.links = this.parseLinks.parse(headers.get('link'));
    this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
    this.gstReconciliations = data;
  }

  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }
}
