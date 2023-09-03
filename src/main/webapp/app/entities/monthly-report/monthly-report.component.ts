import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import * as FileSaver from 'file-saver';
import { MonthlyReportService } from './monthly-report.service';
import { ISalarySearch, SalarySearch } from 'app/shared/model/salary-search.model';
import { DateTimeAdapter, OWL_DATE_TIME_FORMATS, OWL_DATE_TIME_LOCALE, OwlDateTimeComponent, OwlDateTimeFormats } from 'ng-pick-datetime';
import { MomentDateTimeAdapter } from 'ng-pick-datetime-moment';
import { FormControl } from '@angular/forms';
import { Moment } from 'moment';
import * as _moment from 'moment';
import { IFactoryMaster } from 'app/shared/model/factory-master.model';
import { FactoryMasterService } from 'app/entities/factory-master';
const moment = (_moment as any).default ? (_moment as any).default : _moment;
export const MY_MOMENT_DATE_TIME_FORMATS: OwlDateTimeFormats = {
  parseInput: 'MM/YYYY',
  fullPickerInput: 'l LT',
  datePickerInput: 'MM/YYYY',
  timePickerInput: 'LT',
  monthYearLabel: 'MMM YYYY',
  dateA11yLabel: 'LL',
  monthYearA11yLabel: 'MMMM YYYY'
};
@Component({
  selector: 'jhi-monthly-report-salary',
  templateUrl: './monthly-report.component.html',
  encapsulation: ViewEncapsulation.None,
  providers: [
    // `MomentDateTimeAdapter` and `OWL_MOMENT_DATE_TIME_FORMATS` can be automatically provided by importing
    // `OwlMomentDateTimeModule` in your applications root module. We provide it at the component level
    // here, due to limitations of our example generation script.
    { provide: DateTimeAdapter, useClass: MomentDateTimeAdapter, deps: [OWL_DATE_TIME_LOCALE] },
    { provide: OWL_DATE_TIME_FORMATS, useValue: MY_MOMENT_DATE_TIME_FORMATS }
  ]
})
export class MonthlyReportComponent implements OnInit {
  isDownload = false;
  salarySearch: ISalarySearch;
  factoryMasters: IFactoryMaster[];
  factory: string;

  public fromDate = new FormControl(moment());
  public toDate = new FormControl(moment());
  constructor(protected monthlyReportService: MonthlyReportService, protected factoryMasterService: FactoryMasterService) {}

  chosenYearHandler1(normalizedYear: Moment) {
    const ctrlValue = this.fromDate.value;
    ctrlValue.year(normalizedYear.year());
    this.fromDate.setValue(ctrlValue);
  }

  chosenMonthHandler1(normalizedMonth: Moment, datepicker: OwlDateTimeComponent<Moment>) {
    const ctrlValue = this.fromDate.value;
    ctrlValue.month(normalizedMonth.month());
    this.fromDate.setValue(ctrlValue);
    datepicker.close();
  }

  chosenYearHandler2(normalizedYear: Moment) {
    const ctrlValue = this.toDate.value;
    ctrlValue.year(normalizedYear.year());
    this.toDate.setValue(ctrlValue);
  }

  chosenMonthHandler2(normalizedMonth: Moment, datepicker: OwlDateTimeComponent<Moment>) {
    const ctrlValue = this.toDate.value;
    ctrlValue.month(normalizedMonth.month());
    this.toDate.setValue(ctrlValue);
    datepicker.close();
  }

  ngOnInit() {
    this.salarySearch = new SalarySearch();
    this.factory = 'ALL';
    this.factoryMasterService.query().subscribe(factoryMasters => {
      this.factoryMasters = factoryMasters.body;
    });
  }

  generateReport() {
    this.salarySearch.dateFrom = this.fromDate.value;
    this.salarySearch.dateTo = this.toDate.value;
    this.salarySearch.factory = this.factory;
    this.isDownload = true;
    this.monthlyReportService.downloadPdf(this.salarySearch).subscribe(
      res => {
        FileSaver.saveAs(res, 'salary_summary.xlsx');
        this.isDownload = false;
      },
      res => {
        this.isDownload = false;
      }
    );
  }
}
