import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import * as FileSaver from 'file-saver';
import { EmployeeSalaryService } from 'app/entities/employee-salary/employee-salary.service';
import { IMasterSearch, MasterSearch } from 'app/shared/model/master-search.model';
import { ISalarySearch, SalarySearch } from 'app/shared/model/salary-search.model';
import { DateTimeAdapter, OWL_DATE_TIME_FORMATS, OWL_DATE_TIME_LOCALE } from 'ng-pick-datetime';
import { MomentDateTimeAdapter } from 'ng-pick-datetime-moment';
import { MY_MOMENT_FORMATS } from 'app/stitch/manpower-budgeting/manpower-budgeting-update.component';
import * as moment from 'moment';

@Component({
  selector: 'jhi-monthly-salary',
  templateUrl: './employee-salary.component.html',
  encapsulation: ViewEncapsulation.None,
  providers: [
    // `MomentDateTimeAdapter` and `OWL_MOMENT_DATE_TIME_FORMATS` can be automatically provided by importing
    // `OwlMomentDateTimeModule` in your applications root module. We provide it at the component level
    // here, due to limitations of our example generation script.
    { provide: DateTimeAdapter, useClass: MomentDateTimeAdapter, deps: [OWL_DATE_TIME_LOCALE] },
    { provide: OWL_DATE_TIME_FORMATS, useValue: MY_MOMENT_FORMATS }
  ]
})
export class EmployeeSalaryComponent implements OnInit {
  isDownload = false;
  search: ISalarySearch;
  constructor(protected employeeSalaryService: EmployeeSalaryService) {}

  ngOnInit() {
    this.search = new SalarySearch();
    this.search.salary = 45000;
    this.search.dateTo = moment(new Date());
    this.search.dateFrom = moment(new Date());
  }

  generateReport() {
    this.isDownload = true;
    this.employeeSalaryService.downloadPdf(this.search).subscribe(
      res => {
        FileSaver.saveAs(res, 'employeeSalary.xlsx');
        this.isDownload = false;
      },
      res => {
        this.isDownload = false;
      }
    );
  }
}
