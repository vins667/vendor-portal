import { Component, OnInit, OnDestroy, ViewEncapsulation } from '@angular/core';
import { HttpErrorResponse, HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';
import { DateTimeAdapter, OWL_DATE_TIME_FORMATS, OWL_DATE_TIME_LOCALE } from 'ng-pick-datetime';
import { MomentDateTimeAdapter } from 'ng-pick-datetime-moment';
import { ILeaveEntryHr } from 'app/shared/model/leave-entry-hr.model';
import { AccountService } from 'app/core/auth/account.service';
import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { LeaveEntryHrService } from './leave-entry-hr.service';
import { ILeaveSearch, LeaveSearch } from 'app/shared/model/leave-search.model';
import * as moment from 'moment';
import { MY_MOMENT_FORMATS } from 'app/entities/leave-master';
import { ILeaveMaster } from 'app/shared/model/leave-master.model';

@Component({
  selector: 'jhi-leave-entry-hr',
  templateUrl: './leave-entry-hr.component.html',
  encapsulation: ViewEncapsulation.None,
  providers: [
    // `MomentDateTimeAdapter` and `OWL_MOMENT_DATE_TIME_FORMATS` can be automatically provided by importing
    // `OwlMomentDateTimeModule` in your applications root module. We provide it at the component level
    // here, due to limitations of our example generation script.
    { provide: DateTimeAdapter, useClass: MomentDateTimeAdapter, deps: [OWL_DATE_TIME_LOCALE] },
    { provide: OWL_DATE_TIME_FORMATS, useValue: MY_MOMENT_FORMATS }
  ]
})
export class LeaveEntryHrComponent implements OnInit, OnDestroy {
  leaveMasters: ILeaveEntryHr[];
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
  currentAccount: any;
  leaveEntryHrs: ILeaveEntryHr[];

  leavesearch: ILeaveSearch;
  leaveDateFrom: any;
  leaveDateTo: any;

  constructor(
    protected leaveEntryHrService: LeaveEntryHrService,
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
    this.leavesearch.leaveStatus = 'P';
    this.leavesearch.size = ITEMS_PER_PAGE;
    this.leavesearch.pageNo = 0;
    this.page = 0;
    this.leaveEntryHrService
      .queryHr(this.leavesearch)
      .subscribe(
        (res: HttpResponse<ILeaveMaster[]>) => this.paginateLeaveEntryHrs(res.body, res.headers),
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  loadPage(page: number) {
    if (page !== this.previousPage) {
      this.previousPage = page;
      this.transition();
    }
  }

  loadAllNew() {
    if (this.leaveDateFrom != null && this.leaveDateTo !== null && this.leavesearch.leaveStatus !== null) {
      this.leavesearch.leaveDateFrom = this.leaveDateFrom != null ? moment(this.leaveDateFrom, DATE_TIME_FORMAT).startOf('day') : null;
      this.leavesearch.leaveDateTo = this.leaveDateTo != null ? moment(this.leaveDateTo, DATE_TIME_FORMAT).startOf('day') : null;
      this.leavesearch.size = this.itemsPerPage;
      this.leavesearch.pageNo = this.page - 1;
      this.leaveEntryHrService
        .queryHr(this.leavesearch)
        .subscribe(
          (res: HttpResponse<ILeaveMaster[]>) => this.paginateLeaveEntryHrs(res.body, res.headers),
          (res: HttpErrorResponse) => this.onError(res.message)
        );
    }
  }

  transition() {
    this.router.navigate(['leave-entry-hr'], {
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
      '/leave-entry-hr',
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
    this.registerChangeInLeaveEntryHrs();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: ILeaveEntryHr) {
    return item.id;
  }

  registerChangeInLeaveEntryHrs() {
    this.eventSubscriber = this.eventManager.subscribe('leaveEntryHrListModification', response => this.loadAll());
  }

  sort() {
    const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected paginateLeaveEntryHrs(data: ILeaveEntryHr[], headers: HttpHeaders) {
    this.links = this.parseLinks.parse(headers.get('link'));
    this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
    this.queryCount = this.totalItems;
    this.leaveEntryHrs = data;
  }

  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }

  search() {
    if (this.leaveDateFrom != null && this.leaveDateTo !== null && this.leavesearch.leaveStatus !== null) {
      this.leavesearch.leaveDateFrom = this.leaveDateFrom != null ? moment(this.leaveDateFrom, DATE_TIME_FORMAT).startOf('day') : null;
      this.leavesearch.leaveDateTo = this.leaveDateTo != null ? moment(this.leaveDateTo, DATE_TIME_FORMAT).startOf('day') : null;
      this.leavesearch.size = ITEMS_PER_PAGE;
      this.leavesearch.pageNo = 0;
      this.page = 0;
      this.leaveEntryHrService
        .queryHr(this.leavesearch)
        .subscribe(
          (res: HttpResponse<ILeaveMaster[]>) => this.paginateLeaveEntryHrs(res.body, res.headers),
          (res: HttpErrorResponse) => this.onError(res.message)
        );
    }
  }
}
