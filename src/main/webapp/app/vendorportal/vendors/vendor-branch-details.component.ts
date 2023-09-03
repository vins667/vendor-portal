import { Component, OnInit, OnDestroy, Input } from '@angular/core';
import { HttpErrorResponse, HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';
import { IVendorBranchDetails } from 'app/shared/model/vendor-branch-details.model';
import { VendorBranchDetailsService, VendorBranchDetailsUpdateComponent } from 'app/vendorportal/vendor-branch-details';
import { AccountService } from 'app/core/auth/account.service';
import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { NgbModal, NgbModalConfig } from '@ng-bootstrap/ng-bootstrap';
import { BranchSearch, IBranchSearch } from 'app/shared/model/branch-search.model';

@Component({
  selector: 'jhi-vendor-branch-details',
  templateUrl: './vendor-branch-details.component.html'
})
export class VendorBranchDetailsComponent implements OnInit, OnDestroy {
  currentAccount: any;
  vendorBranchDetails: IVendorBranchDetails[];
  branchSearch: IBranchSearch;
  @Input() approvalStatus: string;
  @Input() vendorId: number;
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

  constructor(
    protected vendorBranchDetailsService: VendorBranchDetailsService,
    protected parseLinks: JhiParseLinks,
    protected jhiAlertService: JhiAlertService,
    protected accountService: AccountService,
    protected activatedRoute: ActivatedRoute,
    protected router: Router,
    protected eventManager: JhiEventManager,
    private modalService: NgbModal,
    config: NgbModalConfig
  ) {
    this.branchSearch = new BranchSearch();
    this.itemsPerPage = ITEMS_PER_PAGE;
    this.branchSearch.size = this.itemsPerPage;
    this.page = 1;
    this.branchSearch.pageNo = 0;
  }

  loadAll() {
    this.branchSearch.pageNo = this.page - 1;
    this.branchSearch.vendorId = this.vendorId;
    if (this.vendorId) {
      this.vendorBranchDetailsService
        .query(this.branchSearch)
        .subscribe(
          (res: HttpResponse<IVendorBranchDetails[]>) => this.paginateVendorBranchDetails(res.body, res.headers),
          (res: HttpErrorResponse) => this.onError(res.message)
        );
    }
  }

  loadPage(page: number) {
    if (page !== this.previousPage) {
      this.previousPage = page;
      this.transition();
    }
  }

  transition() {
    this.loadAll();
  }

  clear() {
    this.page = 0;
    this.loadAll();
  }

  ngOnInit() {
    this.loadAll();
    this.accountService.identity().then(account => {
      this.currentAccount = account;
    });
    this.registerChangeInVendorBranchDetails();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IVendorBranchDetails) {
    return item.id;
  }

  registerChangeInVendorBranchDetails() {
    this.eventSubscriber = this.eventManager.subscribe('vendorBranchDetailsListModification', response => this.loadAll());
  }

  sort() {
    const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected paginateVendorBranchDetails(data: IVendorBranchDetails[], headers: HttpHeaders) {
    this.links = this.parseLinks.parse(headers.get('link'));
    this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
    this.page = 1;
    // this.queryCount = this.totalItems;
    this.vendorBranchDetails = data;
  }

  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }

  open(id?: number) {
    if (id) {
      this.vendorBranchDetailsService.find(id).subscribe(vendorBranchDetails => {
        const modalRef = this.modalService.open(VendorBranchDetailsUpdateComponent, { windowClass: 'xlModal' });
        modalRef.componentInstance.vendorBranchDetails = vendorBranchDetails.body;
      });
    } else {
      this.modalService.open(VendorBranchDetailsUpdateComponent, { windowClass: 'xlModal' });
    }
  }
}
