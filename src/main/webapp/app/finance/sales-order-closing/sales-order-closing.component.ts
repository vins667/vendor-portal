import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';

import { ISalesOrderClosing, SalesOrderClosing } from './sales-order-closing.model';
import { AccountService } from 'app/core/auth/account.service';
import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { SalesOrderClosingService } from './sales-order-closing.service';
import { SalesOrderClosingDeleteDialogComponent } from 'app/finance/sales-order-closing/sales-order-closing-delete-dialog.component';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { ISalesOrderClosingSearch, SalesOrderClosingSearch } from 'app/finance/sales-order-closing/sales-order-closing-search.model';

@Component({
  selector: 'jhi-sales-order-closing',
  templateUrl: './sales-order-closing.component.html'
})
export class SalesOrderClosingComponent implements OnInit, OnDestroy {
  currentAccount: any;
  salesOrderClosings: ISalesOrderClosing[];
  error: any;
  success: any;
  eventSubscriber: Subscription;
  routeData: any;
  links: any;
  totalItems = 0;
  itemsPerPage = ITEMS_PER_PAGE;
  page!: number;
  predicate!: string;
  previousPage: any;
  ascending!: boolean;
  ngbPaginationPage = 1;
  search: ISalesOrderClosingSearch;

  constructor(
    protected salesOrderClosingService: SalesOrderClosingService,
    protected parseLinks: JhiParseLinks,
    protected jhiAlertService: JhiAlertService,
    protected accountService: AccountService,
    protected activatedRoute: ActivatedRoute,
    protected router: Router,
    protected modalService: NgbModal,
    protected eventManager: JhiEventManager
  ) {
    this.search = new SalesOrderClosingSearch();
    this.search.shippedPercentage = 95;
    this.search.status = '0';
    /*this.routeData = this.activatedRoute.data.subscribe(data => {
      this.page = data.pagingParams.page;
      this.previousPage = data.pagingParams.page;
      this.predicate = data.pagingParams.predicate;
    });*/
  }

  loadAll() {
    this.salesOrderClosingService
      .query({
        page: this.page - 1,
        size: this.itemsPerPage,
        sort: this.sort()
      })
      .subscribe(
        (res: HttpResponse<ISalesOrderClosing[]>) => this.paginateSalesOrderClosings(res.body, res.headers),
        (res: HttpErrorResponse) => this.onError()
      );
  }

  loadPage(page?: number) {
    const pageToLoad: number = page || this.page;
    this.search.size = this.itemsPerPage;
    this.search.pageNo = pageToLoad - 1;
    const sort = this.sort();
    if (sort && sort.length > 0) {
      this.search.sort = sort[0].split(',')[0];
      this.search.sortType = sort[0].split(',')[1];
    } else {
      this.search.sort = 'code';
      this.search.sortType = 'asc';
    }
    this.salesOrderClosingService
      .queryFilter(this.search)
      .subscribe((res: HttpResponse<ISalesOrderClosing[]>) => this.onSuccess(res.body, res.headers, pageToLoad), () => this.onError());
  }

  ngOnInit() {
    this.registerChangeInSalesOrderClosings();
    this.activatedRoute.data.subscribe(data => {
      this.page = data.pagingParams.page;
      this.ascending = data.pagingParams.ascending;
      this.predicate = data.pagingParams.predicate;
      this.ngbPaginationPage = data.pagingParams.page;
      this.loadPage();
    });
  }

  ngOnDestroy() {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ISalesOrderClosing): any {
    return item.projectCode;
  }

  registerChangeInSalesOrderClosings() {
    this.eventSubscriber = this.eventManager.subscribe('salesOrderClosingListModification', response => this.loadAll());
  }

  sort() {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== '1') {
      result.push('1');
    }
    return result;
  }

  protected paginateSalesOrderClosings(data: ISalesOrderClosing[], headers: HttpHeaders) {
    this.links = this.parseLinks.parse(headers.get('link'));
    this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
    this.salesOrderClosings = data;
  }

  protected onError(): void {
    this.ngbPaginationPage = this.page;
  }

  delete(billRegister: ISalesOrderClosing): void {
    const modalRef = this.modalService.open(SalesOrderClosingDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.billRegister = billRegister;
  }

  edit(projectcode: string): void {
    this.salesOrderClosingService.projectcode = projectcode;
    this.router.navigate(['project-closings/edit']);
  }

  protected onSuccess(data: ISalesOrderClosing[] | null, headers: HttpHeaders, page: number): void {
    this.totalItems = Number(headers.get('X-Total-Count'));
    this.page = page;
    this.salesOrderClosings = data || [];
  }
}
