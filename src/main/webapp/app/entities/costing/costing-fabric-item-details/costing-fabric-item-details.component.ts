import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';
import { ICostingFabricItemDetails } from 'app/shared/model/costing-fabric-item-details.model';
import { AccountService } from 'app/core/auth/account.service';
import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { CostingFabricItemDetailsService } from './costing-fabric-item-details.service';
import { CostingProcessMasterSearch } from 'app/shared/model/costing-process-master-search.modal';

@Component({
  selector: 'jhi-costing-fabric-item-details',
  templateUrl: './costing-fabric-item-details.component.html'
})
export class CostingFabricItemDetailsComponent implements OnInit, OnDestroy {
  currentAccount: any;
  costingFabricItemDetails: ICostingFabricItemDetails[];
  search: CostingProcessMasterSearch;
  error: any;
  success: any;
  eventSubscriber: Subscription;
  routeData: any;
  links: any;
  totalItems: any;
  itemsPerPage: any;
  page: any;
  predicate: any;
  previousPage: any;
  reverse: any;

  constructor(
    protected costingFabricItemDetailsService: CostingFabricItemDetailsService,
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

  filter() {
    this.search.size = ITEMS_PER_PAGE;
    this.search.pageNo = 0;
    this.page = 1;
    this.costingFabricItemDetailsService
      .queryCustom(this.search)
      .subscribe(
        (res: HttpResponse<ICostingFabricItemDetails[]>) => this.paginateCostingFabricItemDetails(res.body, res.headers),
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  loadAllNew() {
    this.search.size = this.itemsPerPage;
    this.search.pageNo = this.page - 1;
    this.costingFabricItemDetailsService
      .queryCustom(this.search)
      .subscribe(
        (res: HttpResponse<ICostingFabricItemDetails[]>) => this.paginateCostingFabricItemDetails(res.body, res.headers),
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  loadAll() {
    this.filter();
  }

  loadPage(page: number) {
    if (page !== this.previousPage) {
      this.previousPage = page;
      this.transition();
    }
  }

  transition() {
    this.router.navigate(['/costing-fabric-item-details'], {
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
      '/costing-fabric-item-details',
      {
        page: this.page,
        sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
      }
    ]);
    this.loadAll();
  }

  ngOnInit() {
    this.search = new CostingProcessMasterSearch();
    this.loadAll();
    this.accountService.identity().then(account => {
      this.currentAccount = account;
    });
    this.registerChangeInCostingFabricItemDetails();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: ICostingFabricItemDetails) {
    return item.id;
  }

  registerChangeInCostingFabricItemDetails() {
    this.eventSubscriber = this.eventManager.subscribe('costingFabricItemDetailsListModification', response => this.loadAll());
  }

  sort() {
    const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected paginateCostingFabricItemDetails(data: ICostingFabricItemDetails[], headers: HttpHeaders) {
    this.links = this.parseLinks.parse(headers.get('link'));
    this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
    this.costingFabricItemDetails = data;
  }

  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }
}
