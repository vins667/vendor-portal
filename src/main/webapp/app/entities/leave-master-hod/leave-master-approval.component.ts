import { Component, OnInit, OnDestroy, ViewEncapsulation } from '@angular/core';
import { HttpErrorResponse, HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Observable, Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';
import { ILeaveMaster } from 'app/shared/model/leave-master.model';
import { AccountService } from 'app/core/auth/account.service';
import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { LeaveMasterHodService } from './leave-master-hod.service';
import { IMessage } from 'app/shared/model/message.model';
import { SnotifyService } from 'ng-snotify';
import { ILeaveSearch, LeaveSearch } from 'app/shared/model/leave-search.model';
import * as moment from 'moment';
import { DateTimeAdapter, OWL_DATE_TIME_FORMATS, OWL_DATE_TIME_LOCALE } from 'ng-pick-datetime';
import { MomentDateTimeAdapter } from 'ng-pick-datetime-moment';
import { MY_MOMENT_FORMATS } from 'app/entities/leave-master/leave-master-update.component';
import { toastConfig } from 'app/core/toast/toast-config';
import { ModalDismissReasons, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { LeaveMasterRemarksDetailsUpdateComponent } from './leave-master-remarks-details-update.component';
import { MapLocationComponent } from 'app/entities/leave-master-hod/map-location.component';
import { IMobileAttendance } from 'app/shared/model/mobile-attendance.model';

@Component({
  selector: 'jhi-leave-master',
  templateUrl: './leave-master-approval.component.html',
  encapsulation: ViewEncapsulation.None,
  providers: [
    // `MomentDateTimeAdapter` and `OWL_MOMENT_DATE_TIME_FORMATS` can be automatically provided by importing
    // `OwlMomentDateTimeModule` in your applications root module. We provide it at the component level
    // here, due to limitations of our example generation script.
    { provide: DateTimeAdapter, useClass: MomentDateTimeAdapter, deps: [OWL_DATE_TIME_LOCALE] },
    { provide: OWL_DATE_TIME_FORMATS, useValue: MY_MOMENT_FORMATS }
  ]
})
export class LeaveMasterApprovalComponent implements OnInit, OnDestroy {
  currentAccount: any;
  leaveMasters: ILeaveMaster[];
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
  leaveSaveMasters: ILeaveMaster[];
  leavesearch: ILeaveSearch;
  leaveDateFrom: any;
  leaveDateTo: any;
  closeResult: string;
  protected ngbModalRef: NgbModalRef;

  constructor(
    protected leaveMasterService: LeaveMasterHodService,
    protected parseLinks: JhiParseLinks,
    protected jhiAlertService: JhiAlertService,
    protected accountService: AccountService,
    protected activatedRoute: ActivatedRoute,
    protected router: Router,
    protected snotifyService: SnotifyService,
    protected eventManager: JhiEventManager,
    private modalService: NgbModal
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
    this.leaveMasterService
      .queryHod(this.leavesearch)
      .subscribe(
        (res: HttpResponse<ILeaveMaster[]>) => this.paginateLeaveMasters(res.body, res.headers),
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
      this.leaveMasterService
        .queryHod(this.leavesearch)
        .subscribe(
          (res: HttpResponse<ILeaveMaster[]>) => this.paginateLeaveMasters(res.body, res.headers),
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
      this.leaveMasterService
        .queryHod(this.leavesearch)
        .subscribe(
          (res: HttpResponse<ILeaveMaster[]>) => this.paginateLeaveMasters(res.body, res.headers),
          (res: HttpErrorResponse) => this.onError(res.message)
        );
    }
  }

  transition() {
    this.router.navigate(['/leave-master-hod/approval'], {
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
      '/leave-master',
      {
        page: this.page,
        sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
      }
    ]);
    this.loadAll();
  }

  pushValue(leaveMaster) {
    this.modalService.dismissAll('Cross click');
    if (this.leaveSaveMasters && this.leaveSaveMasters.length > 0) {
      this.leaveSaveMasters.forEach((leaveMasterLoop, index) => {
        if (leaveMasterLoop.id === leaveMaster.id) {
          this.leaveSaveMasters.splice(index, 1);
        }
      });
    }
    if (leaveMaster.flag !== 'E') {
      if (this.leaveSaveMasters === undefined || this.leaveSaveMasters === null) {
        this.leaveSaveMasters = [];
      }
      this.leaveSaveMasters.push(leaveMaster);
    }
  }

  save() {
    if (this.leaveSaveMasters && this.leaveSaveMasters.length > 0) {
      this.subscribeToSaveResponse(this.leaveMasterService.update(this.leaveSaveMasters));
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
    this.registerChangeInLeaveMasters();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: ILeaveMaster) {
    return item.id;
  }

  sort() {
    const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected paginateLeaveMasters(data: ILeaveMaster[], headers: HttpHeaders) {
    this.links = this.parseLinks.parse(headers.get('link'));
    this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
    this.queryCount = this.totalItems;
    this.leaveMasters = data;
  }

  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }

  open(leaveMaster: ILeaveMaster) {
    this.leaveMasterService.workFlow(leaveMaster.id).subscribe(leaveMasterRemarksDetailsBean => {
      this.ngbModalRef = this.modalService.open(LeaveMasterRemarksDetailsUpdateComponent as Component, {
        size: 'lg',
        backdrop: 'static'
      });
      this.ngbModalRef.componentInstance.leaveMasterRemarksDetails = leaveMasterRemarksDetailsBean.body;
    });
  }

  private getDismissReason(reason: any): string {
    if (reason === ModalDismissReasons.ESC) {
      return 'by pressing ESC';
    } else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
      return 'by clicking on a backdrop';
    } else {
      return `with: ${reason}`;
    }
  }

  registerChangeInLeaveMasters() {
    this.eventSubscriber = this.eventManager.subscribe('leaveMasterListModificationHod', response => this.loadAll());
  }

  maps(mobileAttendance: IMobileAttendance) {
    this.ngbModalRef = this.modalService.open(MapLocationComponent as Component, {
      size: 'lg',
      backdrop: 'static'
    });
    this.ngbModalRef.componentInstance.mobileAttendance = mobileAttendance;
  }
}
