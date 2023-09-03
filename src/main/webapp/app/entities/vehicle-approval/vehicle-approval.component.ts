import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Observable, Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';
import { IVehicleMaster } from 'app/shared/model/vehicle-master.model';
import { AccountService } from 'app/core/auth/account.service';
import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { VehicleApprovalService } from './vehicle-approval.service';
import { ILeaveMaster } from 'app/shared/model/leave-master.model';
import { ILeaveSearch, LeaveSearch } from 'app/shared/model/leave-search.model';
import { SnotifyService } from 'ng-snotify';
import * as moment from 'moment';
import { DateTimeAdapter, OWL_DATE_TIME_FORMATS, OWL_DATE_TIME_LOCALE } from 'ng-pick-datetime';
import { MomentDateTimeAdapter } from 'ng-pick-datetime-moment';
import { MY_MOMENT_FORMATS } from 'app/entities/leave-master';
import { IMessage } from 'app/shared/model/message.model';
import { toastConfig } from 'app/core/toast/toast-config';

@Component({
  selector: 'jhi-vehicle-approval',
  templateUrl: './vehicle-approval.component.html',
  providers: [
    // `MomentDateTimeAdapter` and `OWL_MOMENT_DATE_TIME_FORMATS` can be automatically provided by importing
    // `OwlMomentDateTimeModule` in your applications root module. We provide it at the component level
    // here, due to limitations of our example generation script.
    { provide: DateTimeAdapter, useClass: MomentDateTimeAdapter, deps: [OWL_DATE_TIME_LOCALE] },
    { provide: OWL_DATE_TIME_FORMATS, useValue: MY_MOMENT_FORMATS }
  ]
})
export class VehicleApprovalComponent implements OnInit, OnDestroy {
  currentAccount: any;
  vehicleMasters: IVehicleMaster[];
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
  vehicleSaveMasters: IVehicleMaster[];
  leavesearch: ILeaveSearch;
  leaveDateFrom: any;
  leaveDateTo: any;

  constructor(
    protected vehicleMasterService: VehicleApprovalService,
    protected parseLinks: JhiParseLinks,
    protected jhiAlertService: JhiAlertService,
    protected accountService: AccountService,
    protected activatedRoute: ActivatedRoute,
    protected router: Router,
    protected eventManager: JhiEventManager,
    protected snotifyService: SnotifyService
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
    this.vehicleMasterService
      .queryHod(this.leavesearch)
      .subscribe(
        (res: HttpResponse<ILeaveMaster[]>) => this.paginateVehicleMasters(res.body, res.headers),
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
    if (this.leavesearch.leaveDateFrom !== null && this.leavesearch.leaveDateTo !== null && this.leavesearch.leaveStatus !== null) {
      let size = this.totalItems - this.page * ITEMS_PER_PAGE;
      if (size < 0) {
        size = ITEMS_PER_PAGE + size;
      } else {
        size = ITEMS_PER_PAGE;
      }
      this.leavesearch.leaveDateFrom = this.leaveDateFrom != null ? moment(this.leaveDateFrom, DATE_TIME_FORMAT).startOf('day') : null;
      this.leavesearch.leaveDateTo = this.leaveDateTo != null ? moment(this.leaveDateTo, DATE_TIME_FORMAT).startOf('day') : null;
      this.leavesearch.size = size;
      this.leavesearch.pageNo = this.page - 1;
      this.vehicleMasterService
        .queryHod(this.leavesearch)
        .subscribe(
          (res: HttpResponse<ILeaveMaster[]>) => this.paginateVehicleMasters(res.body, res.headers),
          (res: HttpErrorResponse) => this.onError(res.message)
        );
    }
    this.loadAll();
  }

  clear() {
    this.page = 0;
    this.router.navigate([
      '/vehicle-master',
      {
        page: this.page,
        sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
      }
    ]);
    this.loadAll();
  }

  search() {
    if (this.leaveDateFrom != null && this.leaveDateTo !== null && this.leavesearch.leaveStatus !== null) {
      this.leavesearch.leaveDateFrom = this.leaveDateFrom != null ? moment(this.leaveDateFrom, DATE_TIME_FORMAT).startOf('day') : null;
      this.leavesearch.leaveDateTo = this.leaveDateTo != null ? moment(this.leaveDateTo, DATE_TIME_FORMAT).startOf('day') : null;
      this.leavesearch.size = ITEMS_PER_PAGE;
      this.leavesearch.pageNo = 0;
      this.page = 0;
      this.vehicleMasterService
        .queryHod(this.leavesearch)
        .subscribe(
          (res: HttpResponse<ILeaveMaster[]>) => this.paginateVehicleMasters(res.body, res.headers),
          (res: HttpErrorResponse) => this.onError(res.message)
        );
    }
  }

  pushValue(vehicleMaster) {
    if (this.vehicleSaveMasters && this.vehicleSaveMasters.length > 0) {
      this.vehicleSaveMasters.forEach((leaveMasterLoop, index) => {
        if (leaveMasterLoop.id === vehicleMaster.id) {
          this.vehicleSaveMasters.splice(index, 1);
        }
      });
    }
    if (vehicleMaster.flag !== 'E') {
      if (this.vehicleSaveMasters === undefined || this.vehicleSaveMasters === null) {
        this.vehicleSaveMasters = [];
      }
      this.vehicleSaveMasters.push(vehicleMaster);
    }
  }

  save() {
    if (this.vehicleSaveMasters && this.vehicleSaveMasters.length > 0) {
      this.subscribeToSaveResponse(this.vehicleMasterService.update(this.vehicleSaveMasters));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMessage>>) {
    result.subscribe((res: HttpResponse<IMessage>) => this.onSaveSuccess(res), (res: HttpErrorResponse) => this.onSaveError(res.headers));
  }

  protected onSaveSuccess(res: HttpResponse<IMessage>) {
    this.loadAll();
    if (res.body.type === 'success') {
      this.snotifyService.success(res.body.msg, '', toastConfig);
    } else {
      this.snotifyService.error(res.body.msg, '', toastConfig);
    }
  }

  protected onSaveError(res: HttpHeaders) {
    this.loadAll();
    this.snotifyService.error(res.get('X-vamaniportalApp-error'), '', toastConfig);
  }

  ngOnInit() {
    this.leavesearch = new LeaveSearch();
    this.loadAll();
    this.accountService.identity().then(account => {
      this.currentAccount = account;
    });
    this.registerChangeInVehicleMasters();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IVehicleMaster) {
    return item.id;
  }

  registerChangeInVehicleMasters() {
    this.eventSubscriber = this.eventManager.subscribe('vehicleMasterListModification', response => this.loadAll());
  }

  sort() {
    const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected paginateVehicleMasters(data: IVehicleMaster[], headers: HttpHeaders) {
    this.links = this.parseLinks.parse(headers.get('link'));
    this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
    this.queryCount = this.totalItems;
    this.vehicleMasters = data;
  }

  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }
}
