import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';
import { IPreviousEmploymentQry } from 'app/shared/model/previous-employment-qry.model';
import { PreviousEmploymentQryService } from './previous-employment-qry.service';
import { AccountService } from 'app/core/auth/account.service';
import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { ITdsYearMaster } from 'app/shared/model/tds-year-master.model';
import { ITdsDeclarationSearch, TdsDeclarationSearch } from 'app/shared/model/tds-declaration-search.model';
import { TdsYearMasterService } from '../tds-year-master/tds-year-master.service';

@Component({
  selector: 'jhi-previous-employment-qry',
  templateUrl: './previous-employment-qry.component.html'
})
export class PreviousEmploymentQryComponent implements OnInit, OnDestroy {
  currentAccount: any;
  previousEmploymentQries: IPreviousEmploymentQry[];
  tdsyearmasters: ITdsYearMaster[];
  currentYear: ITdsYearMaster;
  tdsDeclarationSearch: ITdsDeclarationSearch;
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
    protected previousEmploymentQryService: PreviousEmploymentQryService,
    protected parseLinks: JhiParseLinks,
    protected jhiAlertService: JhiAlertService,
    protected accountService: AccountService,
    protected activatedRoute: ActivatedRoute,
    protected router: Router,
    protected eventManager: JhiEventManager,
    protected tdsYearMasterService: TdsYearMasterService
  ) {
    this.itemsPerPage = ITEMS_PER_PAGE;
    this.routeData = this.activatedRoute.data.subscribe(data => {
      this.page = data.pagingParams.page;
      this.previousPage = data.pagingParams.page;
      this.reverse = data.pagingParams.ascending;
      this.predicate = data.pagingParams.predicate;
    });
  }

  search() {
    this.tdsDeclarationSearch.size = ITEMS_PER_PAGE;
    this.tdsDeclarationSearch.pageNo = 0;
    this.page = 0;
    this.previousEmploymentQryService
      .query(this.tdsDeclarationSearch)
      .subscribe(
        (res: HttpResponse<IPreviousEmploymentQry[]>) => this.paginatePreviousEmploymentQries(res.body, res.headers),
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  loadAll() {
    this.tdsYearMasterService.query().subscribe(
      (res: HttpResponse<ITdsYearMaster[]>) => {
        this.tdsyearmasters = res.body;
        if (this.tdsyearmasters && this.tdsyearmasters.length > 0) {
          this.tdsDeclarationSearch.year = this.tdsyearmasters[0].code;
          this.currentYear = this.tdsyearmasters[0];
          this.search();
        }
      },
      (res: HttpErrorResponse) => this.onError(res.message)
    );
  }

  loadPage(page: number) {
    if (page !== this.previousPage) {
      this.previousPage = page;
      this.transition();
    }
  }

  loadAllNew() {
    this.tdsDeclarationSearch.size = this.itemsPerPage;
    this.tdsDeclarationSearch.pageNo = this.page - 1;
    this.previousEmploymentQryService
      .query(this.tdsDeclarationSearch)
      .subscribe(
        (res: HttpResponse<IPreviousEmploymentQry[]>) => this.paginatePreviousEmploymentQries(res.body, res.headers),
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  transition() {
    this.router.navigate(['/previous-employment-qry'], {
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
      '/previous-employment-qry',
      {
        page: this.page,
        sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
      }
    ]);
    this.loadAllNew();
  }

  ngOnInit() {
    this.tdsDeclarationSearch = new TdsDeclarationSearch();
    this.loadAll();
    this.accountService.identity().then(account => {
      this.currentAccount = account;
    });
    this.registerChangeInPreviousEmploymentQries();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IPreviousEmploymentQry) {
    return item.id;
  }

  registerChangeInPreviousEmploymentQries() {
    this.eventSubscriber = this.eventManager.subscribe('previousEmploymentQryListModification', response => this.loadAll());
  }

  sort() {
    const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected paginatePreviousEmploymentQries(data: IPreviousEmploymentQry[], headers: HttpHeaders) {
    this.links = this.parseLinks.parse(headers.get('link'));
    this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
    this.queryCount = this.totalItems;
    this.previousEmploymentQries = data;
  }

  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }
}
