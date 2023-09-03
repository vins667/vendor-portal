import { Component, OnDestroy, OnInit } from '@angular/core';
import { IPaymentRequestForm } from 'app/paymentrequest/payment-request-form/payment-request-form.model';
import { Subscription } from 'rxjs';
import { PaymentRequestFinanceService } from './payment-request-finance.service';
import { ActivatedRoute, Router } from '@angular/router';
import { JhiEventManager } from 'ng-jhipster';
import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { DirectBookingSearch, IDirectBookingSearch } from 'app/shared/model/direct-booking-search.model';
import { ICompany } from 'app/shared/db2/model/company.model';
import { IDivision } from 'app/shared/db2/model/division.model';
import { IFactory } from 'app/shared/model/factory.model';
import { IFinbusinessunit } from 'app/shared/db2/model/finbusinessunit.model';
import { IDirectBookingEntry } from 'app/shared/model/direct-booking-entry.model';
import { CompanyService } from 'app/shared/db2/service/company.service';
import { DivisionService } from 'app/shared/db2/service/division.service';
import { FinbusinessunitService } from 'app/shared/db2/service/finbusinessunit.service';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { PaymentRequestFormDeleteDialogComponent } from 'app/paymentrequest/payment-request-form/payment-request-form-delete-dialog.component';
@Component({
  selector: 'jhi-payment-request-finance',
  templateUrl: './payment-request-finance.component.html'
})
export class PaymentRequestFinanceComponent implements OnInit, OnDestroy {
  companies?: ICompany[];
  divisions?: IDivision[];
  factories?: IFactory[];
  finbusinessunits?: IFinbusinessunit[];
  search?: IDirectBookingSearch;
  paymentRequestForms: IPaymentRequestForm[];
  eventSubscriber?: Subscription;
  totalItems = 0;
  itemsPerPage = ITEMS_PER_PAGE;
  page!: number;
  predicate!: string;
  ascending!: boolean;
  ngbPaginationPage = 1;
  isDownload = false;

  constructor(
    protected paymentRequestFormService: PaymentRequestFinanceService,
    protected activatedRoute: ActivatedRoute,
    protected router: Router,
    protected eventManager: JhiEventManager,
    protected companyService: CompanyService,
    protected divisionService: DivisionService,
    protected finbusinessunitService: FinbusinessunitService,
    protected modalService: NgbModal
  ) {
    this.search = new DirectBookingSearch();
    this.search.flag = 'A';
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

    this.paymentRequestFormService
      .queryFilter(this.search)
      .subscribe((res: HttpResponse<IDirectBookingEntry[]>) => this.onSuccess(res.body, res.headers, pageToLoad), () => this.onError());
  }

  ngOnInit(): void {
    this.registerChangeInPaymentRequestForms();
    this.companyService.query().subscribe((companies: HttpResponse<ICompany[]>) => {
      this.companies = companies.body;
    });
    this.activatedRoute.data.subscribe(data => {
      this.page = data.pagingParams.page;
      this.ascending = data.pagingParams.ascending;
      this.predicate = data.pagingParams.predicate;
      this.ngbPaginationPage = data.pagingParams.page;
      this.loadPage();
    });
  }

  fetchDivision(): void {
    if (this.search.company) {
      this.divisionService.query(this.search.company).subscribe((divisions: HttpResponse<IDivision[]>) => {
        this.divisions = divisions.body;
      });
    } else {
      this.divisions = [];
    }
  }

  fetchBusinessUnit(): void {
    if (this.search.company) {
      this.finbusinessunitService.query(this.search.company).subscribe((businessunits: HttpResponse<IFinbusinessunit[]>) => {
        this.finbusinessunits = businessunits.body;
      });
    } else {
      this.finbusinessunits = [];
    }
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IDirectBookingEntry): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInPaymentRequestForms() {
    this.eventSubscriber = this.eventManager.subscribe('paymentRequestFormListModification', response => this.loadPage(0));
  }

  delete(paymentRequestForm: IPaymentRequestForm): void {
    const modalRef = this.modalService.open(PaymentRequestFormDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.paymentRequestForm = paymentRequestForm;
  }

  sort(): string[] {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected onSuccess(data: IPaymentRequestForm[] | null, headers: HttpHeaders, page: number): void {
    this.totalItems = Number(headers.get('X-Total-Count'));
    this.page = page;
    this.router.navigate(['/payment-request-finance'], {
      queryParams: {
        page: this.page,
        size: this.itemsPerPage,
        sort: this.predicate + ',' + (this.ascending ? 'asc' : 'desc')
      }
    });
    this.paymentRequestForms = data || [];
  }

  protected onError(): void {
    this.ngbPaginationPage = this.page;
  }
}
