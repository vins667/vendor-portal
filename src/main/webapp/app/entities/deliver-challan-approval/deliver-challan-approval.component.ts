import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';
import { DeliverChallanApprovalService } from './deliver-challan-approval.service';
import { IDeliveryChallanSearch, DeliveryChallanSearch } from 'app/shared/model/delivery-challan-search.model';
import { IDeliveryChallan } from 'app/shared/model/delivery-challan.model';
import { AccountService } from 'app/core/auth/account.service';
import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';

@Component({
  selector: 'jhi-deliver-challan-approval',
  templateUrl: './deliver-challan-approval.component.html'
})
export class DeliverChallanApprovalComponent implements OnInit, OnDestroy {
  currentAccount: any;
  deliveryChallanSearch: IDeliveryChallanSearch;
  deliveryChallans: IDeliveryChallan[];
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

  constructor(
    protected deliverChallanApprovalService: DeliverChallanApprovalService,
    protected parseLinks: JhiParseLinks,
    protected jhiAlertService: JhiAlertService,
    protected accountService: AccountService,
    protected activatedRoute: ActivatedRoute,
    protected router: Router,
    protected eventManager: JhiEventManager
  ) {
    this.itemsPerPage = ITEMS_PER_PAGE;
    this.routeData = this.activatedRoute.data.subscribe(data => {
      this.page = data.pagingParams.page;
      this.previousPage = data.pagingParams.page;
      this.reverse = data.pagingParams.ascending;
      this.predicate = data.pagingParams.predicate;
      this.page = 1;
    });
  }

  search() {
    this.deliveryChallanSearch.size = ITEMS_PER_PAGE;
    this.deliveryChallanSearch.pageNo = 0;
    this.page = 0;
    this.deliverChallanApprovalService
      .queryCustom(this.deliveryChallanSearch)
      .subscribe(
        (res: HttpResponse<IDeliveryChallan[]>) => this.paginateDeliveryChallans(res.body, res.headers),
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  loadAllNew() {
    this.deliveryChallanSearch.size = this.itemsPerPage;
    this.deliveryChallanSearch.pageNo = this.page - 1;
    this.deliverChallanApprovalService
      .queryCustom(this.deliveryChallanSearch)
      .subscribe(
        (res: HttpResponse<IDeliveryChallan[]>) => this.paginateDeliveryChallans(res.body, res.headers),
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  loadAll() {
    this.search();
  }
  loadPage(page: number) {
    if (page !== this.previousPage) {
      this.previousPage = page;
      this.transition();
    }
  }

  transition() {
    this.loadAllNew();
  }

  clear() {
    this.page = 0;
    this.router.navigate([
      '/delivery-challan',
      {
        page: this.page,
        sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
      }
    ]);
    this.loadAll();
  }

  ngOnInit() {
    this.deliveryChallanSearch = new DeliveryChallanSearch();
    this.deliveryChallanSearch.status = 'E';
    this.loadAll();
    this.accountService.identity().then(account => {
      this.currentAccount = account;
    });
    this.registerChangeInDeliveryChallans();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IDeliveryChallan) {
    return item.id;
  }

  registerChangeInDeliveryChallans() {
    this.eventSubscriber = this.eventManager.subscribe('deliveryChallanListModification', response => this.loadAll());
  }

  sort() {
    const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected paginateDeliveryChallans(data: IDeliveryChallan[], headers: HttpHeaders) {
    this.links = this.parseLinks.parse(headers.get('link'));
    this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
    this.queryCount = this.totalItems;
    this.deliveryChallans = data;
  }

  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }
}
