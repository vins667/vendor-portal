import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';
import { IIgnoreSoftwareMaster } from 'app/shared/model/ignore-software-master.model';
import { AccountService } from 'app/core/auth/account.service';
import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { IgnoreSoftwareMasterService } from './ignore-software-master.service';
import { IIgnoreSoftwareSearchMaster, IgnoreSoftwareSearchMaster } from 'app/shared/model/ignore-software-search-master.model';

@Component({
  selector: 'jhi-ignore-software-master',
  templateUrl: './ignore-software-master.component.html'
})
export class IgnoreSoftwareMasterComponent implements OnInit, OnDestroy {
  currentAccount: any;
  ignoreSoftwareSearchMasters: IIgnoreSoftwareSearchMaster;
  ignoreSoftwareMasters: IIgnoreSoftwareMaster[];
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
    protected ignoreSoftwareMasterService: IgnoreSoftwareMasterService,
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
      this.predicate = 'id';
    });
  }

  search() {
    if (this.ignoreSoftwareSearchMasters.swName !== null || this.ignoreSoftwareSearchMasters.swPublisher !== null) {
      this.ignoreSoftwareSearchMasters.size = ITEMS_PER_PAGE;
      this.ignoreSoftwareSearchMasters.pageNo = 0;
      this.page = 0;
      this.ignoreSoftwareMasterService
        .queryCustom(this.ignoreSoftwareSearchMasters)
        .subscribe(
          (res: HttpResponse<IIgnoreSoftwareMaster[]>) => this.paginateIgnoreSoftwareMasters(res.body, res.headers),
          (res: HttpErrorResponse) => this.onError(res.message)
        );
    }
  }

  loadAllNew() {
    this.ignoreSoftwareSearchMasters.size = this.itemsPerPage;
    this.ignoreSoftwareSearchMasters.pageNo = this.page - 1;
    this.ignoreSoftwareMasterService
      .queryCustom(this.ignoreSoftwareSearchMasters)
      .subscribe(
        (res: HttpResponse<IIgnoreSoftwareMaster[]>) => this.paginateIgnoreSoftwareMasters(res.body, res.headers),
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
    this.router.navigate(['/ignore-software-master'], {
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
      '/ignore-software-master',
      {
        page: this.page,
        sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
      }
    ]);
    this.loadAll();
  }

  ngOnInit() {
    this.ignoreSoftwareSearchMasters = new IgnoreSoftwareSearchMaster();
    this.loadAll();
    this.accountService.identity().then(account => {
      this.currentAccount = account;
    });
    this.registerChangeInIgnoreSoftwareMasters();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IIgnoreSoftwareMaster) {
    return item.id;
  }

  registerChangeInIgnoreSoftwareMasters() {
    this.eventSubscriber = this.eventManager.subscribe('ignoreSoftwareMasterListModification', response => this.loadAll());
  }

  sort() {
    const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected paginateIgnoreSoftwareMasters(data: IIgnoreSoftwareMaster[], headers: HttpHeaders) {
    this.links = this.parseLinks.parse(headers.get('link'));
    this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
    this.queryCount = this.totalItems;
    this.ignoreSoftwareMasters = data;
  }

  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }
}
