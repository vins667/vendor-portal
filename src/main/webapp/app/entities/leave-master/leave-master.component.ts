import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';
import { ILeaveMaster } from 'app/shared/model/leave-master.model';
import { AccountService } from 'app/core/auth/account.service';
import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { LeaveMasterService } from './leave-master.service';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { LeaveRemarksDetailsUpdateComponent } from './leave-master-remarks-details-update.component';
import { IMobileAttendance } from 'app/shared/model/mobile-attendance.model';
import { LeaveMobileMapLocationComponent } from './leave-mobile-map-location.component';

@Component({
  selector: 'jhi-leave-master',
  templateUrl: './leave-master.component.html'
})
export class LeaveMasterComponent implements OnInit, OnDestroy {
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
  protected ngbModalRef: NgbModalRef;

  constructor(
    protected leaveMasterService: LeaveMasterService,
    protected parseLinks: JhiParseLinks,
    protected jhiAlertService: JhiAlertService,
    protected accountService: AccountService,
    protected modalService: NgbModal,
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
    this.leaveMasterService
      .query({
        page: this.page - 1,
        size: this.itemsPerPage,
        sort: this.sort()
      })
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

  transition() {
    this.router.navigate(['/leave-master'], {
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
      '/leave-master',
      {
        page: this.page,
        sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
      }
    ]);
    this.loadAll();
  }

  ngOnInit() {
    this.loadAll();
    this.accountService.identity().then(account => {
      this.currentAccount = account;
    });
    this.registerChangeInLeaveMasters();
    this.registerChangeInLeaveMastersApp();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: ILeaveMaster) {
    return item.id;
  }

  registerChangeInLeaveMasters() {
    this.eventSubscriber = this.eventManager.subscribe('leaveMasterListModification', response => this.loadAll());
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
      this.ngbModalRef = this.modalService.open(LeaveRemarksDetailsUpdateComponent as Component, {
        size: 'lg',
        backdrop: 'static'
      });
      this.ngbModalRef.componentInstance.leaveMasterRemarksDetails = leaveMasterRemarksDetailsBean.body;
    });
  }

  registerChangeInLeaveMastersApp() {
    this.eventSubscriber = this.eventManager.subscribe('leaveMasterStatusModification', response => this.loadAll());
  }

  maps(mobileAttendance: IMobileAttendance) {
    this.ngbModalRef = this.modalService.open(LeaveMobileMapLocationComponent as Component, {
      size: 'lg',
      backdrop: 'static'
    });
    this.ngbModalRef.componentInstance.mobileAttendance = mobileAttendance;
  }
}
