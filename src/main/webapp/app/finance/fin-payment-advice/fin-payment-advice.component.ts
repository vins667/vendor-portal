import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Observable, Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IViewDifindocumentpaymentadvice, ViewDifindocumentpaymentadvice } from './view-difindocumentpaymentadvice.model';

import { ADVICE_ITEMS_PER_PAGE, ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { FinPaymentAdviceService } from './fin-payment-advice.service';
import { IPaymentadviceSearch } from './paymentadvice-search.model';
import { PopupComponent } from 'app/shared/popup/popup.component';
import { toastConfig } from 'app/core/toast/toast-config';
import { SnotifyService } from 'ng-snotify';
import * as FileSaver from 'file-saver';

@Component({
  selector: 'jhi-fin-payment-advice',
  templateUrl: './fin-payment-advice.component.html'
})
export class FinPaymentAdviceComponent implements OnInit, OnDestroy {
  search?: IPaymentadviceSearch;
  allowPliesAll = false;
  viewDifindocumentpaymentadvices?: IViewDifindocumentpaymentadvice[];
  eventSubscriber?: Subscription;
  totalItems = 0;
  itemsPerPage = ADVICE_ITEMS_PER_PAGE;
  page!: number;
  predicate!: string;
  ascending!: boolean;
  ngbPaginationPage = 1;
  isDownload = false;

  constructor(
    protected finPaymentAdviceService: FinPaymentAdviceService,
    protected activatedRoute: ActivatedRoute,
    protected router: Router,
    protected eventManager: JhiEventManager,
    protected snotifyService: SnotifyService,
    protected modalService: NgbModal
  ) {
    this.search = new ViewDifindocumentpaymentadvice();
    this.search.dateType = 'P';
    this.search.status = 'A';
  }

  loadPage(page?: number): void {
    const pageToLoad: number = page || this.page;

    this.search.size = this.itemsPerPage;
    this.search.pageNo = pageToLoad - 1;
    const sort = this.sort();
    if (sort && sort.length > 0) {
      this.search.sort = sort[0].split(',')[0];
      this.search.sortType = sort[0].split(',')[1];
    } else {
      this.search.sort = 'slCode';
      this.search.sortType = 'asc';
    }
    this.isDownload = true;
    this.finPaymentAdviceService
      .queryFilter(this.search)
      .subscribe(
        (res: HttpResponse<IViewDifindocumentpaymentadvice[]>) => this.onSuccess(res.body, res.headers, pageToLoad),
        () => this.onError()
      );
  }

  download(page?: number): void {
    const pageToLoad: number = page || this.page;

    this.search.size = this.itemsPerPage;
    this.search.pageNo = pageToLoad - 1;
    const sort = this.sort();
    if (sort && sort.length > 0) {
      this.search.sort = sort[0].split(',')[0];
      this.search.sortType = sort[0].split(',')[1];
    } else {
      this.search.sort = 'slCode';
      this.search.sortType = 'asc';
    }
    this.isDownload = true;
    this.finPaymentAdviceService.downloadFilter(this.search).subscribe(
      res => {
        this.isDownload = false;
        FileSaver.saveAs(res, 'paymentAdvice.xlsx');
      },
      () => {
        this.onError();
        this.isDownload = false;
      }
    );
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

  allowPliesChange(): void {
    if (this.allowPliesAll && this.allowPliesAll === true) {
      this.viewDifindocumentpaymentadvices.forEach(finPaymentAdvice => {
        if (finPaymentAdvice.emailaddress && !finPaymentAdvice.advicesent) {
          finPaymentAdvice.flag = true;
        }
      });
    } else {
      this.viewDifindocumentpaymentadvices.forEach(finPaymentAdvice => {
        if (finPaymentAdvice.emailaddress && !finPaymentAdvice.advicesent) {
          finPaymentAdvice.flag = false;
        }
      });
    }
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  sort(): string[] {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected onSuccess(data: IViewDifindocumentpaymentadvice[] | null, headers: HttpHeaders, page: number): void {
    this.totalItems = Number(headers.get('X-Total-Count'));
    this.page = page;
    this.isDownload = false;
    this.viewDifindocumentpaymentadvices = data || [];
    this.viewDifindocumentpaymentadvices.forEach(viewDifindocumentpaymentadvice => {
      if (viewDifindocumentpaymentadvice.advicesent) {
        viewDifindocumentpaymentadvice.flag = viewDifindocumentpaymentadvice.advicesent;
      } else {
        viewDifindocumentpaymentadvice.flag = false;
      }
      if (!viewDifindocumentpaymentadvice.utrnumber) {
        viewDifindocumentpaymentadvice.advicesent = true;
      }
    });
  }

  downloadReport(viewDifindocumentpaymentadvice: IViewDifindocumentpaymentadvice): void {
    this.isDownload = true;
    this.finPaymentAdviceService
      .downloadPDF(
        viewDifindocumentpaymentadvice.id.companycode,
        viewDifindocumentpaymentadvice.id.businessunitcode,
        String(viewDifindocumentpaymentadvice.id.financialyearcode),
        viewDifindocumentpaymentadvice.id.code
      )
      .subscribe(res => {
        this.isDownload = false;
        // FileSaver.saveAs(res, 'PurchaseOrder.pdf');
        const file = new Blob([res], { type: 'application/pdf' });
        const fileURL = window.URL.createObjectURL(file);
        const modalRef = this.modalService.open(PopupComponent as Component, {
          size: 'lg',
          backdrop: 'static',
          windowClass: 'xlModal'
        });
        modalRef.componentInstance.content = fileURL;
      });
  }

  save() {
    this.isDownload = true;
    this.subscribeToSaveResponse(this.finPaymentAdviceService.save(this.viewDifindocumentpaymentadvices));
  }

  resend(viewDifindocumentpaymentadvice?: IViewDifindocumentpaymentadvice): void {
    const viewDifindocumentpaymentadvicesNew = [];
    viewDifindocumentpaymentadvicesNew.push(viewDifindocumentpaymentadvice);
    this.isDownload = true;
    this.subscribeToSaveResponse(this.finPaymentAdviceService.resend(viewDifindocumentpaymentadvicesNew));
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IViewDifindocumentpaymentadvice[]>>) {
    result.subscribe(res => this.onSaveSuccess(res.body), () => this.onSaveError());
  }

  protected onSaveSuccess(viewDifindocumentpaymentadvices: IViewDifindocumentpaymentadvice[]) {
    this.isDownload = false;
    this.snotifyService.success('Save successfully', '', toastConfig);
    this.loadPage();
  }

  protected onSaveError(): void {
    this.isDownload = false;
  }

  protected onError(): void {
    this.isDownload = false;
    this.ngbPaginationPage = this.page;
  }
}
