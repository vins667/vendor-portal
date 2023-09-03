import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';
import { IEmployeeView } from 'app/shared/model/employee-view.model';
import { AccountService } from 'app/core/auth/account.service';
import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { EmployeeViewService } from 'app/entities/employee-view';
import { EmployeeSearch, IEmployeeSearch } from 'app/shared/model/employee-search.model';

@Component({
  selector: 'jhi-connect-me',
  templateUrl: './connect-me.component.html'
})
export class ConnectMeComponent implements OnInit, OnDestroy {
  currentAccount: any;
  employeeViews: IEmployeeView[];
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
  employeesearch: IEmployeeSearch;

  constructor(
    protected employeeViewService: EmployeeViewService,
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
      this.predicate = 'name';
    });
  }

  displayImage(path) {}

  loadAll() {}

  loadPage(page: number) {
    if (page !== this.previousPage) {
      this.previousPage = page;
      this.transition();
    }
  }

  search() {
    if (
      this.employeesearch.empCode !== null ||
      this.employeesearch.name !== null ||
      this.employeesearch.designation !== null ||
      this.employeesearch.department !== null
    ) {
      this.employeesearch.size = ITEMS_PER_PAGE;
      this.employeesearch.pageNo = 0;
      this.page = 0;
      this.employeeViewService
        .query(this.employeesearch)
        .subscribe(
          (res: HttpResponse<IEmployeeView[]>) => this.paginateEmployeeViews(res.body, res.headers),
          (res: HttpErrorResponse) => this.onError(res.message)
        );
    }
  }

  loadAllNew() {
    if (
      this.employeesearch.empCode !== null ||
      this.employeesearch.name !== null ||
      this.employeesearch.designation !== null ||
      this.employeesearch.department !== null
    ) {
      this.employeesearch.size = this.itemsPerPage;
      this.employeesearch.pageNo = this.page - 1;
      this.page = 0;
      this.employeeViewService
        .query(this.employeesearch)
        .subscribe(
          (res: HttpResponse<IEmployeeView[]>) => this.paginateEmployeeViews(res.body, res.headers),
          (res: HttpErrorResponse) => this.onError(res.message)
        );
    }
  }

  transition() {
    this.router.navigate(['/connect-me'], {
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
      '/employee-view',
      {
        page: this.page,
        sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
      }
    ]);
    this.loadAll();
  }

  ngOnInit() {
    this.employeesearch = new EmployeeSearch();
    this.loadAll();
    this.accountService.identity().then(account => {
      this.currentAccount = account;
    });
    this.registerChangeInEmployeeViews();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IEmployeeView) {
    return item.cardNo;
  }

  registerChangeInEmployeeViews() {
    this.eventSubscriber = this.eventManager.subscribe('employeeViewListModification', response => this.loadAll());
  }

  sort() {
    const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
    if (this.predicate !== 'cardNo') {
      result.push('cardNo');
    }
    return result;
  }

  protected paginateEmployeeViews(data: IEmployeeView[], headers: HttpHeaders) {
    this.links = this.parseLinks.parse(headers.get('link'));
    this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
    this.queryCount = this.totalItems;
    this.employeeViews = data;
  }

  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }
}
