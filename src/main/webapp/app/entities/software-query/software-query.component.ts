import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';
import { AccountService } from 'app/core/auth/account.service';
import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { SoftwareQueryService } from './software-query.service';
import { SoftwareSearchQuery } from 'app/shared/model/software-search-query.model';
import { ISoftwareQueryBean } from 'app/shared/model/software-query-bean.model';

@Component({
  selector: 'jhi-software-query',
  templateUrl: './software-query.component.html'
})
export class SoftwareQueryComponent implements OnInit, OnDestroy {
  currentAccount: any;
  softwareSearchQuery: SoftwareSearchQuery;
  softwareQueries: ISoftwareQueryBean[];
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
    protected softwareQueryService: SoftwareQueryService,
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
      this.predicate = 'id';
    });
  }

  search() {
    if (
      this.softwareSearchQuery.assetCode !== null ||
      this.softwareSearchQuery.publisher !== null ||
      this.softwareSearchQuery.software !== null
    ) {
      this.softwareSearchQuery.size = ITEMS_PER_PAGE;
      this.softwareSearchQuery.pageNo = 0;
      this.page = 0;
      this.softwareQueryService
        .queryCustom(this.softwareSearchQuery)
        .subscribe(
          (res: HttpResponse<ISoftwareQueryBean[]>) => this.paginateSoftwareQueries(res.body, res.headers),
          (res: HttpErrorResponse) => this.onError(res.message)
        );
    }
  }

  loadAllNew() {
    this.softwareSearchQuery.size = this.itemsPerPage;
    this.softwareSearchQuery.pageNo = this.page - 1;
    this.softwareQueryService
      .queryCustom(this.softwareSearchQuery)
      .subscribe(
        (res: HttpResponse<ISoftwareQueryBean[]>) => this.paginateSoftwareQueries(res.body, res.headers),
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
    this.router.navigate(['/software-query'], {
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
      '/software-query',
      {
        page: this.page,
        sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
      }
    ]);
    this.loadAll();
  }

  ngOnInit() {
    this.softwareSearchQuery = new SoftwareSearchQuery();
    this.loadAll();
    this.accountService.identity().then(account => {
      this.currentAccount = account;
    });
    this.registerChangeInSoftwareQueries();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: ISoftwareQueryBean) {
    return item.id;
  }

  registerChangeInSoftwareQueries() {
    this.eventSubscriber = this.eventManager.subscribe('softwareQueryListModification', response => this.loadAll());
  }

  sort() {
    const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected paginateSoftwareQueries(data: ISoftwareQueryBean[], headers: HttpHeaders) {
    this.links = this.parseLinks.parse(headers.get('link'));
    this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
    this.queryCount = this.totalItems;
    this.softwareQueries = data;
  }

  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }
}
