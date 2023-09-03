import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import * as FileSaver from 'file-saver';
import { ISalarySearch, SalarySearch } from 'app/shared/model/salary-search.model';
import { PaymentRequestReportService } from 'app/paymentrequest/payment-request-report/payment-request-report.service';
import { DateTimeAdapter, OWL_DATE_TIME_FORMATS, OWL_DATE_TIME_LOCALE } from 'ng-pick-datetime';
import { MomentDateTimeAdapter } from 'ng-pick-datetime-moment';
import { MY_MOMENT_FORMATS } from 'app/entities/mmr-master';
import { HttpResponse } from '@angular/common/http';
import { ICompany } from 'app/shared/db2/model/company.model';
import { CompanyService } from 'app/shared/db2/service/company.service';
import { DivisionService } from 'app/shared/db2/service/division.service';
import { FinbusinessunitService } from 'app/shared/db2/service/finbusinessunit.service';
import { IDivision } from 'app/shared/db2/model/division.model';
import { IFactory } from 'app/shared/model/factory.model';
import { IFinbusinessunit } from 'app/shared/db2/model/finbusinessunit.model';
import { DirectBookingSearch, IDirectBookingSearch } from 'app/shared/model/direct-booking-search.model';
import { SnotifyService } from 'ng-snotify';
import { toastConfig } from 'app/core/toast/toast-config';
@Component({
  selector: 'jhi-payment-request-report',
  templateUrl: './payment-request-report.component.html',
  encapsulation: ViewEncapsulation.None,
  providers: [
    // `MomentDateTimeAdapter` and `OWL_MOMENT_DATE_TIME_FORMATS` can be automatically provided by importing
    // `OwlMomentDateTimeModule` in your applications root module. We provide it at the component level
    // here, due to limitations of our example generation script.
    { provide: DateTimeAdapter, useClass: MomentDateTimeAdapter, deps: [OWL_DATE_TIME_LOCALE] },
    { provide: OWL_DATE_TIME_FORMATS, useValue: MY_MOMENT_FORMATS }
  ]
})
export class PaymentRequestReportComponent implements OnInit {
  isDownload = false;
  companies?: ICompany[];
  divisions?: IDivision[];
  factories?: IFactory[];
  finbusinessunits?: IFinbusinessunit[];
  search: IDirectBookingSearch;
  factory: string;

  // public fromDate = new FormControl();
  // public toDate = new FormControl();
  constructor(
    protected paymentRequestFormService: PaymentRequestReportService,
    protected companyService: CompanyService,
    protected divisionService: DivisionService,
    protected snotifyService: SnotifyService,
    protected finbusinessunitService: FinbusinessunitService
  ) {}

  ngOnInit() {
    this.companyService.query().subscribe((companies: HttpResponse<ICompany[]>) => {
      this.companies = companies.body;
    });
    this.search = new DirectBookingSearch();
    this.factory = 'ALL';
    this.search.bookingDateFrom = new Date();
    this.search.bookingDateTo = new Date();
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

  generateReport() {
    // this.search.dateFrom = this.fromDate.value;
    // this.search.dateTo = this.toDate.value;
    if (this.search.bookingDateTo && this.search.bookingDateTo) {
      this.isDownload = true;
      this.paymentRequestFormService.downloadPdf(this.search).subscribe(
        res => {
          FileSaver.saveAs(res, 'payment_request.xlsx');
          this.isDownload = false;
        },
        res => {
          this.isDownload = false;
        }
      );
    } else {
      this.snotifyService.error('Date From & Date To must not empty', '', toastConfig);
    }
  }
}
