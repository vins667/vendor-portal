import { Component, OnDestroy, OnInit, ViewEncapsulation } from '@angular/core';
import { DateTimeAdapter, OWL_DATE_TIME_FORMATS, OWL_DATE_TIME_LOCALE } from 'ng-pick-datetime';
import { MomentDateTimeAdapter } from 'ng-pick-datetime-moment';
import { MY_MOMENT_FORMATS } from 'app/vcut/vcut-style-plan-upload/vcut-style-plan-upload.component';
import { Subscription } from 'rxjs';
import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { JhiAlertService, JhiEventManager, JhiParseLinks } from 'ng-jhipster';
import { AccountService } from 'app/core/auth/account.service';
import { ActivatedRoute, Router } from '@angular/router';
import * as moment from 'moment';
import { DATE_FORMAT, DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { BankRealizationCertificateUploadSearch } from 'app/entities/bank-realisation-certificate-upload/bank-realization-certificate-upload-search.model';
import { IBankRealisationCertificateUpload } from 'app/entities/bank-realisation-certificate-upload/bank-realisation-certificate-upload.model';
import { BankRealisationCertificateUploadService } from 'app/entities/bank-realisation-certificate-upload/bank-realisation-certificate-upload.service';

export const MY_MOMENTS_FORMAT = {
  parseInput: 'DD-MM-YYYY LT',
  fullPickerInput: 'DD-MM-YYYY LT',
  datePickerInput: 'DD-MM-YYYY',
  timePickerInput: 'HH:mm',
  monthYearLabel: 'MMM YYYY',
  dateA11yLabel: 'LL',
  monthYearA11yLabel: 'MMMM YYYY'
};

@Component({
  selector: 'jhi-bank-realisation-certificate-upload',
  templateUrl: './bank-realisation-certificate-upload.component.html',
  encapsulation: ViewEncapsulation.None,
  providers: [
    // `MomentDateTimeAdapter` and `OWL_MOMENT_DATE_TIME_FORMATS` can be automatically provided by importing
    // `OwlMomentDateTimeModule` in your applications root module. We provide it at the component level
    // here, due to limitations of our example generation script.
    { provide: DateTimeAdapter, useClass: MomentDateTimeAdapter, deps: [OWL_DATE_TIME_LOCALE] },
    { provide: OWL_DATE_TIME_FORMATS, useValue: MY_MOMENT_FORMATS }
  ]
})
export class BankRealisationCertificateUploadComponent implements OnInit, OnDestroy {
  currentAccount: any;
  bankRealisationCertificatUploads: IBankRealisationCertificateUpload[];
  eventSubscriber: Subscription;
  search: BankRealizationCertificateUploadSearch;
  brcDateFrom: any;
  brcDateTo: any;
  totalItems = 0;
  itemsPerPage = ITEMS_PER_PAGE;
  page!: number;
  predicate!: string;
  ascending!: boolean;
  ngbPaginationPage = 1;

  constructor(
    protected bankRealisationCertificateService: BankRealisationCertificateUploadService,
    protected parseLinks: JhiParseLinks,
    protected jhiAlertService: JhiAlertService,
    protected accountService: AccountService,
    protected activatedRoute: ActivatedRoute,
    protected router: Router,
    protected eventManager: JhiEventManager
  ) {
    this.search = new BankRealizationCertificateUploadSearch();
  }

  loadPage(page?: number): void {
    if (this.brcDateFrom !== null && this.brcDateTo !== null) {
      const pageToLoad: number = page || this.page;
      this.search.size = this.itemsPerPage;
      this.search.pageNo = pageToLoad - 1;
      const sort = this.sort();
      if (sort && sort.length > 0) {
        this.search.sort = sort[0].split(',')[0];
        this.search.sortType = sort[0].split(',')[1];
      } else {
        this.search.sort = 'id';
        this.search.sortType = 'desc';
      }
      this.bankRealisationCertificateService
        .queryCustom(this.search)
        .subscribe(
          (res: HttpResponse<IBankRealisationCertificateUpload[]>) => this.onSuccess(res.body, res.headers, pageToLoad),
          () => this.onError()
        );
    }
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IBankRealisationCertificateUpload): number {
    return item.id;
  }

  ngOnInit(): void {
    const dt = new Date();
    const month = dt.getMonth();
    const year = dt.getFullYear();
    const FirstDay = new Date(year, month, 1);
    const LastDay = new Date(year, month + 1, 0);
    this.brcDateFrom = FirstDay;
    this.brcDateFrom.setHours(0, 0, 0, 0);
    this.brcDateTo = LastDay;
    this.brcDateTo.setHours(0, 0, 0, 0);
    this.activatedRoute.data.subscribe(data => {
      this.page = data.pagingParams.page;
      this.ascending = data.pagingParams.ascending;
      this.predicate = data.pagingParams.predicate;
      this.ngbPaginationPage = data.pagingParams.page;
      this.loadPage();
    });
    this.registerChangeInBankRealizationCertificateUpload();
  }

  registerChangeInBankRealizationCertificateUpload() {
    this.eventSubscriber = this.eventManager.subscribe('bankRealisationCertificateUploadListModification', response => this.loadPage());
  }

  sort() {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected onSuccess(data: IBankRealisationCertificateUpload[] | null, headers: HttpHeaders, page: number): void {
    this.totalItems = Number(headers.get('X-Total-Count'));
    this.page = page;
    this.bankRealisationCertificatUploads = data || [];
  }

  protected onError(): void {
    this.ngbPaginationPage = this.page;
  }
}
