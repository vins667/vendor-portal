import { Component, ChangeDetectionStrategy, ViewEncapsulation, OnInit } from '@angular/core';
import { CalendarEvent, CalendarView, CalendarMonthViewDay } from 'angular-calendar';
import { DayViewHour } from 'calendar-utils';
import { JhiAlertService, JhiEventManager } from 'ng-jhipster';
import { Router } from '@angular/router';
import { SnotifyService } from 'ng-snotify';
import { ModalDismissReasons, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { IEvent, Event } from 'app/shared/model/event.model';
export interface EventColor {
  primary: string;
  secondary: string;
}
const colors: any = {
  red: {
    primary: '#ad2121',
    secondary: '#FAE3E3'
  },
  blue: {
    primary: '#1e90ff',
    secondary: '#D1E8FF'
  },
  yellow: {
    primary: '#e3bc08',
    secondary: '#FDF1BA'
  }
};
import { DateTimeAdapter, OWL_DATE_TIME_FORMATS, OWL_DATE_TIME_LOCALE } from 'ng-pick-datetime';
import { MomentDateTimeAdapter } from 'ng-pick-datetime-moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import * as moment from 'moment';
import { BehaviorSubject, Observable, Subject } from 'rxjs';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { EventService } from './event.service';
import { MY_MOMENT_FORMATS } from 'app/entities/leave-master';
import { EventOthersComponent } from './event-others.component';
import { AccountService } from 'app/core/auth/account.service';
import { Account } from 'app/core/user/account.model';
import { LoginModalService } from 'app/core/login/login-modal.service';
import { LoginService } from 'app/core/login/login.service';
import { StateStorageService } from 'app/core/auth/state-storage.service';

@Component({
  selector: 'jhi-event',
  changeDetection: ChangeDetectionStrategy.OnPush,
  templateUrl: 'event.component.html',
  encapsulation: ViewEncapsulation.None,
  providers: [
    // `MomentDateTimeAdapter` and `OWL_MOMENT_DATE_TIME_FORMATS` can be automatically provided by importing
    // `OwlMomentDateTimeModule` in your applications root module. We provide it at the component level
    // here, due to limitations of our example generation script.
    { provide: DateTimeAdapter, useClass: MomentDateTimeAdapter, deps: [OWL_DATE_TIME_LOCALE] },
    { provide: OWL_DATE_TIME_FORMATS, useValue: MY_MOMENT_FORMATS }
  ]
})
export class EventComponent implements OnInit {
  account: Account;
  view: CalendarView = CalendarView.Month;
  viewDate: Date = new Date();
  selectedMonthViewDay: CalendarMonthViewDay;
  prevMonthViewDay: CalendarMonthViewDay;
  selectedDayViewDate: Date;
  dayView: DayViewHour[];
  selectedDays: any = [];
  activeDayIsOpen = false;
  modalData: {
    action: string;
    event: CalendarEvent;
  };
  // @ViewChild('modalContent')
  // modalContent: TemplateRef<any>;
  CalendarView = CalendarView;
  refresh: Subject<any> = new Subject();

  private _list: CalendarEvent[] = [];
  private events$: BehaviorSubject<CalendarEvent[]> = new BehaviorSubject([]);

  messageTitle: string;
  eventTitle: string;
  closeResult: string;
  event: IEvent;
  isSaving: boolean;
  eventFromDate: any;
  eventToDate: any;
  createdDate: string;
  protected ngbModalRef: NgbModalRef;

  constructor(
    private accountService: AccountService,
    private loginModalService: LoginModalService,
    private eventManager: JhiEventManager,
    private loginService: LoginService,
    private stateStorageService: StateStorageService,
    private router: Router,
    private snotifyService: SnotifyService,
    private modalService: NgbModal,
    protected eventService: EventService,
    protected jhiAlertService: JhiAlertService
  ) {}

  get observableList(): Observable<CalendarEvent[]> {
    return this.events$.asObservable();
  }

  ngOnInit() {
    this.fetchEvents();
    this.accountService.identity().then(account => {
      this.account = account;
    });
  }

  isAuthenticated() {
    return this.accountService.isAuthenticated();
  }

  dayClicked(day: CalendarMonthViewDay, events: CalendarEvent[]): void {
    this.event = new Event();
    this.viewDate = day.date;
    this.selectedMonthViewDay = day;
    if (this.prevMonthViewDay !== undefined) {
      const prevDateTime = this.selectedMonthViewDay.date.getTime();
      const prevDateIndex = this.selectedDays.findIndex(selectedDay => selectedDay.date.getTime() === prevDateTime);
      delete this.prevMonthViewDay.cssClass;
      this.selectedDays.splice(prevDateIndex, 1);
      this.prevMonthViewDay = day;
    } else {
      this.prevMonthViewDay = day;
    }
    this.selectedDays.push(this.selectedMonthViewDay);
    day.cssClass = 'cal-day-selected';
  }

  beforeMonthViewRender({ body }: { body: CalendarMonthViewDay[] }): void {
    body.forEach(day => {
      if (this.selectedDays.some(selectedDay => selectedDay.date.getTime() === day.date.getTime())) {
        day.cssClass = 'cal-day-selected';
      }
    });
  }

  hourSegmentClicked(date: Date) {
    this.selectedDayViewDate = date;
    this.addSelectedDayViewClass();
  }

  beforeDayViewRender(dayView: DayViewHour[]) {
    this.dayView = dayView;
    this.addSelectedDayViewClass();
  }

  private addSelectedDayViewClass() {
    this.dayView.forEach(hourSegment => {
      hourSegment.segments.forEach(segment => {
        delete segment.cssClass;
        if (this.selectedDayViewDate && segment.date.getTime() === this.selectedDayViewDate.getTime()) {
          segment.cssClass = 'cal-day-selected';
        }
      });
    });
  }

  handleEvent(action: string, event: CalendarEvent, content): void {
    this.event = event.meta.event;
    this.open(content, 'Edit Event', this.event.eventFrom.toDate(), this.event.eventTo.toDate());
  }

  open(content, subject, startDate?: Date, endDate?: Date) {
    this.messageTitle = subject;
    if (startDate !== undefined && endDate !== undefined) {
      this.eventFromDate = startDate;
      this.eventToDate = endDate;
    } else {
      this.eventFromDate = this.viewDate;
      this.eventToDate = this.viewDate;
    }
    this.modalService.open(content, { ariaLabelledBy: 'modal-basic-title' }).result.then(
      result => {
        this.closeResult = `Closed with: ${result}`;
      },
      reason => {
        this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
      }
    );
  }

  private getDismissReason(reason: any): string {
    if (reason === ModalDismissReasons.ESC) {
      return 'by pressing ESC';
    } else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
      return 'by clicking on a backdrop';
    } else {
      return `with: ${reason}`;
    }
  }

  save() {
    this.isSaving = true;
    this.event.eventFrom = this.eventFromDate != null ? moment(this.eventFromDate, DATE_TIME_FORMAT) : null;
    this.event.eventTo = this.eventToDate != null ? moment(this.eventToDate, DATE_TIME_FORMAT) : null;
    this.event.createdDate = this.createdDate != null ? moment(this.createdDate, DATE_TIME_FORMAT) : null;
    if (this.event.id !== undefined) {
      this.subscribeToSaveResponse(this.eventService.update(this.event));
    } else {
      this.subscribeToSaveResponse(this.eventService.create(this.event));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IEvent>>) {
    result.subscribe((res: HttpResponse<IEvent>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
  }

  fetchEvents() {
    this.event = new Event();
    this._list = [];
    this.events$ = new BehaviorSubject([]);
    let prevDate = 0;
    this.eventService.eventMonth(this.viewDate).subscribe(
      (res: HttpResponse<IEvent[]>) => {
        const events: IEvent[] = res.body;
        let ctr = 0;
        let calendarEvent: CalendarEvent;
        events.forEach(event => {
          if (prevDate === 0) {
            prevDate = event.eventFrom.toDate().getDate();
            calendarEvent = {
              start: event.eventFrom.toDate(),
              end: event.eventTo.toDate(),
              title: event.eventTitle,
              color: colors.yellow,
              meta: {
                event
              }
            };
            ++ctr;
          } else if (prevDate === event.eventFrom.toDate().getDate()) {
            if (ctr === 1) {
              calendarEvent = {
                start: event.eventFrom.toDate(),
                end: event.eventTo.toDate(),
                title: event.eventTitle,
                color: colors.red,
                meta: {
                  event
                }
              };
              ++ctr;
            } else if (ctr === 2) {
              calendarEvent = {
                start: event.eventFrom.toDate(),
                end: event.eventTo.toDate(),
                title: event.eventTitle,
                color: colors.blue,
                meta: {
                  event
                }
              };
              ++ctr;
            } else {
              calendarEvent = {
                start: event.eventFrom.toDate(),
                end: event.eventTo.toDate(),
                title: event.eventTitle,
                color: colors.yellow,
                meta: {
                  event
                }
              };
              ctr = 1;
            }
          } else {
            prevDate = event.eventFrom.toDate().getDate();
            calendarEvent = {
              start: event.eventFrom.toDate(),
              end: event.eventTo.toDate(),
              title: event.eventTitle,
              color: colors.yellow,
              meta: {
                event
              }
            };
            ctr = 1;
          }
          this._list.push(calendarEvent);
          this.events$.next(this._list);
        });
        this.refresh.next();
      },
      (res: HttpErrorResponse) => this.onError(res.message)
    );
  }

  public deleteEvent() {
    if (this.event.id !== undefined) {
      this.eventService.delete(this.event.id).subscribe(response => {
        this.modalService.dismissAll();
        this.fetchEvents();
      });
    }
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.modalService.dismissAll();
    this.fetchEvents();
  }

  protected onSaveError() {
    this.isSaving = false;
  }

  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }

  fetchOthers() {
    this.ngbModalRef = this.modalService.open(EventOthersComponent as Component, {
      size: 'lg',
      backdrop: 'static',
      windowClass: 'xlModal'
    });
  }
}
