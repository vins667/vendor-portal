import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { IEducationTypeMaster } from 'app/shared/model/education-type-master.model';
import { EducationTypeMasterService } from './education-type-master.service';

@Component({
  selector: 'jhi-education-type-master-update',
  templateUrl: './education-type-master-update.component.html'
})
export class EducationTypeMasterUpdateComponent implements OnInit {
  educationTypeMaster: IEducationTypeMaster;
  isSaving: boolean;
  createdDate: string;
  lastUpdatedDate: string;

  constructor(protected educationTypeMasterService: EducationTypeMasterService, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ educationTypeMaster }) => {
      this.educationTypeMaster = educationTypeMaster;
      this.createdDate =
        this.educationTypeMaster.createdDate != null ? this.educationTypeMaster.createdDate.format(DATE_TIME_FORMAT) : null;
      this.lastUpdatedDate =
        this.educationTypeMaster.lastUpdatedDate != null ? this.educationTypeMaster.lastUpdatedDate.format(DATE_TIME_FORMAT) : null;
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    this.educationTypeMaster.createdDate = this.createdDate != null ? moment(this.createdDate, DATE_TIME_FORMAT) : null;
    this.educationTypeMaster.lastUpdatedDate = this.lastUpdatedDate != null ? moment(this.lastUpdatedDate, DATE_TIME_FORMAT) : null;
    if (this.educationTypeMaster.id !== undefined) {
      this.subscribeToSaveResponse(this.educationTypeMasterService.update(this.educationTypeMaster));
    } else {
      this.subscribeToSaveResponse(this.educationTypeMasterService.create(this.educationTypeMaster));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IEducationTypeMaster>>) {
    result.subscribe((res: HttpResponse<IEducationTypeMaster>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
}
