import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { ITdsGroupMaster } from 'app/shared/model/tds-group-master.model';
import { TdsGroupMasterService } from './tds-group-master.service';
import { ITdsYearMaster } from 'app/shared/model/tds-year-master.model';
import { TdsYearMasterService } from 'app/entities/tds-year-master';
import { JhiAlertService } from 'ng-jhipster';

@Component({
  selector: 'jhi-tds-group-master-update',
  templateUrl: './tds-group-master-update.component.html'
})
export class TdsGroupMasterUpdateComponent implements OnInit {
  tdsGroupMaster: ITdsGroupMaster;
  isSaving: boolean;
  createdDate: string;
  lastUpdatedDate: string;
  tdsyearmasters: ITdsYearMaster[];

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected tdsGroupMasterService: TdsGroupMasterService,
    protected activatedRoute: ActivatedRoute,
    protected tdsYearMasterService: TdsYearMasterService
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ tdsGroupMaster }) => {
      this.tdsGroupMaster = tdsGroupMaster;
      this.createdDate = this.tdsGroupMaster.createdDate != null ? this.tdsGroupMaster.createdDate.format(DATE_TIME_FORMAT) : null;
      this.lastUpdatedDate =
        this.tdsGroupMaster.lastUpdatedDate != null ? this.tdsGroupMaster.lastUpdatedDate.format(DATE_TIME_FORMAT) : null;
    });
    this.tdsYearMasterService.query().subscribe(
      (res: HttpResponse<ITdsYearMaster[]>) => {
        this.tdsyearmasters = res.body;
      },
      (res: HttpErrorResponse) => this.onError(res.message)
    );
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    this.tdsGroupMaster.createdDate = this.createdDate != null ? moment(this.createdDate, DATE_TIME_FORMAT) : null;
    this.tdsGroupMaster.lastUpdatedDate = this.lastUpdatedDate != null ? moment(this.lastUpdatedDate, DATE_TIME_FORMAT) : null;
    if (this.tdsGroupMaster.id !== undefined) {
      this.subscribeToSaveResponse(this.tdsGroupMasterService.update(this.tdsGroupMaster));
    } else {
      this.subscribeToSaveResponse(this.tdsGroupMasterService.create(this.tdsGroupMaster));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITdsGroupMaster>>) {
    result.subscribe((res: HttpResponse<ITdsGroupMaster>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }

  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }
}
