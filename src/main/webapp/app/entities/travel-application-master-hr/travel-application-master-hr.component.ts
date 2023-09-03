import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';
import { TravelApplicationMasterHrService } from './travel-application-master-hr.service';
import { ITravelApplicationMaster } from 'app/shared/model/travel-application-master.model';
import { AccountService } from 'app/core/auth/account.service';
import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { ConveyanceSearchMaster } from 'app/shared/model/conveyance-serach-master.model';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { IConveyanceMaster } from 'app/shared/model/conveyance-master.model';
import * as moment from 'moment';
@Component({
  selector: 'jhi-travel-application-master-hr',
  templateUrl: './travel-application-master-hr.component.html'
})
export class TravelApplicationMasterHrComponent implements OnInit, OnDestroy {
  currentAccount: any;
  travelApplicationMasters: ITravelApplicationMaster[];
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
    protected travelApplicationMasterHrService: TravelApplicationMasterHrService,
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

  search() {
    if (this.leaveDateFrom != null && this.leaveDateTo !== null && this.conveyanceSearchMaster.status !== null) {
      this.conveyanceSearchMaster.size = ITEMS_PER_PAGE;
      this.conveyanceSearchMaster.pageNo = 0;
      this.page = 1;
      this.conveyanceSearchMaster.conveyanceDate =
        this.leaveDateFrom != null ? moment(this.leaveDateFrom, DATE_TIME_FORMAT).startOf('day') : null;
      this.conveyanceSearchMaster.conveyanceDateTo =
        this.leaveDateTo != null ? moment(this.leaveDateTo, DATE_TIME_FORMAT).startOf('day') : null;
      this.travelApplicationMasterHrService
        .query(this.conveyanceSearchMaster)
        .subscribe(
          (res: HttpResponse<IConveyanceMaster[]>) => this.paginateTravelApplicationMasterHrs(res.body, res.headers),
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
      this.travelApplicationMasterHrService
        .query(this.conveyanceSearchMaster)
        .subscribe(
          (res: HttpResponse<IConveyanceMaster[]>) => this.paginateTravelApplicationMasterHrs(res.body, res.headers),
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
    this.router.navigate(['/travel-application-master-hr'], {
      queryParams: {
        page: this.page,
        size: this.itemsPerPage,
        sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
      }
    });
    this.loadAll();
  }

  clear() {
    this.page = 0;
    this.router.navigate([
      '/travel-application-master-hr',
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
    this.registerChangeInTravelApplicationMasterHrs();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: ITravelApplicationMaster) {
    return item.id;
  }

  registerChangeInTravelApplicationMasterHrs() {
    this.eventSubscriber = this.eventManager.subscribe('travelApplicationMasterHrListModification', response => this.loadAll());
  }

  sort() {
    const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected paginateTravelApplicationMasterHrs(data: ITravelApplicationMaster[], headers: HttpHeaders) {
    this.links = this.parseLinks.parse(headers.get('link'));
    this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
    this.travelApplicationMasters = data;
  }

  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }
}
