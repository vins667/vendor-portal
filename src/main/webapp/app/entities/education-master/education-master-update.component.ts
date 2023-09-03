import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { IEducationMaster } from 'app/shared/model/education-master.model';
import { EducationMasterService } from './education-master.service';

@Component({
  selector: 'jhi-education-master-update',
  templateUrl: './education-master-update.component.html'
})
export class EducationMasterUpdateComponent implements OnInit {
  educationMaster: IEducationMaster;
  isSaving: boolean;
  createdDate: string;
  lastUpdatedDate: string;

  constructor(protected educationMasterService: EducationMasterService, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ educationMaster }) => {
      this.educationMaster = educationMaster;
      this.createdDate = this.educationMaster.createdDate != null ? this.educationMaster.createdDate.format(DATE_TIME_FORMAT) : null;
      this.lastUpdatedDate =
        this.educationMaster.lastUpdatedDate != null ? this.educationMaster.lastUpdatedDate.format(DATE_TIME_FORMAT) : null;
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    this.educationMaster.createdDate = this.createdDate != null ? moment(this.createdDate, DATE_TIME_FORMAT) : null;
    this.educationMaster.lastUpdatedDate = this.lastUpdatedDate != null ? moment(this.lastUpdatedDate, DATE_TIME_FORMAT) : null;
    if (this.educationMaster.id !== undefined) {
      this.subscribeToSaveResponse(this.educationMasterService.update(this.educationMaster));
    } else {
      this.subscribeToSaveResponse(this.educationMasterService.create(this.educationMaster));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IEducationMaster>>) {
    result.subscribe((res: HttpResponse<IEducationMaster>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
}
