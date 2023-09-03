import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';
import { IKnitCreationMaster } from 'app/shared/model/knit-creation-master.model';
import { AccountService } from 'app/core/auth/account.service';
import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { KnitCreationMasterService } from './knit-creation-master.service';
import { IMaster, Master } from 'app/shared/model/master.modal';

@Component({
  selector: 'jhi-knit-creation-master',
  templateUrl: './knit-creation-master.component.html'
})
export class KnitCreationMasterComponent implements OnInit, OnDestroy {
  currentAccount: any;
  knitCreationMasters: IKnitCreationMaster[];
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
    protected knitCreationMasterService: KnitCreationMasterService,
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

  loadAll() {
    this.masterSearch.size = ITEMS_PER_PAGE;
    this.masterSearch.pageNo = 0;
    this.page = 0;
    this.knitCreationMasterService
      .queryCustom(this.masterSearch)
      .subscribe(
        (res: HttpResponse<IKnitCreationMaster[]>) => this.paginateKnitCreationMasters(res.body, res.headers),
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  loadAllNew() {
    this.masterSearch.size = ITEMS_PER_PAGE;
    this.masterSearch.pageNo = this.page - 1;
    this.knitCreationMasterService
      .queryCustom(this.masterSearch)
      .subscribe(
        (res: HttpResponse<IKnitCreationMaster[]>) => this.paginateKnitCreationMasters(res.body, res.headers),
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
    this.knitCreationMasterService
      .queryCustom(this.masterSearch)
      .subscribe(
        (res: HttpResponse<IKnitCreationMaster[]>) => this.paginateKnitCreationMasters(res.body, res.headers),
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  transition() {
    this.router.navigate(['/knit-creation-master'], {
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
      '/knit-creation-master',
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
    this.registerChangeInKnitCreationMasters();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IKnitCreationMaster) {
    return item.id;
  }

  registerChangeInKnitCreationMasters() {
    this.eventSubscriber = this.eventManager.subscribe('knitCreationMasterListModification', response => this.loadAll());
  }

  sort() {
    const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected paginateKnitCreationMasters(data: IKnitCreationMaster[], headers: HttpHeaders) {
    this.links = this.parseLinks.parse(headers.get('link'));
    this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
    this.knitCreationMasters = data;
  }

  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }
}
