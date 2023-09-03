import { Component, OnInit } from '@angular/core';
import * as FileSaver from 'file-saver';
import { ConveyanceReportService } from './conveyance-report.service';
import { ISalarySearch, SalarySearch } from 'app/shared/model/salary-search.model';
@Component({
  selector: 'jhi-conveyance-report',
  templateUrl: './conveyance-report.component.html'
})
export class ConveyanceReportComponent implements OnInit {
  isDownload = false;
  salarySearch: ISalarySearch;

  constructor(protected conveyanceReportService: ConveyanceReportService) {}

  ngOnInit() {
    this.salarySearch = new SalarySearch();
  }

  generateReport() {
    this.isDownload = true;
    this.conveyanceReportService.downloadPdf(this.salarySearch).subscribe(
      res => {
        FileSaver.saveAs(res, 'ConveyanceReport.xlsx');
        this.isDownload = false;
      },
      res => {
        this.isDownload = false;
      }
    );
  }
}
