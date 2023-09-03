import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { IOperationMaster } from 'app/shared/model/operation-master.model';
import { OperationMasterService } from './operation-master.service';

@Component({
  selector: 'jhi-operation-master-update',
  templateUrl: './operation-master-update.component.html'
})
export class OperationMasterUpdateComponent implements OnInit {
  operationMaster: IOperationMaster;
  isSaving: boolean;
  createdDate: string;
  lastUpdatedDate: string;

  constructor(protected operationMasterService: OperationMasterService, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ operationMaster }) => {
      this.operationMaster = operationMaster;
      this.createdDate = this.operationMaster.createdDate != null ? this.operationMaster.createdDate.format(DATE_TIME_FORMAT) : null;
      this.lastUpdatedDate =
        this.operationMaster.lastUpdatedDate != null ? this.operationMaster.lastUpdatedDate.format(DATE_TIME_FORMAT) : null;
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    this.operationMaster.createdDate = this.createdDate != null ? moment(this.createdDate, DATE_TIME_FORMAT) : null;
    this.operationMaster.lastUpdatedDate = this.lastUpdatedDate != null ? moment(this.lastUpdatedDate, DATE_TIME_FORMAT) : null;
    if (this.operationMaster.id !== undefined) {
      this.subscribeToSaveResponse(this.operationMasterService.update(this.operationMaster));
    } else {
      this.subscribeToSaveResponse(this.operationMasterService.create(this.operationMaster));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IOperationMaster>>) {
    result.subscribe((res: HttpResponse<IOperationMaster>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
}
