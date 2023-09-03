import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { IIgnoreSoftwareMaster } from 'app/shared/model/ignore-software-master.model';
import { IgnoreSoftwareMasterService } from './ignore-software-master.service';

@Component({
  selector: 'jhi-ignore-software-master-update',
  templateUrl: './ignore-software-master-update.component.html'
})
export class IgnoreSoftwareMasterUpdateComponent implements OnInit {
  ignoreSoftwareMaster: IIgnoreSoftwareMaster;
  isSaving: boolean;
  createdDate: string;
  lastUpdatedDate: string;

  constructor(protected ignoreSoftwareMasterService: IgnoreSoftwareMasterService, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ ignoreSoftwareMaster }) => {
      this.ignoreSoftwareMaster = ignoreSoftwareMaster;
      this.createdDate =
        this.ignoreSoftwareMaster.createdDate != null ? this.ignoreSoftwareMaster.createdDate.format(DATE_TIME_FORMAT) : null;
      this.lastUpdatedDate =
        this.ignoreSoftwareMaster.lastUpdatedDate != null ? this.ignoreSoftwareMaster.lastUpdatedDate.format(DATE_TIME_FORMAT) : null;
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    this.ignoreSoftwareMaster.createdDate = this.createdDate != null ? moment(this.createdDate, DATE_TIME_FORMAT) : null;
    this.ignoreSoftwareMaster.lastUpdatedDate = this.lastUpdatedDate != null ? moment(this.lastUpdatedDate, DATE_TIME_FORMAT) : null;
    if (this.ignoreSoftwareMaster.id !== undefined) {
      this.subscribeToSaveResponse(this.ignoreSoftwareMasterService.update(this.ignoreSoftwareMaster));
    } else {
      this.subscribeToSaveResponse(this.ignoreSoftwareMasterService.create(this.ignoreSoftwareMaster));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IIgnoreSoftwareMaster>>) {
    result.subscribe((res: HttpResponse<IIgnoreSoftwareMaster>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
}
