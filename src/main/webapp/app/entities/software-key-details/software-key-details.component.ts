import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';
import { ISoftwareKeyDetailsBean } from 'app/shared/model/software-key-details-bean.model';
import { AccountService } from 'app/core/auth/account.service';
import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { SoftwareKeyDetailsService } from './software-key-details.service';
import { SoftwareKeyDetailsSearch } from 'app/shared/model/software-key-details-search.model';

@Component({
  selector: 'jhi-software-key-details',
  templateUrl: './software-key-details.component.html'
})
export class SoftwareKeyDetailsComponent implements OnInit, OnDestroy {
  currentAccount: any;
  softwareKeyDetails: ISoftwareKeyDetailsBean[];
  softwareKeyDetailsSearch: SoftwareKeyDetailsSearch;
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
    protected softwareKeyDetailsService: SoftwareKeyDetailsService,
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
    if (this.softwareKeyDetailsSearch.name !== null || this.softwareKeyDetailsSearch.jhiKey !== null) {
      this.softwareKeyDetailsSearch.size = ITEMS_PER_PAGE;
      this.softwareKeyDetailsSearch.pageNo = 0;
      this.page = 0;
      this.softwareKeyDetailsService
        .queryCustom(this.softwareKeyDetailsSearch)
        .subscribe(
          (res: HttpResponse<ISoftwareKeyDetailsBean[]>) => this.paginateSoftwareKeyDetails(res.body, res.headers),
          (res: HttpErrorResponse) => this.onError(res.message)
        );
    }
  }

  loadAllNew() {
    this.softwareKeyDetailsSearch.size = this.itemsPerPage;
    this.softwareKeyDetailsSearch.pageNo = this.page - 1;
    this.softwareKeyDetailsService
      .queryCustom(this.softwareKeyDetailsSearch)
      .subscribe(
        (res: HttpResponse<ISoftwareKeyDetailsBean[]>) => this.paginateSoftwareKeyDetails(res.body, res.headers),
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
    this.router.navigate(['/software-key-details'], {
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
      '/software-key-details',
      {
        page: this.page,
        sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
      }
    ]);
    this.loadAll();
  }

  ngOnInit() {
    this.softwareKeyDetailsSearch = new SoftwareKeyDetailsSearch();
    this.loadAll();
    this.accountService.identity().then(account => {
      this.currentAccount = account;
    });
    this.registerChangeInSoftwareKeyDetails();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: ISoftwareKeyDetailsBean) {
    return item.id;
  }

  registerChangeInSoftwareKeyDetails() {
    this.eventSubscriber = this.eventManager.subscribe('softwareKeyDetailsListModification', response => this.loadAll());
  }

  sort() {
    const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected paginateSoftwareKeyDetails(data: ISoftwareKeyDetailsBean[], headers: HttpHeaders) {
    this.links = this.parseLinks.parse(headers.get('link'));
    this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
    this.queryCount = this.totalItems;
    this.softwareKeyDetails = data;
  }

  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }
}
