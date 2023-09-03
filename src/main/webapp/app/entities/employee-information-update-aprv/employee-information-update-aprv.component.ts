import { Component, OnInit, OnDestroy, ViewEncapsulation } from '@angular/core';
import { HttpErrorResponse, HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';
import { IEmployeeInformationUpdateAprv } from 'app/shared/model/employee-information-update-aprv.model';
import { AccountService } from 'app/core/auth/account.service';
import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { EmployeeInformationUpdateAprvService } from './employee-information-update-aprv.service';
import { DateTimeAdapter, OWL_DATE_TIME_FORMATS, OWL_DATE_TIME_LOCALE } from 'ng-pick-datetime';
import { MomentDateTimeAdapter } from 'ng-pick-datetime-moment';
import { MY_MOMENT_FORMATS } from 'app/entities/leave-master';
import { ILeaveSearch, LeaveSearch } from 'app/shared/model/leave-search.model';
import * as moment from 'moment';

@Component({
  selector: 'jhi-employee-information-update-aprv',
  templateUrl: './employee-information-update-aprv.component.html',
  encapsulation: ViewEncapsulation.None,
  providers: [
    // `MomentDateTimeAdapter` and `OWL_MOMENT_DATE_TIME_FORMATS` can be automatically provided by importing
    // `OwlMomentDateTimeModule` in your applications root module. We provide it at the component level
    // here, due to limitations of our example generation script.
    { provide: DateTimeAdapter, useClass: MomentDateTimeAdapter, deps: [OWL_DATE_TIME_LOCALE] },
    { provide: OWL_DATE_TIME_FORMATS, useValue: MY_MOMENT_FORMATS }
  ]
})
export class EmployeeInformationUpdateAprvComponent implements OnInit, OnDestroy {
  currentAccount: any;
  employeeInformationUpdateAprvs: IEmployeeInformationUpdateAprv[];
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
  leavesearch: ILeaveSearch;
  leaveDateFrom: any;
  leaveDateTo: any;

  constructor(
    protected employeeInformationUpdateAprvService: EmployeeInformationUpdateAprvService,
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
    });
  }

  loadAll() {
    const dt = new Date();
    const month = dt.getMonth(),
      year = dt.getFullYear();
    const FirstDay = new Date(year, month, 1);
    const LastDay = new Date(year, month + 1, 0);
    this.leaveDateFrom = FirstDay;
    this.leaveDateFrom.setHours(0, 0, 0, 0);
    this.leaveDateTo = LastDay;
    this.leaveDateTo.setHours(0, 0, 0, 0);
    this.leavesearch.leaveDateFrom = this.leaveDateFrom != null ? moment(this.leaveDateFrom, DATE_TIME_FORMAT).startOf('day') : null;
    this.leavesearch.leaveDateTo = this.leaveDateTo != null ? moment(this.leaveDateTo, DATE_TIME_FORMAT).startOf('day') : null;
    if (this.leavesearch.leaveStatus === null || this.leavesearch.leaveStatus === undefined) {
      this.leavesearch.leaveStatus = 'P';
    }
    this.leavesearch.size = ITEMS_PER_PAGE;
    this.leavesearch.pageNo = 0;
    this.page = 0;
    this.employeeInformationUpdateAprvService
      .query(this.leavesearch)
      .subscribe(
        (res: HttpResponse<IEmployeeInformationUpdateAprv[]>) => this.paginateEmployeeInformationUpdateAprvs(res.body, res.headers),
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  loadPage(page: number) {
    if (page !== this.previousPage) {
      this.previousPage = page;
      this.transition();
    }
  }

  search() {
    if (this.leaveDateFrom != null && this.leaveDateTo !== null && this.leavesearch.leaveStatus !== null) {
      this.leavesearch.leaveDateFrom = this.leaveDateFrom != null ? moment(this.leaveDateFrom, DATE_TIME_FORMAT).startOf('day') : null;
      this.leavesearch.leaveDateTo = this.leaveDateTo != null ? moment(this.leaveDateTo, DATE_TIME_FORMAT).startOf('day') : null;
      this.leavesearch.size = ITEMS_PER_PAGE;
      this.leavesearch.pageNo = 0;
      this.page = 0;
      this.employeeInformationUpdateAprvService
        .query(this.leavesearch)
        .subscribe(
          (res: HttpResponse<IEmployeeInformationUpdateAprv[]>) => this.paginateEmployeeInformationUpdateAprvs(res.body, res.headers),
          (res: HttpErrorResponse) => this.onError(res.message)
        );
    }
  }

  loadAllNew() {
    if (this.leaveDateFrom != null && this.leaveDateTo !== null && this.leavesearch.leaveStatus !== null) {
      this.leavesearch.leaveDateFrom = this.leaveDateFrom != null ? moment(this.leaveDateFrom, DATE_TIME_FORMAT).startOf('day') : null;
      this.leavesearch.leaveDateTo = this.leaveDateTo != null ? moment(this.leaveDateTo, DATE_TIME_FORMAT).startOf('day') : null;
      this.leavesearch.size = this.itemsPerPage;
      this.leavesearch.pageNo = this.page - 1;
      this.employeeInformationUpdateAprvService
        .query(this.leavesearch)
        .subscribe(
          (res: HttpResponse<IEmployeeInformationUpdateAprv[]>) => this.paginateEmployeeInformationUpdateAprvs(res.body, res.headers),
          (res: HttpErrorResponse) => this.onError(res.message)
        );
    }
  }

  transition() {
    this.router.navigate(['/employee-information-update-aprv'], {
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
      '/employee-information-update-aprv',
      {
        page: this.page,
        sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
      }
    ]);
    this.loadAll();
  }

  ngOnInit() {
    this.leavesearch = new LeaveSearch();
    this.loadAll();
    this.accountService.identity().then(account => {
      this.currentAccount = account;
    });
    this.registerChangeInEmployeeInformationUpdateAprvs();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IEmployeeInformationUpdateAprv) {
    return item.id;
  }

  registerChangeInEmployeeInformationUpdateAprvs() {
    this.eventSubscriber = this.eventManager.subscribe('employeeInformationUpdateAprvListModification', response => this.loadAll());
  }

  sort() {
    const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected paginateEmployeeInformationUpdateAprvs(data: IEmployeeInformationUpdateAprv[], headers: HttpHeaders) {
    this.links = this.parseLinks.parse(headers.get('link'));
    this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
    this.queryCount = this.totalItems;
    this.employeeInformationUpdateAprvs = data;
  }

  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }
}
