import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService, JhiEventManager } from 'ng-jhipster';
import { ILeaveMaster, LeaveMaster } from 'app/shared/model/leave-master.model';
import { LeaveMasterService } from './leave-master.service';
import { ILeaveTypeMaster } from 'app/shared/model/leave-type-master.model';
import { LeaveTypeMasterService } from 'app/entities/leave-type-master';
import { ILeaveSubTypeMaster } from 'app/shared/model/leave-sub-type-master.model';
import { LeaveSubTypeMasterService } from 'app/entities/leave-sub-type-master';
import { SnotifyService } from 'ng-snotify';
import { DateTimeAdapter, OWL_DATE_TIME_FORMATS, OWL_DATE_TIME_LOCALE } from 'ng-pick-datetime';
import { MomentDateTimeAdapter } from 'ng-pick-datetime-moment';
import { IEmployeeView } from 'app/shared/model/employee-view.model';
import { EmployeeViewService } from 'app/entities/employee-view';
import { ILeaveStatus } from 'app/shared/model/leave-status.model';
import { IShift } from 'app/shared/model/shift.model';
import { ShiftService } from 'app/entities/shift/shift.service';
import { DayStatusService } from 'app/entities/day-status';
import { IDayStatus } from 'app/shared/model/day-status.model';
import { isMoment } from 'moment';
import { toastConfig } from 'app/core/toast/toast-config';
import { LeaveSearch } from 'app/shared/model/leave-search.model';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { LeaveMobileAttendanceComponent } from './leave-mobile-attendance.component';
import { LeaveMobileMapLocationComponent } from './leave-mobile-map-location.component';
import { ICompOffLeave } from 'app/shared/model/comp-off-leave.model';
import { UserService } from 'app/core/user/user.service';
import { IUser } from 'app/core/user/user.model';

// See the Moment.js docs for the meaning of these formats:
// https://momentjs.com/docs/#/displaying/format/
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
  selector: 'jhi-leave-master-update',
  templateUrl: './leave-master-update.component.html',
  encapsulation: ViewEncapsulation.None,
  providers: [
    // `MomentDateTimeAdapter` and `OWL_MOMENT_DATE_TIME_FORMATS` can be automatically provided by importing
    // `OwlMomentDateTimeModule` in your applications root module. We provide it at the component level
    // here, due to limitations of our example generation script.
    { provide: DateTimeAdapter, useClass: MomentDateTimeAdapter, deps: [OWL_DATE_TIME_LOCALE] },
    { provide: OWL_DATE_TIME_FORMATS, useValue: MY_MOMENT_FORMATS }
  ]
})
export class LeaveMasterUpdateComponent implements OnInit {
  leaveMaster: ILeaveMaster;
  isSaving: boolean;
  isLeaveDateTo = false;
  leavetypemasters: ILeaveTypeMaster[];
  leavesubtypemasters: ILeaveSubTypeMaster[];
  compOffLeaves: ICompOffLeave[];
  leaveDateFrom: any;
  leaveDateTo: any;
  createdDate: string;
  hodApprovedDate: string;
  hrApprovedDate: string;
  employeeView: IEmployeeView;
  hodEmployeeView: IEmployeeView;
  balance: number;
  leaveTimeFrom: any;
  leaveTimeTo: any;
  shift: IShift;
  isTimeDisabled = false;
  isSubTypeDisabled = false;
  inTimeDisabled = false;
  outTimeDisabled = false;
  selectUndefinedOptionValue: any;
  protected ngbModalRef: NgbModalRef;

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected leaveMasterService: LeaveMasterService,
    protected leaveTypeMasterService: LeaveTypeMasterService,
    protected leaveSubTypeMasterService: LeaveSubTypeMasterService,
    protected activatedRoute: ActivatedRoute,
    protected snotifyService: SnotifyService,
    protected userService: UserService,
    protected employeeViewService: EmployeeViewService,
    protected shiftService: ShiftService,
    protected dayStatusService: DayStatusService,
    private eventManager: JhiEventManager,
    private modalService: NgbModal
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.registerChangeInSearchUser();
    this.leaveDateFrom = new Date();
    this.leaveDateFrom.setHours(0, 0, 0, 0);
    this.leaveDateTo = new Date();
    this.leaveDateTo.setHours(0, 0, 0, 0);
    this.leaveTimeFrom = new Date();
    this.leaveTimeTo = new Date();
    this.leaveMaster = new LeaveMaster();
    this.leaveMaster.flag = 'E';

    this.shiftService.findByDate(this.leaveDateFrom).subscribe((res3: HttpResponse<IShift>) => {
      this.shift = res3.body;
    });
    this.userService.currentuser().subscribe((res: HttpResponse<IUser>) => {
      this.leaveMaster.userCode = res.body;
      this.employeeViewService.find(res.body.login).subscribe((res1: HttpResponse<IEmployeeView>) => {
        this.employeeView = res1.body;
        if (this.employeeView.supervisor !== undefined && this.employeeView.supervisor.includes('(') === true) {
          const hodcardNo = this.employeeView.supervisor.substring(0, this.employeeView.supervisor.indexOf('('));
          this.employeeViewService.findByCard(hodcardNo).subscribe((res2: HttpResponse<IEmployeeView>) => {
            this.hodEmployeeView = res2.body;
            this.leaveMaster.hodApprovedBy = res2.body.login.toLowerCase();
          });
        }
      });
    });
    this.leaveTypeMasterService.findByType('L').subscribe(
      (res: HttpResponse<ILeaveTypeMaster[]>) => {
        this.leavetypemasters = res.body;
      },
      (res: HttpErrorResponse) => this.onError(res.message)
    );
  }

  previousState() {
    window.history.back();
  }

  openAttach() {
    if (
      this.leaveMaster.leaveTypeMaster !== null &&
      this.leaveMaster.leaveTypeMaster.leaveCode === 'OD' &&
      this.leaveTimeFrom !== undefined &&
      this.leaveTimeTo !== undefined
    ) {
      const leaveSearch = new LeaveSearch();
      leaveSearch.leaveDateFrom = this.leaveDateFrom != null ? moment(this.leaveDateFrom, DATE_TIME_FORMAT) : null;
      leaveSearch.leaveDateTo = this.leaveDateTo != null ? moment(this.leaveDateTo, DATE_TIME_FORMAT) : null;
      if (leaveSearch.leaveDateTo < leaveSearch.leaveDateFrom) {
        this.snotifyService.error('Date To must be greater than Date From', '', toastConfig);
        this.isSaving = false;
        return;
      } else {
        this.leaveMasterService.mobile(leaveSearch).subscribe(mobileAttendances => {
          this.ngbModalRef = this.modalService.open(LeaveMobileAttendanceComponent as Component, {
            size: 'lg',
            backdrop: 'static',
            windowClass: 'xlModal'
          });
          this.ngbModalRef.componentInstance.mobileAttendances = mobileAttendances.body;
        });
      }
    }
  }

  registerChangeInSearchUser() {
    this.eventManager.subscribe('selectedLeaveMobileAttendance', message => {
      const userDetails = message.content;
      if (this.leaveMaster && this.leaveMaster.mobileAttendances) {
        let exist = false;
        let ctr = 0;
        this.leaveMaster.mobileAttendances.forEach(mobileAttendance => {
          if (mobileAttendance.id === userDetails.id) {
            exist = true;
          }
          ++ctr;
        });
        if (exist === false && ctr === this.leaveMaster.mobileAttendances.length) {
          this.leaveMaster.mobileAttendances.push(userDetails);
        }
      } else {
        this.leaveMaster.mobileAttendances = [];
        this.leaveMaster.mobileAttendances.push(userDetails);
      }
    });
  }

  changeDate(leaveSubTypeMaster) {
    let ctr = 0;
    this.shiftService.findByDate(this.leaveDateFrom).subscribe((res3: HttpResponse<IShift>) => {
      this.shift = res3.body;
      if (this.leaveMaster.leaveTypeMaster.leaveCode === 'SP') {
        this.isSubTypeDisabled = true;
        this.isLeaveDateTo = true;
        this.leaveDateTo = this.leaveDateFrom;
        this.isTimeDisabled = true;
        const date = moment(this.leaveDateFrom, DATE_TIME_FORMAT).format('DD.MM.YYYY');
        this.dayStatusService.find(date).subscribe((res: HttpResponse<IDayStatus>) => {
          if (res.body !== null) {
            if (res.body.inTm !== null && res.body.inTm.trim().length > 0) {
              const inTmarr = res.body.inTm.split(':');
              if (isMoment(this.leaveDateFrom)) {
                this.leaveDateFrom = this.leaveDateFrom.toDate();
              }
              this.leaveTimeFrom = new Date(this.leaveDateFrom.setHours(Number(inTmarr[0]), Number(inTmarr[1]), 0, 0));
              this.inTimeDisabled = true;
            } else {
              ++ctr;
              if (isMoment(this.leaveDateFrom)) {
                this.leaveDateFrom = this.leaveDateFrom.toDate();
              }
              const shiftInTime = this.shift.intime;
              const intarr = shiftInTime.split(':');
              if (isMoment(this.leaveDateFrom)) {
                this.leaveDateFrom = this.leaveDateFrom.toDate();
                this.leaveTimeFrom = new Date(this.leaveDateFrom.setHours(Number(intarr[0]), Number(intarr[1]), 0, 0));
              } else {
                this.leaveTimeFrom = new Date(this.leaveDateFrom.setHours(Number(intarr[0]), Number(intarr[1]), 0, 0));
              }
              this.inTimeDisabled = false;
              this.leaveMaster.missPunchType = 'F';
            }
            if (res.body.outTm !== null && res.body.outTm.trim().length > 0) {
              if (isMoment(this.leaveDateTo)) {
                this.leaveDateTo = this.leaveDateTo.toDate();
              }
              const outTmarr = res.body.outTm.split(':');
              this.leaveTimeTo = new Date(this.leaveDateTo.setHours(Number(outTmarr[0]), Number(outTmarr[1]), 0, 0));
              this.outTimeDisabled = true;
            } else {
              ++ctr;
              if (isMoment(this.leaveDateTo)) {
                this.leaveDateTo = this.leaveDateTo.toDate();
              }
              const shiftOutTime = this.shift.outtime;
              const outarr = shiftOutTime.split(':');
              if (isMoment(this.leaveDateTo)) {
                this.leaveDateTo = this.leaveDateTo.toDate();
                this.leaveTimeTo = new Date(this.leaveDateTo.setHours(Number(outarr[0]), Number(outarr[1]), 0, 0));
              } else {
                this.leaveTimeTo = new Date(this.leaveDateTo.setHours(Number(outarr[0]), Number(outarr[1]), 0, 0));
              }
              this.outTimeDisabled = false;
              this.leaveMaster.missPunchType = 'S';
            }
            if (ctr === 2) {
              this.leaveMaster.missPunchType = 'A';
              this.leavesubtypemasters.forEach(leavesubtypemaster => {
                if (leavesubtypemaster.subTypeCode === 'DP') {
                  this.leaveMaster.leaveSubTypeMaster = leavesubtypemaster;
                }
              });
            } else {
              this.leavesubtypemasters.forEach(leavesubtypemaster => {
                if (leavesubtypemaster.subTypeCode === 'SP') {
                  this.leaveMaster.leaveSubTypeMaster = leavesubtypemaster;
                }
              });
            }
          }
        });
      } else if (leaveSubTypeMaster !== undefined) {
        if (leaveSubTypeMaster.subTypeName === 'FIRST HALF' || leaveSubTypeMaster.subTypeName === 'SECOND HALF') {
          this.isLeaveDateTo = true;
          this.leaveDateTo = this.leaveDateFrom;
          this.leaveMaster.noDays = 0.5;
        } else {
          this.isLeaveDateTo = false;
        }
      } else {
        this.isLeaveDateTo = false;
      }
    });
  }

  changeDateSetting(leaveSubTypeMaster) {
    if (leaveSubTypeMaster !== undefined) {
      if (leaveSubTypeMaster.subTypeName === 'FIRST HALF' || leaveSubTypeMaster.subTypeName === 'SECOND HALF') {
        this.isLeaveDateTo = true;
        this.leaveDateTo = this.leaveDateFrom;
      } else {
        this.isLeaveDateTo = false;
      }
    } else {
      this.isLeaveDateTo = false;
    }

    this.inTimeDisabled = true;
    this.outTimeDisabled = true;
    const inTime = this.shift.intime;
    const outTime = this.shift.outtime;
    const br1 = this.shift.br1;
    const end1 = this.shift.end1;
    if (this.leaveMaster.leaveSubTypeMaster.subTypeName === 'FULL DAY') {
      const intarr = inTime.split(':');
      if (isMoment(this.leaveDateFrom)) {
        this.leaveDateFrom = this.leaveDateFrom.toDate();
        this.leaveTimeFrom = new Date(this.leaveDateFrom.setHours(Number(intarr[0]), Number(intarr[1]), 0, 0));
      } else {
        this.leaveTimeFrom = new Date(this.leaveDateFrom.setHours(Number(intarr[0]), Number(intarr[1]), 0, 0));
      }

      const outarr = outTime.split(':');
      if (isMoment(this.leaveDateTo)) {
        this.leaveDateTo = this.leaveDateTo.toDate();
        this.leaveTimeTo = new Date(this.leaveDateTo.setHours(Number(outarr[0]), Number(outarr[1]), 0, 0));
      } else {
        this.leaveTimeTo = new Date(this.leaveDateTo.setHours(Number(outarr[0]), Number(outarr[1]), 0, 0));
      }
      this.isTimeDisabled = true;
    } else if (this.leaveMaster.leaveSubTypeMaster.subTypeName === 'FIRST HALF') {
      const intarr = inTime.split(':');
      if (isMoment(this.leaveDateFrom)) {
        this.leaveDateFrom = this.leaveDateFrom.toDate();
        this.leaveTimeFrom = new Date(this.leaveDateFrom.setHours(Number(intarr[0]), Number(intarr[1]), 0, 0));
      } else {
        this.leaveTimeFrom = new Date(this.leaveDateFrom.setHours(Number(intarr[0]), Number(intarr[1]), 0, 0));
      }

      const br1arr = br1.split(':');
      if (isMoment(this.leaveDateTo)) {
        this.leaveDateTo = this.leaveDateTo.toDate();
        this.leaveTimeTo = new Date(this.leaveDateTo.setHours(Number(br1arr[0]), Number(br1arr[1]), 0, 0));
      } else {
        this.leaveTimeTo = new Date(this.leaveDateTo.setHours(Number(br1arr[0]), Number(br1arr[1]), 0, 0));
      }
      this.isTimeDisabled = true;
      this.leaveMaster.noDays = 0.5;
    } else if (this.leaveMaster.leaveSubTypeMaster.subTypeName === 'SECOND HALF') {
      const end1arr = end1.split(':');
      if (isMoment(this.leaveDateFrom)) {
        this.leaveDateFrom = this.leaveDateFrom.toDate();
        this.leaveTimeFrom = new Date(this.leaveDateFrom.setHours(Number(end1arr[0]), Number(end1arr[1]), 0, 0));
      } else {
        this.leaveTimeFrom = new Date(this.leaveDateFrom.setHours(Number(end1arr[0]), Number(end1arr[1]), 0, 0));
      }

      const outarr = outTime.split(':');
      if (isMoment(this.leaveDateTo)) {
        this.leaveDateTo = this.leaveDateTo.toDate();
        this.leaveTimeTo = new Date(this.leaveDateTo.setHours(Number(outarr[0]), Number(outarr[1]), 0, 0));
      } else {
        this.leaveTimeTo = new Date(this.leaveDateTo.setHours(Number(outarr[0]), Number(outarr[1]), 0, 0));
      }
      this.isTimeDisabled = true;
      this.leaveMaster.noDays = 0.5;
    } else if (this.leaveMaster.leaveSubTypeMaster.subTypeName === 'IN BETWEEN') {
      const intarr = inTime.split(':');
      if (isMoment(this.leaveDateFrom)) {
        this.leaveDateFrom = this.leaveDateFrom.toDate();
        this.leaveTimeFrom = new Date(this.leaveDateFrom.setHours(Number(intarr[0]), Number(intarr[1]), 0, 0));
      } else {
        this.leaveTimeFrom = new Date(this.leaveDateFrom.setHours(Number(intarr[0]), Number(intarr[1]), 0, 0));
      }

      const outarr = outTime.split(':');
      if (isMoment(this.leaveDateTo)) {
        this.leaveDateTo = this.leaveDateTo.toDate();
        this.leaveTimeTo = new Date(this.leaveDateTo.setHours(Number(outarr[0]), Number(outarr[1]), 0, 0));
      } else {
        this.leaveTimeTo = new Date(this.leaveDateTo.setHours(Number(outarr[0]), Number(outarr[1]), 0, 0));
      }
      this.isTimeDisabled = false;
    }
  }

  save() {
    this.isSaving = true;

    this.leaveMaster.leaveDateFrom = this.leaveDateFrom != null ? moment(this.leaveDateFrom, DATE_TIME_FORMAT).startOf('day') : null;
    this.leaveMaster.leaveDateTo = this.leaveDateTo != null ? moment(this.leaveDateTo, DATE_TIME_FORMAT).startOf('day') : null;
    this.leaveMaster.createdDate = this.createdDate != null ? moment(this.createdDate, DATE_TIME_FORMAT) : null;
    this.leaveMaster.hodApprovedDate = this.hodApprovedDate != null ? moment(this.hodApprovedDate, DATE_TIME_FORMAT) : null;
    this.leaveMaster.hrApprovedDate = this.hrApprovedDate != null ? moment(this.hrApprovedDate, DATE_TIME_FORMAT) : null;
    this.leaveMaster.leaveTimeFrom = this.leaveTimeFrom != null ? moment(this.leaveTimeFrom, DATE_TIME_FORMAT) : null;
    this.leaveMaster.leaveTimeTo = this.leaveTimeTo != null ? moment(this.leaveTimeTo, DATE_TIME_FORMAT) : null;
    if (this.leaveMaster.leaveDateTo < this.leaveMaster.leaveDateFrom) {
      this.snotifyService.error('Date To must be greater than Date From', '', toastConfig);
      this.isSaving = false;
      return;
    } else if (this.leaveMaster.leaveDateTo.format('M') !== this.leaveMaster.leaveDateFrom.format('M')) {
      this.snotifyService.error('From & To Date month must be same..', '', toastConfig);
      this.isSaving = false;
      return;
    } else if (
      this.leaveMaster.leaveTypeMaster !== null &&
      (this.leaveMaster.leaveTypeMaster.leaveCode === 'CL' ||
        this.leaveMaster.leaveTypeMaster.leaveCode === 'SL' ||
        this.leaveMaster.leaveTypeMaster.leaveCode === 'CO' ||
        this.leaveMaster.leaveTypeMaster.leaveCode === 'EL') &&
      this.leaveMaster.leaveSubTypeMaster.subTypeName === 'FULL DAY' &&
      this.leaveMaster.leaveDateTo.diff(this.leaveMaster.leaveDateFrom, 'days') + 1 > this.balance
    ) {
      this.snotifyService.error('You do not have balance..', '', toastConfig);
      this.isSaving = false;
      return;
    } else if (
      this.leaveMaster.leaveTypeMaster !== null &&
      (this.leaveMaster.leaveTypeMaster.leaveCode === 'CL' ||
        this.leaveMaster.leaveTypeMaster.leaveCode === 'SL' ||
        this.leaveMaster.leaveTypeMaster.leaveCode === 'CO' ||
        this.leaveMaster.leaveTypeMaster.leaveCode === 'EL') &&
      (this.leaveMaster.leaveSubTypeMaster.subTypeName === 'FIRST HALF' ||
        this.leaveMaster.leaveSubTypeMaster.subTypeName === 'SECOND HALF') &&
      this.leaveMaster.leaveDateTo.diff(this.leaveMaster.leaveDateFrom, 'days') + 0.5 > this.balance
    ) {
      this.snotifyService.error('You do not have balance..', '', toastConfig);
      this.isSaving = false;
      return;
    } else {
      if (
        this.leaveMaster.leaveSubTypeMaster.subTypeName === 'FIRST HALF' ||
        this.leaveMaster.leaveSubTypeMaster.subTypeName === 'SECOND HALF'
      ) {
        this.leaveMaster.noDays = 0.5;
      } else {
        this.leaveMaster.noDays = this.leaveMaster.leaveDateTo.diff(this.leaveMaster.leaveDateFrom, 'days') + 1;
      }
      this.subscribeToSaveResponse(this.leaveMasterService.create(this.leaveMaster));
    }
  }

  compOff() {
    if (this.leaveMaster.compOffMasterId && this.leaveMaster.compOffMasterId !== undefined) {
      this.compOffLeaves.forEach(compOffLeave => {
        if (Number(compOffLeave.id) === Number(this.leaveMaster.compOffMasterId)) {
          this.balance = compOffLeave.balance;
          this.leaveMaster.reason = 'COMP Off against work done on ' + compOffLeave.compOffDateView;
        }
      });
    } else {
      this.balance = 0;
      this.leaveMaster.compOffMasterId = undefined;
      this.leaveMaster.reason = '';
    }
  }

  loadTypes() {
    if (isMoment(this.leaveDateFrom)) {
      this.leaveDateFrom = this.leaveDateFrom.toDate();
    }

    if (isMoment(this.leaveDateTo)) {
      this.leaveDateTo = this.leaveDateTo.toDate();
    }
    if (this.leaveMaster.leaveTypeMaster === 'undefined') {
      this.leavesubtypemasters = [];
      this.leaveMaster.leaveSubTypeMaster = undefined;
      this.leaveMaster.leaveTypeMaster = undefined;
    } else if (this.leaveMaster.leaveTypeMaster !== undefined || this.leaveMaster.leaveTypeMaster !== null) {
      this.leaveSubTypeMasterService.queryByLeaveType(this.leaveMaster.leaveTypeMaster.id).subscribe(
        (res: HttpResponse<ILeaveSubTypeMaster[]>) => {
          this.leavesubtypemasters = res.body;
          this.leaveMaster.leaveSubTypeMaster = res.body[0];
          const inTime = this.shift.intime;
          const outTime = this.shift.outtime;
          const br1 = this.shift.br1;
          const end1 = this.shift.end1;
          this.inTimeDisabled = true;
          this.outTimeDisabled = true;
          if (this.leaveMaster.leaveSubTypeMaster.subTypeName === 'FULL DAY') {
            const intarr = inTime.split(':');
            if (isMoment(this.leaveDateFrom)) {
              this.leaveDateFrom = this.leaveDateFrom.toDate();
              this.leaveTimeFrom = new Date(this.leaveDateFrom.setHours(Number(intarr[0]), Number(intarr[1]), 0, 0));
            } else {
              this.leaveTimeFrom = new Date(this.leaveDateFrom.setHours(Number(intarr[0]), Number(intarr[1]), 0, 0));
            }

            const outarr = outTime.split(':');
            if (isMoment(this.leaveDateTo)) {
              this.leaveDateTo = this.leaveDateTo.toDate();
              this.leaveTimeTo = new Date(this.leaveDateTo.setHours(Number(outarr[0]), Number(outarr[1]), 0, 0));
            } else {
              this.leaveTimeTo = new Date(this.leaveDateTo.setHours(Number(outarr[0]), Number(outarr[1]), 0, 0));
            }
            this.isTimeDisabled = true;
          } else if (this.leaveMaster.leaveSubTypeMaster.subTypeName === 'FIRST HALF') {
            const intarr = inTime.split(':');
            if (isMoment(this.leaveDateFrom)) {
              this.leaveDateFrom = this.leaveDateFrom.toDate();
              this.leaveTimeFrom = new Date(this.leaveDateFrom.setHours(Number(intarr[0]), Number(intarr[1]), 0, 0));
            } else {
              this.leaveTimeFrom = new Date(this.leaveDateFrom.setHours(Number(intarr[0]), Number(intarr[1]), 0, 0));
            }

            const br1arr = br1.split(':');
            if (isMoment(this.leaveDateTo)) {
              this.leaveDateTo = this.leaveDateTo.toDate();
              this.leaveTimeTo = new Date(this.leaveDateTo.setHours(Number(br1arr[0]), Number(br1arr[1]), 0, 0));
            } else {
              this.leaveTimeTo = new Date(this.leaveDateTo.setHours(Number(br1arr[0]), Number(br1arr[1]), 0, 0));
            }
            this.isTimeDisabled = true;
            this.leaveMaster.noDays = 0.5;
          } else if (this.leaveMaster.leaveSubTypeMaster.subTypeName === 'SECOND HALF') {
            const end1arr = end1.split(':');
            if (isMoment(this.leaveDateFrom)) {
              this.leaveDateFrom = this.leaveDateFrom.toDate();
              this.leaveTimeFrom = new Date(this.leaveDateFrom.setHours(Number(end1arr[0]), Number(end1arr[1]), 0, 0));
            } else {
              this.leaveTimeFrom = new Date(this.leaveDateFrom.setHours(Number(end1arr[0]), Number(end1arr[1]), 0, 0));
            }

            const outarr = outTime.split(':');
            if (isMoment(this.leaveDateTo)) {
              this.leaveDateTo = this.leaveDateTo.toDate();
              this.leaveTimeTo = new Date(this.leaveDateTo.setHours(Number(outarr[0]), Number(outarr[1]), 0, 0));
            } else {
              this.leaveTimeTo = new Date(this.leaveDateTo.setHours(Number(outarr[0]), Number(outarr[1]), 0, 0));
            }
            this.isTimeDisabled = true;
            this.leaveMaster.noDays = 0.5;
          }
        },
        (res: HttpErrorResponse) => this.onError(res.message)
      );
      this.leaveMasterService.leaveBalance(this.leaveMaster.leaveTypeMaster.leaveCode).subscribe(
        (res: HttpResponse<ILeaveStatus>) => {
          this.balance = res.body.leaveBalance;
        },
        (res: HttpErrorResponse) => this.onError(res.message)
      );
      let ctr = 0;
      if (this.leaveMaster.leaveTypeMaster.leaveCode === 'SP') {
        this.isSubTypeDisabled = true;
        this.isTimeDisabled = true;
        this.isLeaveDateTo = true;
        this.leaveDateTo = this.leaveDateFrom;
        this.dayStatusService
          .find(moment(this.leaveDateFrom, DATE_TIME_FORMAT).format('DD.MM.YYYY'))
          .subscribe((res: HttpResponse<IDayStatus>) => {
            if (res.body !== null) {
              if (res.body.inTm !== null && res.body.inTm.trim().length > 0) {
                const inTmarr = res.body.inTm.split(':');
                this.leaveTimeFrom = new Date(this.leaveDateFrom.setHours(Number(inTmarr[0]), Number(inTmarr[1]), 0, 0));
                this.inTimeDisabled = true;
              } else {
                ++ctr;
                const inTime = this.shift.intime;
                const intarr = inTime.split(':');
                if (isMoment(this.leaveDateFrom)) {
                  this.leaveDateFrom = this.leaveDateFrom.toDate();
                  this.leaveTimeFrom = new Date(this.leaveDateFrom.setHours(Number(intarr[0]), Number(intarr[1]), 0, 0));
                } else {
                  this.leaveTimeFrom = new Date(this.leaveDateFrom.setHours(Number(intarr[0]), Number(intarr[1]), 0, 0));
                }
                this.inTimeDisabled = false;
                this.leaveMaster.missPunchType = 'F';
              }
              if (res.body.outTm !== null && res.body.outTm.trim().length > 0) {
                const outTmarr = res.body.outTm.split(':');
                this.leaveTimeTo = new Date(this.leaveDateTo.setHours(Number(outTmarr[0]), Number(outTmarr[1]), 0, 0));
                this.outTimeDisabled = true;
              } else {
                ++ctr;
                const outTime = this.shift.outtime;
                const outarr = outTime.split(':');
                if (isMoment(this.leaveDateTo)) {
                  this.leaveDateTo = this.leaveDateTo.toDate();
                  this.leaveTimeTo = new Date(this.leaveDateTo.setHours(Number(outarr[0]), Number(outarr[1]), 0, 0));
                } else {
                  this.leaveTimeTo = new Date(this.leaveDateTo.setHours(Number(outarr[0]), Number(outarr[1]), 0, 0));
                }
                this.outTimeDisabled = false;
                this.leaveMaster.missPunchType = 'S';
              }
              if (ctr === 2) {
                this.leaveMaster.missPunchType = 'A';
                this.leavesubtypemasters.forEach(leavesubtypemaster => {
                  if (leavesubtypemaster.subTypeCode === 'DP') {
                    this.leaveMaster.leaveSubTypeMaster = leavesubtypemaster;
                  }
                });
              } else {
                this.leavesubtypemasters.forEach(leavesubtypemaster => {
                  if (leavesubtypemaster.subTypeName === 'SP') {
                    this.leaveMaster.leaveSubTypeMaster = leavesubtypemaster;
                  }
                });
              }
            }
          });
      }
      if (
        this.leaveMaster.leaveTypeMaster &&
        this.leaveMaster.leaveTypeMaster.leaveCode !== undefined &&
        this.leaveMaster.leaveTypeMaster.leaveCode === 'CO'
      ) {
        this.leaveMasterService.compOff().subscribe(compOffLeaves => {
          this.compOffLeaves = compOffLeaves.body;
        });
      } else {
        this.compOffLeaves = [];
        this.leaveMaster.compOffMasterId = undefined;
      }
    } else {
      this.leavesubtypemasters = [];
      this.leaveMaster.leaveSubTypeMaster = undefined;
      this.leaveMaster.leaveTypeMaster = undefined;
      this.leaveMaster.compOffMasterId = undefined;
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ILeaveMaster>>) {
    result.subscribe((res: HttpResponse<ILeaveMaster>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError(res.headers));
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(res: HttpHeaders) {
    this.isSaving = false;
    this.snotifyService.error(res.get('X-vamaniportalApp-error'), '', toastConfig);
  }

  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }

  trackLeaveTypeMasterById(index: number, item: ILeaveTypeMaster) {
    return item.id;
  }

  trackLeaveSubTypeMasterById(index: number, item: ILeaveSubTypeMaster) {
    return item.id;
  }

  maps(mobileAttendance) {
    this.ngbModalRef = this.modalService.open(LeaveMobileMapLocationComponent as Component, {
      size: 'lg',
      backdrop: 'static'
    });
    this.ngbModalRef.componentInstance.mobileAttendance = mobileAttendance;
  }
}
