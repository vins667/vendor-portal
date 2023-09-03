import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { IMobileAttendance } from 'app/shared/model/mobile-attendance.model';
import { MobileAttendanceService } from './mobile-attendance.service';

@Component({
  selector: 'jhi-mobile-attendance-update',
  templateUrl: './mobile-attendance-update.component.html'
})
export class MobileAttendanceUpdateComponent implements OnInit {
  mobileAttendance: IMobileAttendance;
  isSaving: boolean;
  attendanceDate: string;
  createdDate: string;

  constructor(protected mobileAttendanceService: MobileAttendanceService, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mobileAttendance }) => {
      this.mobileAttendance = mobileAttendance;
      this.attendanceDate =
        this.mobileAttendance.attendanceDate != null ? this.mobileAttendance.attendanceDate.format(DATE_TIME_FORMAT) : null;
      this.createdDate = this.mobileAttendance.createdDate != null ? this.mobileAttendance.createdDate.format(DATE_TIME_FORMAT) : null;
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    this.mobileAttendance.attendanceDate = this.attendanceDate != null ? moment(this.attendanceDate, DATE_TIME_FORMAT) : null;
    this.mobileAttendance.createdDate = this.createdDate != null ? moment(this.createdDate, DATE_TIME_FORMAT) : null;
    if (this.mobileAttendance.id !== undefined) {
      this.subscribeToSaveResponse(this.mobileAttendanceService.update(this.mobileAttendance));
    } else {
      this.subscribeToSaveResponse(this.mobileAttendanceService.create(this.mobileAttendance));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMobileAttendance>>) {
    result.subscribe((res: HttpResponse<IMobileAttendance>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
}
