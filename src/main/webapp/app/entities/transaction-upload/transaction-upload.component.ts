import { Component, OnDestroy, OnInit } from '@angular/core';
import { ITransactionUpload } from 'app/entities/transaction-upload/transaction-upload.model';
import { Subscription } from 'rxjs';
import { TransactionUploadSearch } from 'app/entities/transaction-upload/transaction-upload-search';
import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { JhiAlertService, JhiEventManager, JhiParseLinks } from 'ng-jhipster';
import { ActivatedRoute, Router } from '@angular/router';
import { TransactionUploadService } from 'app/entities/transaction-upload/transaction-upload.service';
import { HttpErrorResponse, HttpHeaders, HttpResponse } from '@angular/common/http';
import { filter, map } from 'rxjs/operators';

@Component({
  selector: 'jhi-transaction-upload',
  templateUrl: './transaction-upload.component.html'
})
export class TransactionUploadComponent implements OnInit, OnDestroy {
  transactionUploads: ITransactionUpload[];
  eventSubscriber: Subscription;
  search: TransactionUploadSearch;
  totalItems = 0;
  itemsPerPage = ITEMS_PER_PAGE;
  page!: number;
  predicate!: string;
  ascending!: boolean;
  ngbPaginationPage = 1;

  constructor(
    protected transactionUploadService: TransactionUploadService,
    protected parseLinks: JhiParseLinks,
    protected jhiAlertService: JhiAlertService,
    protected activatedRoute: ActivatedRoute,
    protected router: Router,
    protected eventManager: JhiEventManager
  ) {
    this.search = new TransactionUploadSearch();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  loadPage(page?: number): void {
    const pageToLoad: number = page || this.page;
    this.search.size = this.itemsPerPage;
    this.search.pageNo = pageToLoad - 1;
    const sort = this.sort();
    this.search.sort = sort[0].split(',')[0];
    this.search.sortType = sort[0].split(',')[1];
    this.transactionUploadService
      .query()
      .pipe(
        filter((res: HttpResponse<ITransactionUpload[]>) => res.ok),
        map((res: HttpResponse<ITransactionUpload[]>) => res.body)
      )
      .subscribe(
        (res: ITransactionUpload[]) => {
          this.transactionUploads = res;
        },
        (res: HttpErrorResponse) => this.onError()
      );
    this.transactionUploadService
      .queryCustom(this.search)
      .subscribe((res: HttpResponse<ITransactionUpload[]>) => this.onSuccess(res.body, res.headers, pageToLoad), () => this.onError());
  }

  // loadPage(page?: number): void {
  //   const pageToLoad: number = page || this.page;
  //   this.search.size = this.itemsPerPage;
  //   this.search.pageNo = pageToLoad - 1;
  //   const sort = this.sort();
  //   this.search.sort = sort[0].split(',')[0];
  //   this.search.sortType = sort[0].split(',')[1];
  //   this.transactionUploadService
  //     .queryCustom(this.search)
  //     .subscribe(
  //       (res: HttpResponse<ITransactionUpload[]>) => this.onSuccess(res.body, res.headers, pageToLoad),
  //       () => this.onError()
  //     );
  //
  // }

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(data => {
      this.page = data.pagingParams.page;
      this.ascending = data.pagingParams.ascending;
      this.predicate = data.pagingParams.predicate;
      this.ngbPaginationPage = data.pagingParams.page;
      this.loadPage();
    });
    this.registerChangeInTransactionUpload();
  }

  registerChangeInTransactionUpload() {
    this.eventSubscriber = this.eventManager.subscribe('transactionUploadListModification', response => this.loadPage());
  }

  sort() {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected onSuccess(data: ITransactionUpload[] | null, headers: HttpHeaders, page: number): void {
    this.totalItems = Number(headers.get('X-Total-Count'));
    this.page = page;
    this.transactionUploads = data || [];
  }

  protected onError(): void {
    this.ngbPaginationPage = this.page;
  }

  trackId(index: number, item: ITransactionUpload): number {
    return item.id;
  }
}
