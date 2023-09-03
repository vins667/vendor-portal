import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';

import { IVcutOperationMaster } from 'app/shared/model/vcut-operation-master.model';
import { AccountService } from 'app/core/auth/account.service';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { VcutOperationMasterService } from './vcut-operation-master.service';
import { VcutStylePlanUploadSearch } from 'app/shared/model/vcut-style-plan-upload-search.model';

@Component({
  selector: 'jhi-vcut-operation-master',
  templateUrl: './vcut-operation-master.component.html'
})
export class VcutOperationMasterComponent implements OnInit, OnDestroy {
  currentAccount: any;
  vcutStylePlanUploadSearch: VcutStylePlanUploadSearch;
  vcutOperationMasters: IVcutOperationMaster[];
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

  constructor(
    protected vcutOperationMasterService: VcutOperationMasterService,
    protected parseLinks: JhiParseLinks,
    protected jhiAlertService: JhiAlertService,
    protected accountService: AccountService,
    protected activatedRoute: ActivatedRoute,
    protected router: Router,
    protected eventManager: JhiEventManager
  ) {
    this.itemsPerPage = ITEMS_PER_PAGE;
    this.routeData = this.activatedRoute.data.subscribe(data => {
      this.page = 1;
    });
  }

  search() {
    this.vcutStylePlanUploadSearch.size = ITEMS_PER_PAGE;
    this.vcutStylePlanUploadSearch.pageNo = 0;
    this.page = 1;
    this.vcutOperationMasterService
      .queryCustom(this.vcutStylePlanUploadSearch)
      .subscribe(
        (res: HttpResponse<IVcutOperationMaster[]>) => this.paginateVcutOperationMasters(res.body, res.headers),
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  loadAllNew() {
    this.vcutStylePlanUploadSearch.size = this.itemsPerPage;
    this.vcutStylePlanUploadSearch.pageNo = this.page - 1;
    this.vcutOperationMasterService
      .queryCustom(this.vcutStylePlanUploadSearch)
      .subscribe(
        (res: HttpResponse<IVcutOperationMaster[]>) => this.paginateVcutOperationMasters(res.body, res.headers),
        (res: HttpErrorResponse) => this.onError(res.message)
      );
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
    this.router.navigate(['/vcut-operation-master'], {
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
      '/vcut-operation-master',
      {
        page: this.page,
        sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
      }
    ]);
    this.loadAll();
  }

  ngOnInit() {
    this.vcutStylePlanUploadSearch = new VcutStylePlanUploadSearch();
    this.loadAll();
    this.accountService.identity().then(account => {
      this.currentAccount = account;
    });
    this.registerChangeInVcutOperationMasters();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IVcutOperationMaster) {
    return item.id;
  }

  registerChangeInVcutOperationMasters() {
    this.eventSubscriber = this.eventManager.subscribe('vcutOperationMasterListModification', response => this.loadAll());
  }

  sort() {
    const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected paginateVcutOperationMasters(data: IVcutOperationMaster[], headers: HttpHeaders) {
    this.links = this.parseLinks.parse(headers.get('link'));
    this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
    this.vcutOperationMasters = data;
  }

  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }
}
