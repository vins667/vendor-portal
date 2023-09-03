import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { IDelPlaceMaster } from 'app/shared/model/del-place-master.model';
import { DelPlaceMasterService } from './del-place-master.service';

@Component({
  selector: 'jhi-del-place-master-update',
  templateUrl: './del-place-master-update.component.html'
})
export class DelPlaceMasterUpdateComponent implements OnInit {
  delPlaceMaster: IDelPlaceMaster;
  isSaving: boolean;
  createdDate: string;
  lastUpdatedDate: string;

  constructor(protected delPlaceMasterService: DelPlaceMasterService, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ delPlaceMaster }) => {
      this.delPlaceMaster = delPlaceMaster;
      this.createdDate = this.delPlaceMaster.createdDate != null ? this.delPlaceMaster.createdDate.format(DATE_TIME_FORMAT) : null;
      this.lastUpdatedDate =
        this.delPlaceMaster.lastUpdatedDate != null ? this.delPlaceMaster.lastUpdatedDate.format(DATE_TIME_FORMAT) : null;
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    this.delPlaceMaster.createdDate = this.createdDate != null ? moment(this.createdDate, DATE_TIME_FORMAT) : null;
    this.delPlaceMaster.lastUpdatedDate = this.lastUpdatedDate != null ? moment(this.lastUpdatedDate, DATE_TIME_FORMAT) : null;
    if (this.delPlaceMaster.id !== undefined) {
      this.subscribeToSaveResponse(this.delPlaceMasterService.update(this.delPlaceMaster));
    } else {
      this.subscribeToSaveResponse(this.delPlaceMasterService.create(this.delPlaceMaster));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IDelPlaceMaster>>) {
    result.subscribe((res: HttpResponse<IDelPlaceMaster>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
}
