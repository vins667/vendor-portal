import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService, JhiEventManager } from 'ng-jhipster';
import { ILeaveEntryHr, LeaveEntryHr } from 'app/shared/model/leave-entry-hr.model';
import { LeaveEntryHrService } from './leave-entry-hr.service';
import { ILeaveTypeMaster } from 'app/shared/model/leave-type-master.model';
import { LeaveTypeMasterService } from 'app/entities/leave-type-master';
import { ILeaveSubTypeMaster } from 'app/shared/model/leave-sub-type-master.model';
import { LeaveSubTypeMasterService } from 'app/entities/leave-sub-type-master';
import { DateTimeAdapter, OWL_DATE_TIME_LOCALE, OWL_DATE_TIME_FORMATS } from 'ng-pick-datetime';
import { MomentDateTimeAdapter } from 'ng-pick-datetime-moment';
import { IEmployeeView } from 'app/shared/model/employee-view.model';
import { IShift } from 'app/shared/model/shift.model';
import { SnotifyService } from 'ng-snotify';
import { EmployeeViewService } from '../employee-view';
import { ShiftService } from '../shift';
import { DayStatusService } from '../day-status';
import { IDayStatus } from 'app/shared/model/day-status.model';
import { toastConfig } from 'app/core/toast/toast-config';
import { ILeaveStatus } from 'app/shared/model/leave-status.model';
import { EmployeeSearchComponent } from './employee-search.component';
import { NgbModalRef, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Master } from 'app/shared/model/master.modal';
import { UserService } from 'app/core/user/user.service';

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
  selector: 'jhi-leave-entry-hr-update',
  templateUrl: './leave-entry-hr-update.component.html',
  encapsulation: ViewEncapsulation.None,
  providers: [
    // `MomentDateTimeAdapter` and `OWL_MOMENT_DATE_TIME_FORMATS` can be automatically provided by importing
    // `OwlMomentDateTimeModule` in your applications root module. We provide it at the component level
    // here, due to limitations of our example generation script.
    { provide: DateTimeAdapter, useClass: MomentDateTimeAdapter, deps: [OWL_DATE_TIME_LOCALE] },
    { provide: OWL_DATE_TIME_FORMATS, useValue: MY_MOMENT_FORMATS }
  ]
})
export class LeaveEntryHrUpdateComponent implements OnInit {
  leaveEntryHr: ILeaveEntryHr;
  isSaving: boolean;

  isLeaveDateTo = false;
  leavetypemasters: ILeaveTypeMaster[];
  leavesubtypemasters: ILeaveSubTypeMaster[];
  leaveDateFrom: any;
  leaveDateTo: any;
  createdDate: string;
  hodApprovedDate: string;
  hrApprovedDate: string;
  employeeView: IEmployeeView;
  hodEmployeeView: IEmployeeView;
  subTypeName: string;
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
  name: string;
  cardNo: string;
  constructor(
    protected jhiAlertService: JhiAlertService,
    protected leaveEntryHrService: LeaveEntryHrService,
    protected leaveTypeMasterService: LeaveTypeMasterService,
    protected leaveSubTypeMasterService: LeaveSubTypeMasterService,
    protected activatedRoute: ActivatedRoute,
    protected snotifyService: SnotifyService,
    protected userService: UserService,
    protected employeeViewService: EmployeeViewService,
    protected shiftService: ShiftService,
    protected dayStatusService: DayStatusService,
    private eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.isSaving = false;
    this.leaveDateFrom = new Date();
    this.leaveDateFrom.setHours(0, 0, 0, 0);
    this.leaveDateTo = new Date();
    this.leaveDateTo.setHours(0, 0, 0, 0);
    this.leaveTimeFrom = new Date();
    this.leaveTimeTo = new Date();
    this.leaveEntryHr = new LeaveEntryHr();
    this.leaveEntryHr.flag = 'E';
    this.leaveTypeMasterService.findByType('L').subscribe(
      (res: HttpResponse<ILeaveTypeMaster[]>) => {
        this.leavetypemasters = res.body;
      },
      (res: HttpErrorResponse) => this.onError(res.message)
    );
    this.registerChangeInSearchUser();
  }

  previousState() {
    window.history.back();
  }

  registerChangeInSearchUser() {
    this.eventManager.subscribe('selectedUserLinkCreation', message => {
      const userDetails = message.content;
      this.cardNo = userDetails.cardNo;
      this.shiftService.find(message.content.sftCode).subscribe((res3: HttpResponse<IShift>) => {
        this.shift = res3.body;
      });
      this.name = userDetails.name + '(' + userDetails.cardNo + ')';
      this.userService.find(userDetails.cardNo).subscribe(user => {
        this.leaveEntryHr.userCode = user.body;
        this.loadTypes();
      });
    });
  }

  changeDate(leaveSubTypeMaster) {
    let ctr = 0;
    if (this.leaveEntryHr.leaveTypeMaster.leaveCode === 'SP') {
      this.isSubTypeDisabled = true;
      this.isLeaveDateTo = true;
      this.leaveDateTo = this.leaveDateFrom;
      this.isTimeDisabled = true;
      const date = moment(this.leaveDateFrom, DATE_TIME_FORMAT).format('DD.MM.YYYY');
      this.dayStatusService.find(date).subscribe((res: HttpResponse<IDayStatus>) => {
        if (res.body !== null) {
          if (res.body.inTm !== null && res.body.inTm.trim().length > 0) {
            const inTmarr = res.body.inTm.split(':');
            if (moment.isMoment(this.leaveDateFrom)) {
              this.leaveDateFrom = this.leaveDateFrom.toDate();
            }
            this.leaveTimeFrom = new Date(this.leaveDateFrom.setHours(Number(inTmarr[0]), Number(inTmarr[1]), 0, 0));
            this.inTimeDisabled = true;
          } else {
            ++ctr;
            if (moment.isMoment(this.leaveDateFrom)) {
              this.leaveDateFrom = this.leaveDateFrom.toDate();
            }
            const shiftInTime = this.shift.intime;
            const intarr = shiftInTime.split(':');
            this.leaveTimeFrom = new Date(this.leaveDateFrom.setHours(Number(intarr[0]), Number(intarr[1]), 0, 0));
            this.inTimeDisabled = false;
            this.leaveEntryHr.missPunchType = 'F';
          }
          if (res.body.outTm !== null && res.body.outTm.trim().length > 0) {
            if (moment.isMoment(this.leaveDateTo)) {
              this.leaveDateTo = this.leaveDateTo.toDate();
            }
            const outTmarr = res.body.outTm.split(':');
            this.leaveTimeTo = new Date(this.leaveDateTo.setHours(Number(outTmarr[0]), Number(outTmarr[1]), 0, 0));
            this.outTimeDisabled = true;
          } else {
            ++ctr;
            if (moment.isMoment(this.leaveDateTo)) {
              this.leaveDateTo = this.leaveDateTo.toDate();
            }
            const shiftOutTime = this.shift.outtime;
            const outarr = shiftOutTime.split(':');
            this.leaveTimeTo = new Date(this.leaveDateTo.setHours(Number(outarr[0]), Number(outarr[1]), 0, 0));
            this.outTimeDisabled = false;
            this.leaveEntryHr.missPunchType = 'S';
          }
          if (ctr === 2) {
            this.leaveEntryHr.missPunchType = 'A';
            this.leavesubtypemasters.forEach(leavesubtypemaster => {
              if (leavesubtypemaster.subTypeCode === 'DP') {
                this.leaveEntryHr.leaveSubTypeMaster = leavesubtypemaster;
              }
            });
          } else {
            this.leavesubtypemasters.forEach(leavesubtypemaster => {
              if (leavesubtypemaster.subTypeCode === 'SP') {
                this.leaveEntryHr.leaveSubTypeMaster = leavesubtypemaster;
              }
            });
          }
        }
      });
    } else if (leaveSubTypeMaster !== undefined) {
      if (leaveSubTypeMaster.subTypeName === 'FIRST HALF' || leaveSubTypeMaster.subTypeName === 'SECOND HALF') {
        this.isLeaveDateTo = true;
        this.leaveDateTo = this.leaveDateFrom;
        this.leaveEntryHr.noDays = 0.5;
      } else {
        this.isLeaveDateTo = false;
      }
    } else {
      this.isLeaveDateTo = false;
    }
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
    if (this.leaveEntryHr.leaveSubTypeMaster.subTypeName === 'FULL DAY') {
      const intarr = inTime.split(':');
      this.leaveTimeFrom = new Date(this.leaveDateFrom.setHours(Number(intarr[0]), Number(intarr[1]), 0, 0));

      const outarr = outTime.split(':');
      this.leaveTimeTo = new Date(this.leaveDateTo.setHours(Number(outarr[0]), Number(outarr[1]), 0, 0));
      this.isTimeDisabled = true;
    } else if (this.leaveEntryHr.leaveSubTypeMaster.subTypeName === 'FIRST HALF') {
      const intarr = inTime.split(':');
      this.leaveTimeFrom = new Date(this.leaveDateFrom.setHours(Number(intarr[0]), Number(intarr[1]), 0, 0));

      const br1arr = br1.split(':');
      this.leaveTimeTo = new Date(this.leaveDateTo.setHours(Number(br1arr[0]), Number(br1arr[1]), 0, 0));
      this.isTimeDisabled = true;
      this.leaveEntryHr.noDays = 0.5;
    } else if (this.leaveEntryHr.leaveSubTypeMaster.subTypeName === 'SECOND HALF') {
      const end1arr = end1.split(':');
      this.leaveTimeFrom = new Date(this.leaveDateFrom.setHours(Number(end1arr[0]), Number(end1arr[1]), 0, 0));

      const outarr = outTime.split(':');
      this.leaveTimeTo = new Date(this.leaveDateTo.setHours(Number(outarr[0]), Number(outarr[1]), 0, 0));
      this.isTimeDisabled = true;
      this.leaveEntryHr.noDays = 0.5;
    } else if (this.leaveEntryHr.leaveSubTypeMaster.subTypeName === 'IN BETWEEN') {
      const intarr = inTime.split(':');
      this.leaveTimeFrom = new Date(this.leaveDateFrom.setHours(Number(intarr[0]), Number(intarr[1]), 0, 0));

      const outarr = outTime.split(':');
      this.leaveTimeTo = new Date(this.leaveDateTo.setHours(Number(outarr[0]), Number(outarr[1]), 0, 0));
      this.isTimeDisabled = false;
    }
  }

  save() {
    this.isSaving = true;
    this.leaveEntryHr.leaveDateFrom = this.leaveDateFrom != null ? moment(this.leaveDateFrom, DATE_TIME_FORMAT).startOf('day') : null;
    this.leaveEntryHr.leaveDateTo = this.leaveDateTo != null ? moment(this.leaveDateTo, DATE_TIME_FORMAT).startOf('day') : null;
    this.leaveEntryHr.createdDate = this.createdDate != null ? moment(this.createdDate, DATE_TIME_FORMAT) : null;
    this.leaveEntryHr.hodApprovedDate = this.hodApprovedDate != null ? moment(this.hodApprovedDate, DATE_TIME_FORMAT) : null;
    this.leaveEntryHr.hrApprovedDate = this.hrApprovedDate != null ? moment(this.hrApprovedDate, DATE_TIME_FORMAT) : null;
    this.leaveEntryHr.leaveTimeFrom = this.leaveTimeFrom != null ? moment(this.leaveTimeFrom, DATE_TIME_FORMAT) : null;
    this.leaveEntryHr.leaveTimeTo = this.leaveTimeTo != null ? moment(this.leaveTimeTo, DATE_TIME_FORMAT) : null;
    if (this.leaveEntryHr.leaveDateTo < this.leaveEntryHr.leaveDateFrom) {
      this.snotifyService.error('Date To must be greater than Date From', '', toastConfig);
      this.isSaving = false;
      return;
    } else if (this.leaveEntryHr.leaveDateTo.format('M') !== this.leaveEntryHr.leaveDateFrom.format('M')) {
      this.snotifyService.error('From & To Date month must be same..', '', toastConfig);
      this.isSaving = false;
      return;
    } else if (
      this.leaveEntryHr.leaveTypeMaster !== null &&
      (this.leaveEntryHr.leaveTypeMaster.leaveCode === 'CL' ||
        this.leaveEntryHr.leaveTypeMaster.leaveCode === 'SL' ||
        this.leaveEntryHr.leaveTypeMaster.leaveCode === 'EL') &&
      this.leaveEntryHr.leaveSubTypeMaster.subTypeName === 'FULL DAY' &&
      this.leaveEntryHr.leaveDateTo.diff(this.leaveEntryHr.leaveDateFrom, 'days') + 1 > this.balance
    ) {
      this.snotifyService.error('You do not have balance..', '', toastConfig);
      this.isSaving = false;
      return;
    } else if (
      this.leaveEntryHr.leaveTypeMaster !== null &&
      (this.leaveEntryHr.leaveTypeMaster.leaveCode === 'CL' ||
        this.leaveEntryHr.leaveTypeMaster.leaveCode === 'SL' ||
        this.leaveEntryHr.leaveTypeMaster.leaveCode === 'EL') &&
      (this.leaveEntryHr.leaveSubTypeMaster.subTypeName === 'FIRST HALF' ||
        this.leaveEntryHr.leaveSubTypeMaster.subTypeName === 'SECOND HALF') &&
      this.leaveEntryHr.leaveDateTo.diff(this.leaveEntryHr.leaveDateFrom, 'days') + 0.5 > this.balance
    ) {
      this.snotifyService.error('You do not have balance..', '', toastConfig);
      this.isSaving = false;
      return;
    } else {
      if (
        this.leaveEntryHr.leaveSubTypeMaster.subTypeName === 'FIRST HALF' ||
        this.leaveEntryHr.leaveSubTypeMaster.subTypeName === 'SECOND HALF'
      ) {
        this.leaveEntryHr.noDays = 0.5;
      } else {
        this.leaveEntryHr.noDays = this.leaveEntryHr.leaveDateTo.diff(this.leaveEntryHr.leaveDateFrom, 'days') + 1;
      }
      this.subscribeToSaveResponse(this.leaveEntryHrService.create(this.leaveEntryHr));
    }
  }

  loadTypes() {
    if (moment.isMoment(this.leaveDateFrom)) {
      this.leaveDateFrom = this.leaveDateFrom.toDate();
    }

    if (moment.isMoment(this.leaveDateTo)) {
      this.leaveDateTo = this.leaveDateTo.toDate();
    }
    if (this.leaveEntryHr.leaveTypeMaster === 'undefined') {
      this.leavesubtypemasters = [];
      this.leaveEntryHr.leaveSubTypeMaster = undefined;
      this.leaveEntryHr.leaveTypeMaster = undefined;
    } else if (
      this.leaveEntryHr.leaveTypeMaster !== undefined &&
      this.leaveEntryHr.leaveTypeMaster !== null &&
      this.leaveEntryHr.leaveTypeMaster
    ) {
      this.leaveSubTypeMasterService.queryByLeaveType(this.leaveEntryHr.leaveTypeMaster.id).subscribe(
        (res: HttpResponse<ILeaveSubTypeMaster[]>) => {
          this.leavesubtypemasters = res.body;
          this.leaveEntryHr.leaveSubTypeMaster = res.body[0];
          const inTime = this.shift.intime;
          const outTime = this.shift.outtime;
          const br1 = this.shift.br1;
          const end1 = this.shift.end1;
          this.inTimeDisabled = true;
          this.outTimeDisabled = true;
          if (this.leaveEntryHr.leaveSubTypeMaster.subTypeName === 'FULL DAY') {
            const intarr = inTime.split(':');
            this.leaveTimeFrom = new Date(this.leaveDateFrom.setHours(Number(intarr[0]), Number(intarr[1]), 0, 0));

            const outarr = outTime.split(':');
            this.leaveTimeTo = new Date(this.leaveDateTo.setHours(Number(outarr[0]), Number(outarr[1]), 0, 0));
            this.isTimeDisabled = true;
          } else if (this.leaveEntryHr.leaveSubTypeMaster.subTypeName === 'FIRST HALF') {
            const intarr = inTime.split(':');
            this.leaveTimeFrom = new Date(this.leaveDateFrom.setHours(Number(intarr[0]), Number(intarr[1]), 0, 0));

            const br1arr = br1.split(':');
            this.leaveTimeTo = new Date(this.leaveDateTo.setHours(Number(br1arr[0]), Number(br1arr[1]), 0, 0));
            this.isTimeDisabled = true;
            this.leaveEntryHr.noDays = 0.5;
          } else if (this.leaveEntryHr.leaveSubTypeMaster.subTypeName === 'SECOND HALF') {
            const end1arr = end1.split(':');
            this.leaveTimeFrom = new Date(this.leaveDateFrom.setHours(Number(end1arr[0]), Number(end1arr[1]), 0, 0));

            const outarr = outTime.split(':');
            this.leaveTimeTo = new Date(this.leaveDateTo.setHours(Number(outarr[0]), Number(outarr[1]), 0, 0));
            this.isTimeDisabled = true;
            this.leaveEntryHr.noDays = 0.5;
          }
        },
        (res: HttpErrorResponse) => this.onError(res.message)
      );
      const master = new Master();
      master.id = this.cardNo;
      master.desc = this.leaveEntryHr.leaveTypeMaster.leaveCode;
      this.leaveEntryHrService.leaveBalance(master).subscribe(
        (res: HttpResponse<ILeaveStatus>) => {
          this.balance = res.body.leaveBalance;
        },
        (res: HttpErrorResponse) => this.onError(res.message)
      );
      let ctr = 0;
      if (this.leaveEntryHr.leaveTypeMaster.leaveCode === 'SP') {
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
                this.leaveTimeFrom = new Date(this.leaveDateFrom.setHours(Number(intarr[0]), Number(intarr[1]), 0, 0));
                this.inTimeDisabled = false;
                this.leaveEntryHr.missPunchType = 'F';
              }
              if (res.body.outTm !== null && res.body.outTm.trim().length > 0) {
                const outTmarr = res.body.outTm.split(':');
                this.leaveTimeTo = new Date(this.leaveDateTo.setHours(Number(outTmarr[0]), Number(outTmarr[1]), 0, 0));
                this.outTimeDisabled = true;
              } else {
                ++ctr;
                const outTime = this.shift.outtime;
                const outarr = outTime.split(':');
                this.leaveTimeTo = new Date(this.leaveDateTo.setHours(Number(outarr[0]), Number(outarr[1]), 0, 0));
                this.outTimeDisabled = false;
                this.leaveEntryHr.missPunchType = 'S';
              }
              if (ctr === 2) {
                this.leaveEntryHr.missPunchType = 'A';
                this.leavesubtypemasters.forEach(leavesubtypemaster => {
                  if (leavesubtypemaster.subTypeCode === 'DP') {
                    this.leaveEntryHr.leaveSubTypeMaster = leavesubtypemaster;
                  }
                });
              } else {
                this.leavesubtypemasters.forEach(leavesubtypemaster => {
                  if (leavesubtypemaster.subTypeName === 'SP') {
                    this.leaveEntryHr.leaveSubTypeMaster = leavesubtypemaster;
                  }
                });
              }
            }
          });
      }
    } else {
      this.leavesubtypemasters = [];
      this.leaveEntryHr.leaveSubTypeMaster = undefined;
      this.leaveEntryHr.leaveTypeMaster = undefined;
    }
  }

  searchEmployee() {
    this.ngbModalRef = this.modalService.open(EmployeeSearchComponent as Component, {
      size: 'lg',
      backdrop: 'static',
      windowClass: 'xlModal'
    });
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ILeaveEntryHr>>) {
    result.subscribe((res: HttpResponse<ILeaveEntryHr>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError(res.headers));
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
}
