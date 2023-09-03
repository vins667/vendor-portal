import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';
import { IBuyerMaster } from 'app/shared/model/buyer-master.model';
import { AccountService } from 'app/core/auth/account.service';
import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import {BuyerMasterService} from 'app/vendorportal/buyer-master';
import {IMaster, Master} from 'app/shared/model/master.modal';
import {NgbActiveModal} from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'jhi-buyer-master-search',
  templateUrl: './buyer-master-search.component.html'
})
export class BuyerMasterSearchComponent implements OnInit, OnDestroy {
  currentAccount: any;
  buyerMasters: IBuyerMaster[];
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
  masterSearch: IMaster;

  constructor(
    protected buyerMasterService: BuyerMasterService,
    protected parseLinks: JhiParseLinks,
    protected jhiAlertService: JhiAlertService,
    protected accountService: AccountService,
    protected activatedRoute: ActivatedRoute,
    protected router: Router,
    protected eventManager: JhiEventManager,
    public activeModal: NgbActiveModal
  ) {
    this.itemsPerPage = ITEMS_PER_PAGE;
  }

  loadAll() {
    this.masterSearch.size = ITEMS_PER_PAGE;
    this.masterSearch.pageNo = 0;
    this.page = 1;
    this.buyerMasterService
      .search(this.masterSearch)
      .subscribe(
        (res: HttpResponse<IBuyerMaster[]>) => this.paginateBuyerMasters(res.body, res.headers),
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  loadAllNew() {
    this.masterSearch.size = ITEMS_PER_PAGE;
    this.masterSearch.pageNo = this.page - 1;
    // this.page = 1;
    this.buyerMasterService
      .search(this.masterSearch)
      .subscribe(
        (res: HttpResponse<IBuyerMaster[]>) => this.paginateBuyerMasters(res.body, res.headers),
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
    this.masterSearch.size = ITEMS_PER_PAGE;
    this.masterSearch.pageNo = 0;
    this.page = 0;
    this.buyerMasterService
      .search(this.masterSearch)
      .subscribe(
        (res: HttpResponse<IBuyerMaster[]>) => this.paginateBuyerMasters(res.body, res.headers),
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  transition() {
    this.loadAllNew();
  }

  clear() {
    this.page = 0;
    this.router.navigate([
      '/buyer-master',
      {
        page: this.page,
        sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
      }
    ]);
    this.loadAll();
  }

  ngOnInit() {
    this.masterSearch = new Master();
    this.loadAll();
    this.accountService.identity().then(account => {
      this.currentAccount = account;
    });
    this.registerChangeInBuyerMasters();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IBuyerMaster) {
    return item.id;
  }

  registerChangeInBuyerMasters() {
    this.eventSubscriber = this.eventManager.subscribe('buyerMasterListModification', response => this.loadAll());
  }

  sort() {
    const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected paginateBuyerMasters(data: IBuyerMaster[], headers: HttpHeaders) {
    this.links = this.parseLinks.parse(headers.get('link'));
    this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
    this.buyerMasters = data;
  }

  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }

  addData(buyerMaster) {
    this.eventManager.broadcast({ name: 'selectedBuyerList', content: buyerMaster });
    // this.activeModal.dismiss('cancel');
  }

  close() {
    this.activeModal.dismiss('cancel');
  }
}
