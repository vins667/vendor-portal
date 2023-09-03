import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { IOccupationMaster } from 'app/shared/model/occupation-master.model';
import { OccupationMasterService } from './occupation-master.service';

@Component({
  selector: 'jhi-occupation-master-update',
  templateUrl: './occupation-master-update.component.html'
})
export class OccupationMasterUpdateComponent implements OnInit {
  occupationMaster: IOccupationMaster;
  isSaving: boolean;
  createdDate: string;
  lastUpdatedDate: string;

  constructor(protected occupationMasterService: OccupationMasterService, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ occupationMaster }) => {
      this.occupationMaster = occupationMaster;
      this.createdDate = this.occupationMaster.createdDate != null ? this.occupationMaster.createdDate.format(DATE_TIME_FORMAT) : null;
      this.lastUpdatedDate =
        this.occupationMaster.lastUpdatedDate != null ? this.occupationMaster.lastUpdatedDate.format(DATE_TIME_FORMAT) : null;
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    this.occupationMaster.createdDate = this.createdDate != null ? moment(this.createdDate, DATE_TIME_FORMAT) : null;
    this.occupationMaster.lastUpdatedDate = this.lastUpdatedDate != null ? moment(this.lastUpdatedDate, DATE_TIME_FORMAT) : null;
    if (this.occupationMaster.id !== undefined) {
      this.subscribeToSaveResponse(this.occupationMasterService.update(this.occupationMaster));
    } else {
      this.subscribeToSaveResponse(this.occupationMasterService.create(this.occupationMaster));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IOccupationMaster>>) {
    result.subscribe((res: HttpResponse<IOccupationMaster>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
}
