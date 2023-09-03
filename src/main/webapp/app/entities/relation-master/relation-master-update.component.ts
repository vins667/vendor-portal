import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { IRelationMaster } from 'app/shared/model/relation-master.model';
import { RelationMasterService } from './relation-master.service';

@Component({
  selector: 'jhi-relation-master-update',
  templateUrl: './relation-master-update.component.html'
})
export class RelationMasterUpdateComponent implements OnInit {
  relationMaster: IRelationMaster;
  isSaving: boolean;
  createdDate: string;
  lastUpdatedDate: string;

  constructor(protected relationMasterService: RelationMasterService, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ relationMaster }) => {
      this.relationMaster = relationMaster;
      this.createdDate = this.relationMaster.createdDate != null ? this.relationMaster.createdDate.format(DATE_TIME_FORMAT) : null;
      this.lastUpdatedDate =
        this.relationMaster.lastUpdatedDate != null ? this.relationMaster.lastUpdatedDate.format(DATE_TIME_FORMAT) : null;
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    this.relationMaster.createdDate = this.createdDate != null ? moment(this.createdDate, DATE_TIME_FORMAT) : null;
    this.relationMaster.lastUpdatedDate = this.lastUpdatedDate != null ? moment(this.lastUpdatedDate, DATE_TIME_FORMAT) : null;
    if (this.relationMaster.id !== undefined) {
      this.subscribeToSaveResponse(this.relationMasterService.update(this.relationMaster));
    } else {
      this.subscribeToSaveResponse(this.relationMasterService.create(this.relationMaster));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IRelationMaster>>) {
    result.subscribe((res: HttpResponse<IRelationMaster>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
}
