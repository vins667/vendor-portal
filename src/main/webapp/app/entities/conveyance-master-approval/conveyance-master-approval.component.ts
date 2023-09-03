import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';
import { ConveyanceMasterApprovalService } from './conveyance-master-approval.service';
import { AccountService } from 'app/core/auth/account.service';
import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { IConveyanceMaster } from 'app/shared/model/conveyance-master.model';
import { DateTimeAdapter, OWL_DATE_TIME_FORMATS, OWL_DATE_TIME_LOCALE } from 'ng-pick-datetime';
import { MomentDateTimeAdapter } from 'ng-pick-datetime-moment';
import { MY_MOMENT_FORMATS } from 'app/entities/leave-master';
import { ConveyanceSearchMaster } from 'app/shared/model/conveyance-serach-master.model';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import * as moment from 'moment';

@Component({
  selector: 'jhi-conveyance-master-approval',
  templateUrl: './conveyance-master-approval.component.html',
  providers: [
    // `MomentDateTimeAdapter` and `OWL_MOMENT_DATE_TIME_FORMATS` can be automatically provided by importing
    // `OwlMomentDateTimeModule` in your applications root module. We provide it at the component level
    // here, due to limitations of our example generation script.
    { provide: DateTimeAdapter, useClass: MomentDateTimeAdapter, deps: [OWL_DATE_TIME_LOCALE] },
    { provide: OWL_DATE_TIME_FORMATS, useValue: MY_MOMENT_FORMATS }
  ]
})
export class ConveyanceMasterApprovalComponent implements OnInit, OnDestroy {
  currentAccount: any;
  conveyanceMasters: IConveyanceMaster[];
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
  leaveDateFrom: any;
  leaveDateTo: any;
  conveyanceSearchMaster: ConveyanceSearchMaster;

  constructor(
    protected conveyanceMasterApprovalService: ConveyanceMasterApprovalService,
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
    if (this.leaveDateFrom != null && this.leaveDateTo !== null && this.conveyanceSearchMaster.status !== null) {
      this.conveyanceSearchMaster.size = ITEMS_PER_PAGE;
      this.conveyanceSearchMaster.pageNo = 0;
      this.page = 1;
      this.conveyanceSearchMaster.conveyanceDate =
        this.leaveDateFrom != null ? moment(this.leaveDateFrom, DATE_TIME_FORMAT).startOf('day') : null;
      this.conveyanceSearchMaster.conveyanceDateTo =
        this.leaveDateTo != null ? moment(this.leaveDateTo, DATE_TIME_FORMAT).startOf('day') : null;
      this.conveyanceMasterApprovalService
        .query(this.conveyanceSearchMaster)
        .subscribe(
          (res: HttpResponse<IConveyanceMaster[]>) => this.paginateConveyanceMasterApprovals(res.body, res.headers),
          (res: HttpErrorResponse) => this.onError(res.message)
        );
    }
  }

  loadAllNew() {
    if (this.leaveDateFrom != null && this.leaveDateTo !== null && this.conveyanceSearchMaster.status !== null) {
      this.conveyanceSearchMaster.conveyanceDate =
        this.leaveDateFrom != null ? moment(this.leaveDateFrom, DATE_TIME_FORMAT).startOf('day') : null;
      this.conveyanceSearchMaster.conveyanceDateTo =
        this.leaveDateTo != null ? moment(this.leaveDateTo, DATE_TIME_FORMAT).startOf('day') : null;
      this.conveyanceSearchMaster.size = this.itemsPerPage;
      this.conveyanceSearchMaster.pageNo = this.page - 1;
      this.conveyanceMasterApprovalService
        .query(this.conveyanceSearchMaster)
        .subscribe(
          (res: HttpResponse<IConveyanceMaster[]>) => this.paginateConveyanceMasterApprovals(res.body, res.headers),
          (res: HttpErrorResponse) => this.onError(res.message)
        );
    }
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
    if (this.conveyanceSearchMaster === null || this.conveyanceSearchMaster.status === undefined) {
      this.conveyanceSearchMaster.status = 'P';
    }
    this.search();
  }

  loadPage(page: number) {
    if (page !== this.previousPage) {
      this.previousPage = page;
      this.transition();
    }
  }

  transition() {
    this.router.navigate(['/conveyance-master-approval'], {
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
      '/conveyance-master-approval',
      {
        page: this.page,
        sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
      }
    ]);
    this.loadAll();
  }

  ngOnInit() {
    this.conveyanceSearchMaster = new ConveyanceSearchMaster();
    this.loadAll();
    this.accountService.identity().then(account => {
      this.currentAccount = account;
    });
    this.registerChangeInConveyanceMasterApprovals();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IConveyanceMaster) {
    return item.id;
  }

  registerChangeInConveyanceMasterApprovals() {
    this.eventSubscriber = this.eventManager.subscribe('conveyanceMasterApprovalListModification', response => this.loadAll());
  }

  sort() {
    const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected paginateConveyanceMasterApprovals(data: IConveyanceMaster[], headers: HttpHeaders) {
    this.links = this.parseLinks.parse(headers.get('link'));
    this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
    this.conveyanceMasters = data;
  }

  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }
}
