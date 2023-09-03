import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { IMachineMaster } from 'app/shared/model/machine-master.model';
import { MachineMasterService } from './machine-master.service';

@Component({
  selector: 'jhi-machine-master-update',
  templateUrl: './machine-master-update.component.html'
})
export class MachineMasterUpdateComponent implements OnInit {
  machineMaster: IMachineMaster;
  isSaving: boolean;
  createdDate: string;
  lastUpdatedDate: string;

  constructor(protected machineMasterService: MachineMasterService, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ machineMaster }) => {
      this.machineMaster = machineMaster;
      this.createdDate = this.machineMaster.createdDate != null ? this.machineMaster.createdDate.format(DATE_TIME_FORMAT) : null;
      this.lastUpdatedDate =
        this.machineMaster.lastUpdatedDate != null ? this.machineMaster.lastUpdatedDate.format(DATE_TIME_FORMAT) : null;
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    this.machineMaster.createdDate = this.createdDate != null ? moment(this.createdDate, DATE_TIME_FORMAT) : null;
    this.machineMaster.lastUpdatedDate = this.lastUpdatedDate != null ? moment(this.lastUpdatedDate, DATE_TIME_FORMAT) : null;
    if (this.machineMaster.id !== undefined) {
      this.subscribeToSaveResponse(this.machineMasterService.update(this.machineMaster));
    } else {
      this.subscribeToSaveResponse(this.machineMasterService.create(this.machineMaster));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMachineMaster>>) {
    result.subscribe((res: HttpResponse<IMachineMaster>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
}
