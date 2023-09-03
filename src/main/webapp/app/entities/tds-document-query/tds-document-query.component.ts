import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';
import { ITdsDocumentQuery } from './tds-document-query.model';
import { TdsDocumentQueryService } from './tds-document-query.service';
import { AccountService } from 'app/core/auth/account.service';
import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { ITdsDeclarationUploadSearch, TdsDeclarationUploadSearch } from 'app/shared/model/tds-declaration-upload-search.model';
import * as FileSaver from 'file-saver';
import { ITdsYearMaster } from 'app/shared/model/tds-year-master.model';
import { TdsYearMasterService } from 'app/entities/tds-year-master';

@Component({
  selector: 'jhi-tds-document-query',
  templateUrl: './tds-document-query.component.html'
})
export class TdsDocumentQueryComponent implements OnInit, OnDestroy {
  currentAccount: any;
  tdsDeclarationUploadQries: ITdsDocumentQuery[];
  tdsDeclarationUploadSearch: ITdsDeclarationUploadSearch;
  tdsYearMasters: ITdsYearMaster[];
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
    protected tdsDocumentQueryService: TdsDocumentQueryService,
    protected tdsYearMasterService: TdsYearMasterService,
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
    this.tdsDocumentQueryService
      .query(this.tdsDeclarationUploadSearch)
      .subscribe(
        (res: HttpResponse<ITdsDocumentQuery[]>) => this.paginateTdsDeclarationUploadQries(res.body, res.headers),
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  search() {
    if (this.tdsDeclarationUploadSearch.financialYear !== undefined) {
      this.tdsDeclarationUploadSearch.size = ITEMS_PER_PAGE;
      this.tdsDeclarationUploadSearch.pageNo = 0;
      this.page = 1;
      this.totalpage = 1;
      this.tdsDocumentQueryService
        .query(this.tdsDeclarationUploadSearch)
        .subscribe(
          (res: HttpResponse<ITdsDocumentQuery[]>) => this.paginateTdsDeclarationUploadQries(res.body, res.headers),
          (res: HttpErrorResponse) => this.onError(res.message)
        );
    }
  }

  downloadXlsx(): void {
    if (this.tdsDeclarationUploadSearch.financialYear !== undefined) {
      this.isDownload = true;
      this.tdsDeclarationUploadSearch.size = ITEMS_PER_PAGE;
      this.tdsDeclarationUploadSearch.pageNo = 0;
      this.page = 1;
      this.totalpage = 1;
      this.tdsDocumentQueryService.downloadXlsx(this.tdsDeclarationUploadSearch).subscribe(
        res => {
          this.isDownload = false;
          FileSaver.saveAs(res, 'tdsDeclarationQuery.xlsx');
        },
        () => {
          this.isDownload = false;
        }
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
    this.router.navigate(['/tds-document-query'], {
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
      '/tds-document-query',
      {
        page: this.page,
        sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
      }
    ]);
    this.loadAll();
  }

  ngOnInit() {
    this.tdsDeclarationUploadSearch = new TdsDeclarationUploadSearch();
    this.tdsYearMasters = [];
    this.tdsYearMasterService.query().subscribe(tdsYearMasters => {
      this.tdsYearMasters = tdsYearMasters.body;
      if (this.tdsYearMasters) {
        this.tdsDeclarationUploadSearch.financialYear = this.tdsYearMasters[0].code;
      }
      this.loadAll();
    });
    this.accountService.identity().then(account => {
      this.currentAccount = account;
    });
    this.registerChangeInTdsDeclarationUploadQries();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: ITdsDocumentQuery) {
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
  protected paginateTdsDeclarationUploadQries(data: ITdsDocumentQuery[], headers: HttpHeaders) {
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
      this.tdsDocumentQueryService.download(cardNo, year).subscribe(data => {
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
      this.tdsDocumentQueryService.downloadAllZip(this.tdsDeclarationUploadSearch).subscribe(data => {
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
      this.tdsDocumentQueryService.downloadXlsx(this.tdsDeclarationUploadSearch).subscribe(
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
