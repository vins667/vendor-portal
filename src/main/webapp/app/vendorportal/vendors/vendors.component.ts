import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';
import { IVendors, Vendors } from 'app/shared/model/vendors.model';
import { AccountService } from 'app/core/auth/account.service';
import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { VendorsService } from './vendors.service';

@Component({
  selector: 'jhi-vendors',
  templateUrl: './vendors.component.html'
})
export class VendorsComponent implements OnInit, OnDestroy {
  currentAccount: any;
  vendors: IVendors[];
  vendorSearch: IVendors;
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
    protected vendorsService: VendorsService,
    protected parseLinks: JhiParseLinks,
    protected jhiAlertService: JhiAlertService,
    protected accountService: AccountService,
    protected activatedRoute: ActivatedRoute,
    protected router: Router,
    protected eventManager: JhiEventManager
  ) {
    this.vendorSearch = new Vendors();
    this.itemsPerPage = ITEMS_PER_PAGE;
    this.routeData = this.activatedRoute.data.subscribe(data => {
      this.page = data.pagingParams.page;
      this.previousPage = data.pagingParams.page;
      this.reverse = data.pagingParams.ascending;
      this.predicate = data.pagingParams.predicate;
    });
  }

  loadAll() {
    this.vendorSearch.size = ITEMS_PER_PAGE;
    this.vendorSearch.pageNo = 0;
    this.page = 0;
    this.vendorsService
      .query(this.vendorSearch)
      .subscribe(
        (res: HttpResponse<IVendors[]>) => this.paginateVendors(res.body, res.headers),
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  loadAllNew() {
    this.vendorSearch.size = this.itemsPerPage;
    this.vendorSearch.pageNo = this.page - 1;
    this.vendorsService
      .query(this.vendorSearch)
      .subscribe(
        (res: HttpResponse<IVendors[]>) => this.paginateVendors(res.body, res.headers),
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  loadPage(page: number) {
    if (page !== this.previousPage) {
      this.previousPage = page;
      this.transition();
    }
  }

  transition() {
    this.router.navigate(['/vendors'], {
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
      '/vendors',
      {
        page: this.page,
        sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
      }
    ]);
    this.loadAll();
  }

  ngOnInit() {
    this.vendorSearch.approvalStatus = 'F';
    this.loadAll();
    this.accountService.identity().then(account => {
      this.currentAccount = account;
    });
    this.registerChangeInVendors();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IVendors) {
    return item.id;
  }

  registerChangeInVendors() {
    this.eventSubscriber = this.eventManager.subscribe('vendorsListModification', response => this.loadAll());
  }

  sort() {
    const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected paginateVendors(data: IVendors[], headers: HttpHeaders) {
    this.links = this.parseLinks.parse(headers.get('link'));
    this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
    this.queryCount = this.totalItems;
    this.vendors = data;
  }

  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }

  search() {
    this.vendorSearch.size = ITEMS_PER_PAGE;
    this.vendorSearch.pageNo = 0;
    this.page = 0;
    this.vendorsService
      .query(this.vendorSearch)
      .subscribe(
        (res: HttpResponse<IVendors[]>) => this.paginateVendors(res.body, res.headers),
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }
}
