import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';
import { IFabricContentMaster } from 'app/shared/model/fabric-content-master.model';
import { AccountService } from 'app/core/auth/account.service';
import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { FabricContentMasterService } from 'app/fabriccoding/fabric-content-master';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { IMaster, Master } from 'app/shared/model/master.modal';

@Component({
  selector: 'jhi-fabric-content-master',
  templateUrl: './fabric-content-search.component.html'
})
export class FabricContentSearchComponent implements OnInit, OnDestroy {
  currentAccount: any;
  fabricContentMasters: IFabricContentMaster[];
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
  masterSearch: IMaster;

  constructor(
    protected fabricContentMasterService: FabricContentMasterService,
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

  loadAll() {
    this.masterSearch.size = ITEMS_PER_PAGE;
    this.masterSearch.pageNo = 0;
    this.page = 1;
    this.fabricContentMasterService
      .search(this.masterSearch)
      .subscribe(
        (res: HttpResponse<IFabricContentMaster[]>) => this.paginateFabricContentMasters(res.body, res.headers),
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  loadAllNew() {
    this.masterSearch.size = ITEMS_PER_PAGE;
    this.masterSearch.pageNo = this.page - 1;
    // this.page = 1;
    this.fabricContentMasterService
      .search(this.masterSearch)
      .subscribe(
        (res: HttpResponse<IFabricContentMaster[]>) => this.paginateFabricContentMasters(res.body, res.headers),
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
    this.fabricContentMasterService
      .search(this.masterSearch)
      .subscribe(
        (res: HttpResponse<IFabricContentMaster[]>) => this.paginateFabricContentMasters(res.body, res.headers),
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  transition() {
    this.loadAllNew();
  }

  clear() {
    this.page = 0;
    this.router.navigate([
      '/fabric-content-master',
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
    this.registerChangeInFabricContentMasters();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IFabricContentMaster) {
    return item.id;
  }

  registerChangeInFabricContentMasters() {
    this.eventSubscriber = this.eventManager.subscribe('fabricContentMasterListModification', response => this.loadAll());
  }

  sort() {
    const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected paginateFabricContentMasters(data: IFabricContentMaster[], headers: HttpHeaders) {
    this.links = this.parseLinks.parse(headers.get('link'));
    this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
    this.queryCount = this.totalItems;
    this.fabricContentMasters = data;
  }

  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }

  addData(fabricContentMaster) {
    this.eventManager.broadcast({ name: 'selectedFabricContentDetails', content: fabricContentMaster });
    this.activeModal.dismiss('cancel');
  }

  close() {
    this.activeModal.dismiss('cancel');
  }
}
