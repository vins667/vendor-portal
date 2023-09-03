import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { DateTimeAdapter, OWL_DATE_TIME_FORMATS, OWL_DATE_TIME_LOCALE } from 'ng-pick-datetime';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import * as _moment from 'moment';
import * as FileSaver from 'file-saver';
import { MomentDateTimeAdapter } from 'ng-pick-datetime-moment';
import { LeaveDifferenceService } from '.';
import { LeaveSearch, ILeaveSearch } from 'app/shared/model/leave-search.model';
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
  selector: 'jhi-leave-difference',
  templateUrl: './leave-difference.component.html',
  encapsulation: ViewEncapsulation.None,
  providers: [
    // `MomentDateTimeAdapter` and `OWL_MOMENT_DATE_TIME_FORMATS` can be automatically provided by importing
    // `OwlMomentDateTimeModule` in your applications root module. We provide it at the component level
    // here, due to limitations of our example generation script.
    { provide: DateTimeAdapter, useClass: MomentDateTimeAdapter, deps: [OWL_DATE_TIME_LOCALE] },
    { provide: OWL_DATE_TIME_FORMATS, useValue: MY_MOMENT_FORMATS }
  ]
})
export class LeaveDifferenceComponent implements OnInit {
  leaveSearch: ILeaveSearch;
  dateFrom: any;
  dateTo: any;
  isDownload = false;
  constructor(protected leaveDifferenceService: LeaveDifferenceService) {}

  ngOnInit() {
    this.leaveSearch = new LeaveSearch();
    const dt = new Date();
    const month = dt.getMonth(),
      year = dt.getFullYear();
    const FirstDay = new Date(year, month, 1);
    const LastDay = new Date(year, month + 1, 0);
    this.dateFrom = FirstDay;
    this.dateFrom.setHours(0, 0, 0, 0);
    this.dateTo = LastDay;
    this.dateTo.setHours(0, 0, 0, 0);
  }

  generateReport() {
    this.isDownload = true;
    this.leaveSearch.leaveDateFrom = this.dateFrom != null ? moment(this.dateFrom, DATE_TIME_FORMAT).startOf('day') : null;
    this.leaveSearch.leaveDateTo = this.dateTo != null ? moment(this.dateTo, DATE_TIME_FORMAT).startOf('day') : null;
    this.leaveDifferenceService.downloadPdf(this.leaveSearch).subscribe(
      res => {
        FileSaver.saveAs(res, 'LeaveDifference.xlsx');
        this.isDownload = false;
      },
      res => {
        this.isDownload = false;
      }
    );
  }
}
