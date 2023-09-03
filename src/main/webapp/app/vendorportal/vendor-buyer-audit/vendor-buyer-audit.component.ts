import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';

import { IVendorBuyerAudit } from 'app/shared/model/vendor-buyer-audit.model';
import { AccountService } from 'app/core/auth/account.service';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { VendorBuyerAuditService } from './vendor-buyer-audit.service';
import { IVendorMaster } from 'app/shared/model/vendor-master.model';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { VendorBuyerMasterSearchComponent } from './vendor-buyer-master-search.component';
import { IBuyerMaster } from 'app/shared/model/buyer-master.model';
import { IMaster } from 'app/shared/model/master.modal';

@Component({
  selector: 'jhi-vendor-buyer-audit',
  templateUrl: './vendor-buyer-audit.component.html'
})
export class VendorBuyerAuditComponent implements OnInit, OnDestroy {
  currentAccount: any;
  vendorBuyerAudits: IVendorBuyerAudit[];
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
  vendorMaster: IVendorMaster;
  buyerMaster: IBuyerMaster;
  buyerMasters: IBuyerMaster[];
  protected ngbModalRef: NgbModalRef;
  auditMaster: IMaster;
  auditMasters: IMaster[];

  constructor(
    protected vendorBuyerAuditService: VendorBuyerAuditService,
    protected parseLinks: JhiParseLinks,
    protected jhiAlertService: JhiAlertService,
    protected accountService: AccountService,
    protected activatedRoute: ActivatedRoute,
    protected router: Router,
    protected modalService: NgbModal,
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

  searchContent() {
    this.ngbModalRef = this.modalService.open(VendorBuyerMasterSearchComponent as Component, {
      size: 'lg',
      backdrop: 'static',
      windowClass: 'xlModal'
    });
  }

  searchAudits() {
    this.auditMasters = [];
    this.auditMaster = undefined;
    if (this.buyerMaster && this.buyerMaster.buyerCode) {
      this.vendorBuyerAuditService.findByAudits(this.buyerMaster.buyerCode).subscribe((res: HttpResponse<IMaster[]>) => {
        this.auditMasters = res.body;
      });
    }
  }

  loadAll() {
    this.vendorBuyerAuditService
      .query({
        page: this.page - 1,
        size: this.itemsPerPage,
        sort: this.sort()
      })
      .subscribe(
        (res: HttpResponse<IVendorBuyerAudit[]>) => this.paginateVendorBuyerAudits(res.body, res.headers),
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
    this.router.navigate(['/vendor-buyer-audit'], {
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
      '/vendor-buyer-audit',
      {
        page: this.page,
        sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
      }
    ]);
    this.loadAll();
  }

  ngOnInit() {
    this.loadAll();
    this.registerChangeInSelectedVendorMasters();
    this.accountService.identity().then(account => {
      this.currentAccount = account;
    });
    this.registerChangeInVendorBuyerAudits();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IVendorBuyerAudit) {
    return item.id;
  }

  registerChangeInVendorBuyerAudits() {
    this.eventSubscriber = this.eventManager.subscribe('vendorBuyerAuditListModification', response => this.loadAll());
  }

  sort() {
    const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected paginateVendorBuyerAudits(data: IVendorBuyerAudit[], headers: HttpHeaders) {
    this.links = this.parseLinks.parse(headers.get('link'));
    this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
    this.vendorBuyerAudits = data;
  }

  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }

  registerChangeInSelectedVendorMasters() {
    this.eventManager.subscribe('selectedVendorBuyerMasterBuyerLink', message => {
      const vendorMasters = message.content;
      this.vendorMaster = vendorMasters;
      this.buyerMasters = [];
      this.buyerMaster = undefined;
      if (this.vendorMaster) {
        this.vendorBuyerAuditService.findByVendor(this.vendorMaster.code).subscribe(buyerMasters => {
          this.buyerMasters = buyerMasters.body;
        });
      }
    });
  }

  routeNew() {
    const url = '/vendor-buyer-audit/new';
    this.router.navigate([url], {
      queryParams: { buyerCode: this.buyerMaster.buyerCode, vendorCode: this.vendorMaster.code, auditCode: this.auditMaster.id },
      skipLocationChange: true
    });
  }
}
