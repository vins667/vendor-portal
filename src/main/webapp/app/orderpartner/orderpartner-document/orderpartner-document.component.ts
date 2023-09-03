import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IOrderpartnerDocument } from './orderpartner-document.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { OrderpartnerDocumentService } from './orderpartner-document.service';
import { SnotifyService } from 'ng-snotify';
import { IMasterSearch, MasterSearch } from 'app/shared/model/master-search.model';
import * as FileSaver from 'file-saver';

@Component({
  selector: 'jhi-orderpartner-document',
  templateUrl: './orderpartner-document.component.html'
})
export class OrderpartnerDocumentComponent implements OnInit, OnDestroy {
  isDownload?: boolean;
  orderpartnerDocuments?: IOrderpartnerDocument[];
  eventSubscriber?: Subscription;
  totalItems = 0;
  itemsPerPage = ITEMS_PER_PAGE;
  page!: number;
  predicate!: string;
  ascending!: boolean;
  ngbPaginationPage = 1;
  checked = false;
  submit = false;
  search?: IMasterSearch;

  constructor(
    protected orderpartnerDocumentService: OrderpartnerDocumentService,
    protected activatedRoute: ActivatedRoute,
    protected router: Router,
    protected modalService: NgbModal,
    protected eventManager: JhiEventManager,
    protected snotifyService: SnotifyService
  ) {
    this.search = new MasterSearch();
    this.search.companyCode = '2';
  }

  loadPage(page?: number): void {
    const pageToLoad: number = page || this.page;

    this.search.size = this.itemsPerPage;
    this.search.pageNo = pageToLoad - 1;
    this.orderpartnerDocumentService
      .queryFilter(this.search)
      .subscribe((res: HttpResponse<IOrderpartnerDocument[]>) => this.onSuccess(res.body, res.headers, pageToLoad), () => this.onError());
  }
  ngOnInit(): void {
    this.activatedRoute.data.subscribe(data => {
      this.page = data.pagingParams.page;
      this.ascending = data.pagingParams.ascending;
      this.predicate = data.pagingParams.predicate;
      this.ngbPaginationPage = data.pagingParams.page;
      this.loadPage();
    });
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  generateXLS() {
    this.isDownload = true;

    this.orderpartnerDocumentService.downloadXlsx(this.search.companyCode).subscribe(
      res => {
        FileSaver.saveAs(res, 'orderPartners.xlsx');
        this.isDownload = false;
      },
      res => {
        this.isDownload = false;
      }
    );
  }
  protected onSuccess(data: IOrderpartnerDocument[] | null, headers: HttpHeaders, page: number): void {
    this.totalItems = Number(headers.get('X-Total-Count'));
    this.page = page;
    this.orderpartnerDocuments = data || [];
  }

  protected onError(): void {
    this.ngbPaginationPage = this.page;
  }
}
