import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { SupervisorEmployeeDetails, ISupervisorEmployeeDetails } from 'app/shared/model/supervisor-employee-details.model';
import { SupervisorEmployeeDetailsService } from './supervisor-employee-details.service';
import { IEmployeeView } from 'app/shared/model/employee-view.model';
import { DateTimeAdapter, OWL_DATE_TIME_LOCALE, OWL_DATE_TIME_FORMATS } from 'ng-pick-datetime';
import { MomentDateTimeAdapter } from 'ng-pick-datetime-moment';
import { MY_MOMENT_FORMATS } from '../leave-master';
import { CalendarView, CalendarEvent } from 'angular-calendar';

@Component({
  selector: 'jhi-supervisor-employee-details-update',
  templateUrl: './supervisor-employee-details-update.component.html',
  encapsulation: ViewEncapsulation.None,
  providers: [
    // `MomentDateTimeAdapter` and `OWL_MOMENT_DATE_TIME_FORMATS` can be automatically provided by importing
    // `OwlMomentDateTimeModule` in your applications root module. We provide it at the component level
    // here, due to limitations of our example generation script.
    { provide: DateTimeAdapter, useClass: MomentDateTimeAdapter, deps: [OWL_DATE_TIME_LOCALE] },
    { provide: OWL_DATE_TIME_FORMATS, useValue: MY_MOMENT_FORMATS }
  ]
})
export class SupervisorEmployeeDetailsUpdateComponent implements OnInit {
  employeeViews: IEmployeeView[];
  supervisorEmployeeDetail: SupervisorEmployeeDetails;
  supervisorEmployeeDetails: ISupervisorEmployeeDetails;
  view: CalendarView = CalendarView.Month;
  viewDate: Date = new Date();
  activeDayIsOpen = false;
  eventFromDate: any;
  eventToDate: any;
  createdDate: string;
  events: CalendarEvent[] = [];
  constructor(protected supervisorEmployeeDetailsService: SupervisorEmployeeDetailsService, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.supervisorEmployeeDetail = new SupervisorEmployeeDetails();
    this.supervisorEmployeeDetailsService.query().subscribe(res => {
      this.employeeViews = res.body;
      this.supervisorEmployeeDetail.cardNo = this.employeeViews[0].login;
      if (this.supervisorEmployeeDetail !== undefined) {
        const month = (0, eval)(this.viewDate.getMonth().toString()) + 1;
        let monthYear = '';
        if (month < 10) {
          monthYear = '0' + month + '-' + this.viewDate.getFullYear();
        } else {
          monthYear = month + '-' + this.viewDate.getFullYear();
        }
        this.supervisorEmployeeDetailsService.getAttendance(this.supervisorEmployeeDetail.cardNo, monthYear).subscribe(resDetails => {
          this.supervisorEmployeeDetails = resDetails.body;
          this.events = [];
          let calendarEvent: CalendarEvent;
          this.supervisorEmployeeDetails.attendanceList.forEach(data => {
            let attendanceTitle = null;
            if (data.msg !== null && !(data.msg === 'Absent' || data.msg === 'Single Punch')) {
              attendanceTitle = 'In- ' + data.inTime + ' | Out- ' + data.outTime + ' | ' + data.msg;
            } else if (data.msg !== null && data.msg === 'Absent') {
              attendanceTitle = data.msg;
            } else if (data.msg !== null && data.msg === 'Single Punch') {
              attendanceTitle = 'In- ' + data.inTime + ' | ' + data.msg;
            } else {
              attendanceTitle = 'In- ' + data.inTime + ' | Out- ' + data.outTime;
            }
            calendarEvent = {
              start: data.attendanceDate.toDate(),
              end: data.attendanceDate.toDate(),
              title: attendanceTitle,
              meta: {
                event
              }
            };
            this.events.push(calendarEvent);
          });
        });
      }
    });
  }

  onNavigateChange() {
    const month = (0, eval)(this.viewDate.getMonth().toString()) + 1;
    let monthYear = '';
    if (month < 10) {
      monthYear = '0' + month + '-' + this.viewDate.getFullYear();
    } else {
      monthYear = month + '-' + this.viewDate.getFullYear();
    }
    if (this.supervisorEmployeeDetail !== undefined) {
      this.supervisorEmployeeDetailsService.getAttendance(this.supervisorEmployeeDetail.cardNo, monthYear).subscribe(res => {
        this.supervisorEmployeeDetails = res.body;
        this.events = [];
        let calendarEvent: CalendarEvent;
        this.supervisorEmployeeDetails.attendanceList.forEach(data => {
          let attendanceTitle = null;
          if (data.msg !== null && !(data.msg === 'Absent' || data.msg === 'Single Punch')) {
            attendanceTitle = 'In- ' + data.inTime + ' | Out- ' + data.outTime + ' | ' + data.msg;
          } else if (data.msg !== null && data.msg === 'Absent') {
            attendanceTitle = data.msg;
          } else if (data.msg !== null && data.msg === 'Single Punch') {
            attendanceTitle = 'In- ' + data.inTime + ' | ' + data.msg;
          } else {
            attendanceTitle = 'In- ' + data.inTime + ' | Out- ' + data.outTime;
          }
          calendarEvent = {
            start: data.attendanceDate.toDate(),
            end: data.attendanceDate.toDate(),
            title: attendanceTitle,
            meta: {
              event
            }
          };
          this.events.push(calendarEvent);
        });
      });
    }
  }
  lockedWeekendFilter(d: Date): boolean {
    const date = new Date(d);
    const day = date.getDay();
    return day === 0 || day === 7;
  }
}
