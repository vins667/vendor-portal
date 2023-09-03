import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { IVcutSessionMaster, VcutSessionMaster } from 'app/shared/model/vcut-session-master.model';
import { VcutSessionMasterService } from './vcut-session-master.service';
import { IVcutSessionDetails, VcutSessionDetails } from 'app/shared/model/vcut-session-details.model';
import { DateTimeAdapter, OWL_DATE_TIME_FORMATS, OWL_DATE_TIME_LOCALE } from 'ng-pick-datetime';
import { MomentDateTimeAdapter } from 'ng-pick-datetime-moment';
import { SnotifyService, SnotifyPosition } from 'ng-snotify';
import { toastConfig } from 'app/core/toast/toast-config';
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
  selector: 'jhi-vcut-session-master-update',
  templateUrl: './vcut-session-master-update.component.html',
  encapsulation: ViewEncapsulation.None,
  providers: [
    // `MomentDateTimeAdapter` and `OWL_MOMENT_DATE_TIME_FORMATS` can be automatically provided by importing
    // `OwlMomentDateTimeModule` in your applications root module. We provide it at the component level
    // here, due to limitations of our example generation script.
    { provide: DateTimeAdapter, useClass: MomentDateTimeAdapter, deps: [OWL_DATE_TIME_LOCALE] },
    { provide: OWL_DATE_TIME_FORMATS, useValue: MY_MOMENT_FORMATS }
  ]
})
export class VcutSessionMasterUpdateComponent implements OnInit {
  vcutSessionMaster: IVcutSessionMaster;
  isSaving: boolean;
  dayStartTime: string;
  createdDate: string;
  lastUpdatedDate: string;
  leaveTimeFrom: any;
  mask = [/[0-2]/, /[0-9]/, ':', /[0-5]/, /[0-9]/];

  constructor(
    protected vcutSessionMasterService: VcutSessionMasterService,
    protected activatedRoute: ActivatedRoute,
    protected snotifyService: SnotifyService
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.leaveTimeFrom = new Date();
    this.vcutSessionMaster = new VcutSessionMaster();
    this.activatedRoute.data.subscribe(({ vcutSessionMaster }) => {
      this.vcutSessionMaster = vcutSessionMaster;
      this.leaveTimeFrom = this.vcutSessionMaster.dayStartTime != null ? this.vcutSessionMaster.dayStartTime : null;
      if (
        this.vcutSessionMaster !== null &&
        this.vcutSessionMaster.id !== undefined &&
        this.vcutSessionMaster.vcutSessionDetails &&
        this.vcutSessionMaster.vcutSessionDetails.length > 0
      ) {
      } else {
        this.getPrixedVcutSessionDetails();
      }
    });
  }

  getPrixedVcutSessionDetails() {
    this.vcutSessionMaster.vcutSessionDetails = new Array<IVcutSessionDetails>();
    for (let i = 0; i < 10; i++) {
      this.vcutSessionMaster.vcutSessionDetails.push(new VcutSessionDetails());
    }
  }

  addRow() {
    if (this.vcutSessionMaster.vcutSessionDetails) {
      this.vcutSessionMaster.vcutSessionDetails.push(new VcutSessionDetails());
    }
  }

  removeRow(index: any) {
    if (this.vcutSessionMaster.vcutSessionDetails[index].id !== undefined) {
      this.deleteRow(this.vcutSessionMaster.vcutSessionDetails[index].id, index);
    } else {
      this.vcutSessionMaster.vcutSessionDetails.splice(index, 1);
    }
  }

  deleteRow(id, index) {
    this.snotifyService.confirm('Are you sure to delete row?', 'Confirm', {
      timeout: 25000,
      showProgressBar: false,
      closeOnClick: false,
      pauseOnHover: true,
      position: SnotifyPosition.centerTop,
      buttons: [
        { text: 'Yes', action: toast => this.delete(toast, id, index), bold: false },
        { text: 'No', action: toast => this.snotifyService.remove(toast.id) }
      ]
    });
  }
  delete(toast, id, index) {
    /* this.vcutSessionMasterService.deleteDetail(id).subscribe(any => {
          this.snotifyService.remove(toast.id);
          this.vcutSessionMaster .vcutSessionDetails.splice(index, 1);
        }); */
  }
  getHHMints(hours, minutes) {
    if (hours && hours.value !== null && hours.value !== undefined) {
      this.vcutSessionMaster.totalMinsPerDay = 60 * hours.value + Number(minutes.value);
    }
  }

  calTimeDifference() {
    let value = 0;
    this.vcutSessionMaster.vcutSessionDetails.forEach(vcutSessionDetail => {
      if (vcutSessionDetail.startTime && vcutSessionDetail.endTime) {
        const start = vcutSessionDetail.startTime.split(':');
        const end = vcutSessionDetail.endTime.split(':');
        const time1 = parseInt(start[0], 10) * 60 + parseInt(start[1], 10);
        const time2 = parseInt(end[0], 10) * 60 + parseInt(end[1], 10);
        const time3 = time2 - time1;
        vcutSessionDetail.duration = time3;
        value += time3;
        vcutSessionDetail.cumulativeMins = value;
      }
    });
  }
  previousState() {
    window.history.back();
  }

  getVlidateDetails() {
    if (!this.vcutSessionMaster.vcutSessionDetails[0].startTime) {
      this.snotifyService.error("Start Time ca'nt be empty", '', toastConfig);
      return false;
    }
    return true;
  }

  save() {
    if (this.getVlidateDetails() !== false) {
      this.isSaving = true;
      this.vcutSessionMaster.dayStartTime = this.leaveTimeFrom != null ? moment(this.leaveTimeFrom, DATE_TIME_FORMAT) : null;
      this.vcutSessionMaster.createdDate = this.createdDate != null ? moment(this.createdDate, DATE_TIME_FORMAT) : null;
      this.vcutSessionMaster.lastUpdatedDate = this.lastUpdatedDate != null ? moment(this.lastUpdatedDate, DATE_TIME_FORMAT) : null;
      if (this.vcutSessionMaster.id !== undefined) {
        this.subscribeToSaveResponse(this.vcutSessionMasterService.update(this.vcutSessionMaster));
      } else {
        this.subscribeToSaveResponse(this.vcutSessionMasterService.create(this.vcutSessionMaster));
      }
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IVcutSessionMaster>>) {
    result.subscribe((res: HttpResponse<IVcutSessionMaster>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
}
