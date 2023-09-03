import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { IDesignationMaster } from 'app/shared/model/designation-master.model';
import { DesignationMasterService } from './designation-master.service';

@Component({
  selector: 'jhi-designation-master-update',
  templateUrl: './designation-master-update.component.html'
})
export class DesignationMasterUpdateComponent implements OnInit {
  designationMaster: IDesignationMaster;
  isSaving: boolean;
  createdDate: string;
  lastUpdatedDate: string;

  constructor(protected designationMasterService: DesignationMasterService, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ designationMaster }) => {
      this.designationMaster = designationMaster;
      this.createdDate = this.designationMaster.createdDate != null ? this.designationMaster.createdDate.format(DATE_TIME_FORMAT) : null;
      this.lastUpdatedDate =
        this.designationMaster.lastUpdatedDate != null ? this.designationMaster.lastUpdatedDate.format(DATE_TIME_FORMAT) : null;
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    this.designationMaster.createdDate = this.createdDate != null ? moment(this.createdDate, DATE_TIME_FORMAT) : null;
    this.designationMaster.lastUpdatedDate = this.lastUpdatedDate != null ? moment(this.lastUpdatedDate, DATE_TIME_FORMAT) : null;
    if (this.designationMaster.id !== undefined) {
      this.subscribeToSaveResponse(this.designationMasterService.update(this.designationMaster));
    } else {
      this.subscribeToSaveResponse(this.designationMasterService.create(this.designationMaster));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IDesignationMaster>>) {
    result.subscribe((res: HttpResponse<IDesignationMaster>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
}
