import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { ITurnoverMaster } from 'app/shared/model/turnover-master.model';
import { TurnoverMasterService } from './turnover-master.service';

@Component({
  selector: 'jhi-turnover-master-update',
  templateUrl: './turnover-master-update.component.html'
})
export class TurnoverMasterUpdateComponent implements OnInit {
  turnoverMaster: ITurnoverMaster;
  isSaving: boolean;
  createdDate: string;
  lastUpdatedDate: string;

  constructor(protected turnoverMasterService: TurnoverMasterService, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ turnoverMaster }) => {
      this.turnoverMaster = turnoverMaster;
      this.createdDate = this.turnoverMaster.createdDate != null ? this.turnoverMaster.createdDate.format(DATE_TIME_FORMAT) : null;
      this.lastUpdatedDate =
        this.turnoverMaster.lastUpdatedDate != null ? this.turnoverMaster.lastUpdatedDate.format(DATE_TIME_FORMAT) : null;
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    this.turnoverMaster.createdDate = this.createdDate != null ? moment(this.createdDate, DATE_TIME_FORMAT) : null;
    this.turnoverMaster.lastUpdatedDate = this.lastUpdatedDate != null ? moment(this.lastUpdatedDate, DATE_TIME_FORMAT) : null;
    if (this.turnoverMaster.id !== undefined) {
      this.subscribeToSaveResponse(this.turnoverMasterService.update(this.turnoverMaster));
    } else {
      this.subscribeToSaveResponse(this.turnoverMasterService.create(this.turnoverMaster));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITurnoverMaster>>) {
    result.subscribe((res: HttpResponse<ITurnoverMaster>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
}
