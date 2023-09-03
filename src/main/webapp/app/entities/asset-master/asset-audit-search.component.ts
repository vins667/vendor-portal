import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';
import { AssetAuditDetails, IAssetAuditDetails } from 'app/shared/model/asset-audit-details.model';
import { AccountService } from 'app/core/auth/account.service';
import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { IAssetAuditSearch } from 'app/shared/model/asset-audit-search.model';
import { AssetAuditDetailsService } from 'app/entities/asset-audit-details';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'jhi-asset-audit-search',
  templateUrl: './asset-audit-search.component.html'
})
export class AssetAuditSearchComponent implements OnInit, OnDestroy {
  currentAccount: any;
  assetAuditSearch: IAssetAuditSearch;
  assetAuditDetails: IAssetAuditDetails[];
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
    protected assetAuditDetailsService: AssetAuditDetailsService,
    protected parseLinks: JhiParseLinks,
    protected jhiAlertService: JhiAlertService,
    protected accountService: AccountService,
    protected activatedRoute: ActivatedRoute,
    protected router: Router,
    protected eventManager: JhiEventManager,
    public activeModal: NgbActiveModal
  ) {
    this.itemsPerPage = ITEMS_PER_PAGE;
    this.routeData = this.activatedRoute.data.subscribe(data => {
      this.page = data.pagingParams.page;
      this.previousPage = data.pagingParams.page;
      this.reverse = data.pagingParams.ascending;
      this.predicate = data.pagingParams.predicate;
    });
  }

  close() {
    this.activeModal.dismiss('cancel');
  }

  loadAll() {
    this.search();
  }

  loadPage(page: number) {
    if (page !== this.previousPage) {
      this.previousPage = page;
      this.transition();
    }
  }

  transition() {
    this.loadAllNew();
  }

  loadAllNew() {
    this.assetAuditSearch.size = this.itemsPerPage;
    this.assetAuditSearch.pageNo = this.page - 1;
    this.assetAuditDetailsService
      .queryCustom(this.assetAuditSearch)
      .subscribe(
        (res: HttpResponse<IAssetAuditDetails[]>) => this.paginateAssetAuditDetails(res.body, res.headers),
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  clear() {
    this.loadAll();
  }

  search() {
    this.assetAuditSearch.size = ITEMS_PER_PAGE;
    this.assetAuditSearch.pageNo = 0;
    this.page = 0;
    this.assetAuditDetailsService
      .queryCustom(this.assetAuditSearch)
      .subscribe(
        (res: HttpResponse<IAssetAuditDetails[]>) => this.paginateAssetAuditDetails(res.body, res.headers),
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  ngOnInit() {
    this.assetAuditSearch = new AssetAuditDetails();
    this.loadAll();
    this.accountService.identity().then(account => {
      this.currentAccount = account;
    });
    this.registerChangeInAssetAuditDetails();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IAssetAuditDetails) {
    return item.id;
  }

  registerChangeInAssetAuditDetails() {
    this.eventSubscriber = this.eventManager.subscribe('assetAuditDetailsListModification', response => this.loadAll());
  }

  sort() {
    const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected paginateAssetAuditDetails(data: IAssetAuditDetails[], headers: HttpHeaders) {
    this.links = this.parseLinks.parse(headers.get('link'));
    this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
    this.queryCount = this.totalItems;
    this.assetAuditDetails = data;
  }

  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }

  addData(assetAuditDetails) {
    this.eventManager.broadcast({ name: 'selectedAssetAuditDetails', content: assetAuditDetails });
    this.activeModal.dismiss('cancel');
  }
}
