import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { ITdsYearMaster } from 'app/shared/model/tds-year-master.model';
import { TdsYearMasterService } from './tds-year-master.service';

@Component({
  selector: 'jhi-tds-year-master-update',
  templateUrl: './tds-year-master-update.component.html'
})
export class TdsYearMasterUpdateComponent implements OnInit {
  tdsYearMaster: ITdsYearMaster;
  isSaving: boolean;
  createdDate: string;

  constructor(protected tdsYearMasterService: TdsYearMasterService, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ tdsYearMaster }) => {
      this.tdsYearMaster = tdsYearMaster;
      this.createdDate = this.tdsYearMaster.createdDate != null ? this.tdsYearMaster.createdDate.format(DATE_TIME_FORMAT) : null;
    });
    if (this.tdsYearMaster.tempLock === undefined || this.tdsYearMaster.tempLock === null) {
      this.tdsYearMaster.tempLock = false;
    }
    if (this.tdsYearMaster.uploadDoc === undefined || this.tdsYearMaster.uploadDoc === null) {
      this.tdsYearMaster.uploadDoc = false;
    }
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    this.tdsYearMaster.createdDate = this.createdDate != null ? moment(this.createdDate, DATE_TIME_FORMAT) : null;
    if (this.tdsYearMaster.id !== undefined) {
      this.subscribeToSaveResponse(this.tdsYearMasterService.update(this.tdsYearMaster));
    } else {
      this.subscribeToSaveResponse(this.tdsYearMasterService.create(this.tdsYearMaster));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITdsYearMaster>>) {
    result.subscribe((res: HttpResponse<ITdsYearMaster>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
}
