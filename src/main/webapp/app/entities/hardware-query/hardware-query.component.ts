import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';
import { IHardwareQueryBean } from 'app/shared/model/hardware-query.bean.model';
import { AccountService } from 'app/core/auth/account.service';
import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { HardwareQueryService } from './hardware-query.service';
import { IHardwareSearchQuery, HardwareSearchQuery } from 'app/shared/model/hardware-search-query.model';

@Component({
  selector: 'jhi-hardware-query',
  templateUrl: './hardware-query.component.html'
})
export class HardwareQueryComponent implements OnInit, OnDestroy {
  currentAccount: any;
  hardwareQueries: IHardwareQueryBean[];
  hardwareSearch: HardwareSearchQuery;
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
    protected hardwareQueryService: HardwareQueryService,
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
    if (this.hardwareSearch.storageMin !== null || this.hardwareSearch.memoryMin !== null) {
      this.hardwareSearch.size = ITEMS_PER_PAGE;
      this.hardwareSearch.pageNo = 0;
      this.page = 0;
      this.hardwareQueryService
        .queryCustom(this.hardwareSearch)
        .subscribe(
          (res: HttpResponse<IHardwareSearchQuery[]>) => this.paginateHardwareQueries(res.body, res.headers),
          (res: HttpErrorResponse) => this.onError(res.message)
        );
    }
  }

  loadAllNew() {
    this.hardwareSearch.size = this.itemsPerPage;
    this.hardwareSearch.pageNo = this.page - 1;
    this.hardwareQueryService
      .queryCustom(this.hardwareSearch)
      .subscribe(
        (res: HttpResponse<IHardwareQueryBean[]>) => this.paginateHardwareQueries(res.body, res.headers),
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
    this.router.navigate(['/hardware-query'], {
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
      '/hardware-query',
      {
        page: this.page,
        sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
      }
    ]);
    this.loadAll();
  }

  ngOnInit() {
    this.hardwareSearch = new HardwareSearchQuery();
    this.loadAll();
    this.accountService.identity().then(account => {
      this.currentAccount = account;
    });
    this.registerChangeInHardwareQueries();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IHardwareQueryBean) {
    return item.id;
  }

  registerChangeInHardwareQueries() {
    this.eventSubscriber = this.eventManager.subscribe('hardwareQueryListModification', response => this.loadAll());
  }

  sort() {
    const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected paginateHardwareQueries(data: IHardwareQueryBean[], headers: HttpHeaders) {
    this.links = this.parseLinks.parse(headers.get('link'));
    this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
    this.queryCount = this.totalItems;
    this.hardwareQueries = data;
  }

  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }
}
