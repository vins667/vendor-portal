import { Component, OnInit } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IPaymentRequestForward } from '../payment-request-forward.model';

import { PaymentRequestForwardService } from '../service/payment-request-forward.service';
import { PaymentRequestForwardDeleteDialogComponent } from '../delete/payment-request-forward-delete-dialog.component';
import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { IMasterSearch, MasterSearch } from 'app/shared/model/master-search.model';
import { JhiEventManager } from 'ng-jhipster';
import { IDirectBookingEntry } from 'app/shared/model/direct-booking-entry.model';

@Component({
  selector: 'jhi-payment-request-forward',
  templateUrl: './payment-request-forward.component.html'
})
export class PaymentRequestForwardComponent implements OnInit {
  paymentRequestForwards?: IPaymentRequestForward[];
  search?: IMasterSearch;
  eventSubscriber?: Subscription;
  totalItems = 0;
  itemsPerPage = ITEMS_PER_PAGE;
  page!: number;
  predicate!: string;
  ascending!: boolean;
  ngbPaginationPage = 1;
  isDownload = false;

  constructor(
    protected paymentRequestForwardService: PaymentRequestForwardService,
    protected activatedRoute: ActivatedRoute,
    protected router: Router,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {
    this.search = new MasterSearch();
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
      this.search.sort = 'id';
      this.search.sortType = 'asc';
    }

    this.paymentRequestForwardService
      .queryFilter(this.search)
      .subscribe((res: HttpResponse<IPaymentRequestForward[]>) => this.onSuccess(res.body, res.headers, pageToLoad), () => this.onError());
  }

  ngOnInit(): void {
    this.registerChangeInPaymentRequestForms();
    this.activatedRoute.data.subscribe(data => {
      this.page = data.pagingParams.page;
      this.ascending = data.pagingParams.ascending;
      this.predicate = data.pagingParams.predicate;
      this.ngbPaginationPage = data.pagingParams.page;
      this.loadPage();
    });
  }

  registerChangeInPaymentRequestForms() {
    this.eventSubscriber = this.eventManager.subscribe('paymentRequestForwardListModification', response => this.loadPage(0));
  }

  delete(paymentRequestForward: IPaymentRequestForward): void {
    const modalRef = this.modalService.open(PaymentRequestForwardDeleteDialogComponent, {
      size: 'lg',
      backdrop: 'static'
    });
    modalRef.componentInstance.paymentRequestForward = paymentRequestForward;
  }

  sort(): string[] {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected onSuccess(data: IPaymentRequestForward[] | null, headers: HttpHeaders, page: number): void {
    this.totalItems = Number(headers.get('X-Total-Count'));
    this.page = page;
    this.router.navigate(['/payment-request-forward'], {
      queryParams: {
        page: this.page,
        size: this.itemsPerPage,
        sort: this.predicate + ',' + (this.ascending ? 'asc' : 'desc')
      }
    });
    this.paymentRequestForwards = data || [];
  }

  protected onError(): void {
    this.ngbPaginationPage = this.page;
  }
}
