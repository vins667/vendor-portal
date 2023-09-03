import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import * as FileSaver from 'file-saver';
import * as _moment from 'moment';
import { IStrengthReportBean, StrengthReportBean } from 'app/shared/model/strength-report.model';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { StrengthReportService } from './strength-report.service';
import { DateTimeAdapter, OWL_DATE_TIME_LOCALE, OWL_DATE_TIME_FORMATS } from 'ng-pick-datetime';
import { MomentDateTimeAdapter } from 'ng-pick-datetime-moment';
import { ISectionMat } from 'app/shared/model/section-mat.model';
import { ISubcomp } from 'app/shared/model/subcomp.model';
const moment = (_moment as any).default ? (_moment as any).default : _moment;
export const MY_MOMENT_FORMATS = {
  parseInput: 'DD-MM-YYYY LT',
  fullPickerInput: 'DD-MM-YYYY LT',
  datePickerInput: 'DD-MM-YYYY',
  timePickerInput: 'HH:mm',
  monthYearLabel: 'MMM YYYY',
  dateA11yLabel: 'LL',
  monthYearA11yLabel: 'MMMM YYYY'
};
@Component({
  selector: 'jhi-strength-report',
  templateUrl: './strength-report.component.html',
  encapsulation: ViewEncapsulation.None,
  providers: [
    // `MomentDateTimeAdapter` and `OWL_MOMENT_DATE_TIME_FORMATS` can be automatically provided by importing
    // `OwlMomentDateTimeModule` in your applications root module. We provide it at the component level
    // here, due to limitations of our example generation script.
    { provide: DateTimeAdapter, useClass: MomentDateTimeAdapter, deps: [OWL_DATE_TIME_LOCALE] },
    { provide: OWL_DATE_TIME_FORMATS, useValue: MY_MOMENT_FORMATS }
  ]
})
export class StrengthReportComponent implements OnInit {
  strengthReport: IStrengthReportBean;
  reportType: String;
  dateFrom: any;
  isDownload = false;
  sectionMats: ISectionMat[];
  subcomps: ISubcomp[];

  constructor(protected strengthReportService: StrengthReportService) {}

  ngOnInit() {
    this.strengthReport = new StrengthReportBean();
    this.dateFrom = new Date();
    this.dateFrom.setHours(0, 0, 0, 0);
  }

  fetchList() {
    if (this.strengthReport.factory !== undefined && this.strengthReport.factory !== null) {
      this.strengthReportService.findSubComp(this.strengthReport.factory).subscribe(subcomps => {
        this.subcomps = subcomps.body;
        this.strengthReport.subComp = this.subcomps[0].id.subCode + '';
      });
      if (
        this.strengthReport.factory === '102' ||
        this.strengthReport.factory === '103' ||
        this.strengthReport.factory === '104' ||
        this.strengthReport.factory === '105'
      ) {
        this.strengthReportService.findLine(this.strengthReport.factory).subscribe(lines => {
          this.sectionMats = lines.body;
          this.strengthReport.line = this.sectionMats[0].id.secCode + '';
        });
      } else {
        this.strengthReportService.findLine('101').subscribe(lines => {
          this.sectionMats = lines.body;
          this.strengthReport.line = this.sectionMats[0].id.secCode + '';
        });
      }
    } else {
      this.subcomps = [];
      this.sectionMats = [];
    }
  }

  generateReport() {
    this.isDownload = true;
    this.strengthReport.dateFrom = this.dateFrom != null ? moment(this.dateFrom, DATE_TIME_FORMAT).startOf('day') : null;
    if (this.reportType && this.reportType !== null && this.reportType === 'C') {
      this.strengthReportService.downloadCTCPdf(this.strengthReport).subscribe(
        res => {
          FileSaver.saveAs(res, 'CTCReport.xlsx');
          this.isDownload = false;
        },
        res => {
          this.isDownload = false;
        }
      );
    } else if (this.reportType && this.reportType !== null && this.reportType === 'D') {
      this.strengthReportService.downloadCTCDetailsPdf(this.strengthReport).subscribe(
        res => {
          FileSaver.saveAs(res, 'CTCReport.xlsx');
          this.isDownload = false;
        },
        res => {
          this.isDownload = false;
        }
      );
    } else {
      this.strengthReportService.downloadPdf(this.strengthReport).subscribe(
        res => {
          FileSaver.saveAs(res, 'StrengthReport.xlsx');
          this.isDownload = false;
        },
        res => {
          this.isDownload = false;
        }
      );
    }
  }
}
