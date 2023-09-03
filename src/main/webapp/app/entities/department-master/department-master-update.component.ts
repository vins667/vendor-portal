import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { IDepartmentMaster } from 'app/shared/model/department-master.model';
import { DepartmentMasterService } from './department-master.service';

@Component({
  selector: 'jhi-department-master-update',
  templateUrl: './department-master-update.component.html'
})
export class DepartmentMasterUpdateComponent implements OnInit {
  departmentMaster: IDepartmentMaster;
  isSaving: boolean;
  createdDate: string;
  lastUpdatedDate: string;

  constructor(protected departmentMasterService: DepartmentMasterService, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ departmentMaster }) => {
      this.departmentMaster = departmentMaster;
      this.createdDate = this.departmentMaster.createdDate != null ? this.departmentMaster.createdDate.format(DATE_TIME_FORMAT) : null;
      this.lastUpdatedDate =
        this.departmentMaster.lastUpdatedDate != null ? this.departmentMaster.lastUpdatedDate.format(DATE_TIME_FORMAT) : null;
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    this.departmentMaster.createdDate = this.createdDate != null ? moment(this.createdDate, DATE_TIME_FORMAT) : null;
    this.departmentMaster.lastUpdatedDate = this.lastUpdatedDate != null ? moment(this.lastUpdatedDate, DATE_TIME_FORMAT) : null;
    if (this.departmentMaster.id !== undefined) {
      this.subscribeToSaveResponse(this.departmentMasterService.update(this.departmentMaster));
    } else {
      this.subscribeToSaveResponse(this.departmentMasterService.create(this.departmentMaster));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IDepartmentMaster>>) {
    result.subscribe((res: HttpResponse<IDepartmentMaster>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
}
