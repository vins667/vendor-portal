import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';
import { ITdsDeclarationUploadQry } from 'app/shared/model/tds-declaration-upload-qry.model';
import { TdsDeclarationUploadQryService } from './tds-declaration-upload-qry.service';
import { AccountService } from 'app/core/auth/account.service';
import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { ITdsDeclarationUploadSearch, TdsDeclarationUploadSearch } from 'app/shared/model/tds-declaration-upload-search.model';
import * as FileSaver from 'file-saver';
import { ITdsYearMaster, TdsYearMaster } from 'app/shared/model/tds-year-master.model';

@Component({
  selector: 'jhi-tds-declaration-upload-qry',
  templateUrl: './tds-declaration-upload-qry.component.html'
})
export class TdsDeclarationUploadQryComponent implements OnInit, OnDestroy {
  currentAccount: any;
  tdsDeclarationUploadQries: ITdsDeclarationUploadQry[];
  tdsDeclarationUploadSearch: ITdsDeclarationUploadSearch;
  tdsYearMasters: ITdsYearMaster;
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
  totalpage: any;
  isDownload = false;

  constructor(
    protected tdsDeclarationUploadQryService: TdsDeclarationUploadQryService,
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
      this.totalpage = 1;
    });
  }

  loadAllNew() {
    this.tdsDeclarationUploadSearch.size = this.itemsPerPage;
    this.tdsDeclarationUploadSearch.pageNo = this.page - 1;
    this.totalpage = this.page;
    this.tdsDeclarationUploadQryService
      .query(this.tdsDeclarationUploadSearch)
      .subscribe(
        (res: HttpResponse<ITdsDeclarationUploadQry[]>) => this.paginateTdsDeclarationUploadQries(res.body, res.headers),
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  search() {
    if (this.tdsDeclarationUploadSearch.financialYear !== undefined) {
      this.tdsDeclarationUploadSearch.size = ITEMS_PER_PAGE;
      this.tdsDeclarationUploadSearch.pageNo = 0;
      this.page = 1;
      this.totalpage = 1;
      this.tdsDeclarationUploadQryService
        .query(this.tdsDeclarationUploadSearch)
        .subscribe(
          (res: HttpResponse<ITdsDeclarationUploadQry[]>) => this.paginateTdsDeclarationUploadQries(res.body, res.headers),
          (res: HttpErrorResponse) => this.onError(res.message)
        );
    }
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
    this.router.navigate(['/tds-declaration-upload-qry'], {
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
      '/tds-declaration-upload-qry',
      {
        page: this.page,
        sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
      }
    ]);
    this.loadAll();
  }

  ngOnInit() {
    this.tdsDeclarationUploadSearch = new TdsDeclarationUploadSearch();
    this.tdsDeclarationUploadQryService.active().subscribe(tdsYearMaster => {
      this.tdsYearMasters = tdsYearMaster.body;
      this.tdsDeclarationUploadSearch.financialYear = this.tdsYearMasters.code;
      this.loadAll();
    });
    if (this.tdsYearMasters === undefined) {
      this.tdsYearMasters = new TdsYearMaster();
    }
    this.accountService.identity().then(account => {
      this.currentAccount = account;
    });
    this.registerChangeInTdsDeclarationUploadQries();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: ITdsDeclarationUploadQry) {
    return item.id;
  }

  registerChangeInTdsDeclarationUploadQries() {
    this.eventSubscriber = this.eventManager.subscribe('tdsDeclarationUploadQryListModification', response => this.loadAll());
  }

  sort() {
    const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
    if (this.predicate !== 'card_no') {
      result.push('card_no');
    }
    return result;
  }
  protected paginateTdsDeclarationUploadQries(data: ITdsDeclarationUploadQry[], headers: HttpHeaders) {
    this.links = this.parseLinks.parse(headers.get('link'));
    this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
    this.tdsDeclarationUploadQries = data;
  }

  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }

  download(tdsDeclarationUploadQry): any {
    if (tdsDeclarationUploadQry && tdsDeclarationUploadQry.cardNo !== undefined) {
      this.isDownload = true;
      const cardNo = tdsDeclarationUploadQry.cardNo;
      const year = tdsDeclarationUploadQry.financialYear;
      this.tdsDeclarationUploadQryService.download(cardNo, year).subscribe(data => {
        const blob = new Blob([data], {
          type: 'application/zip'
        });
        FileSaver.saveAs(blob, tdsDeclarationUploadQry.cardNo + '.zip');
        this.isDownload = false;
      });
    }
  }

  downloadAll() {
    if (this.tdsDeclarationUploadSearch && this.tdsDeclarationUploadSearch.financialYear !== undefined) {
      this.isDownload = true;
      this.tdsDeclarationUploadQryService.downloadAllZip(this.tdsDeclarationUploadSearch).subscribe(data => {
        const blob = new Blob([data], {
          type: 'application/zip'
        });
        FileSaver.saveAs(blob, this.tdsDeclarationUploadSearch.financialYear + '.zip');
        this.isDownload = false;
      });
    }
  }

  downloadReport() {
    if (this.tdsDeclarationUploadSearch && this.tdsDeclarationUploadSearch.financialYear !== undefined) {
      this.isDownload = true;
      this.tdsDeclarationUploadQryService.downloadXlsx(this.tdsDeclarationUploadSearch).subscribe(
        res => {
          FileSaver.saveAs(res, 'TdsDeclarationUploadReport.xlsx');
          this.isDownload = false;
        },
        res => {
          this.isDownload = false;
        }
      );
    }
  }
}
