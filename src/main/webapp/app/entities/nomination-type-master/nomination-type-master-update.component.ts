import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { INominationTypeMaster } from 'app/shared/model/nomination-type-master.model';
import { NominationTypeMasterService } from './nomination-type-master.service';

@Component({
  selector: 'jhi-nomination-type-master-update',
  templateUrl: './nomination-type-master-update.component.html'
})
export class NominationTypeMasterUpdateComponent implements OnInit {
  nominationTypeMaster: INominationTypeMaster;
  isSaving: boolean;
  createdDate: string;
  lastUpdatedDate: string;

  constructor(protected nominationTypeMasterService: NominationTypeMasterService, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ nominationTypeMaster }) => {
      this.nominationTypeMaster = nominationTypeMaster;
      this.createdDate =
        this.nominationTypeMaster.createdDate != null ? this.nominationTypeMaster.createdDate.format(DATE_TIME_FORMAT) : null;
      this.lastUpdatedDate =
        this.nominationTypeMaster.lastUpdatedDate != null ? this.nominationTypeMaster.lastUpdatedDate.format(DATE_TIME_FORMAT) : null;
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    this.nominationTypeMaster.createdDate = this.createdDate != null ? moment(this.createdDate, DATE_TIME_FORMAT) : null;
    this.nominationTypeMaster.lastUpdatedDate = this.lastUpdatedDate != null ? moment(this.lastUpdatedDate, DATE_TIME_FORMAT) : null;
    if (this.nominationTypeMaster.id !== undefined) {
      this.subscribeToSaveResponse(this.nominationTypeMasterService.update(this.nominationTypeMaster));
    } else {
      this.subscribeToSaveResponse(this.nominationTypeMasterService.create(this.nominationTypeMaster));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<INominationTypeMaster>>) {
    result.subscribe((res: HttpResponse<INominationTypeMaster>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
}
