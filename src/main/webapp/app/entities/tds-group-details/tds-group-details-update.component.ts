import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';
import { ITdsGroupDetails } from 'app/shared/model/tds-group-details.model';
import { TdsGroupDetailsService } from './tds-group-details.service';
import { ITdsGroupMaster } from 'app/shared/model/tds-group-master.model';
import { TdsGroupMasterService } from 'app/entities/tds-group-master';

@Component({
  selector: 'jhi-tds-group-details-update',
  templateUrl: './tds-group-details-update.component.html'
})
export class TdsGroupDetailsUpdateComponent implements OnInit {
  tdsGroupDetails: ITdsGroupDetails;
  isSaving: boolean;

  tdsgroupmasters: ITdsGroupMaster[];
  lastUpdatedDate: string;
  createdDate: string;

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected tdsGroupDetailsService: TdsGroupDetailsService,
    protected tdsGroupMasterService: TdsGroupMasterService,
    protected activatedRoute: ActivatedRoute
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ tdsGroupDetails }) => {
      this.tdsGroupDetails = tdsGroupDetails;
      this.lastUpdatedDate =
        this.tdsGroupDetails.lastUpdatedDate != null ? this.tdsGroupDetails.lastUpdatedDate.format(DATE_TIME_FORMAT) : null;
      this.createdDate = this.tdsGroupDetails.createdDate != null ? this.tdsGroupDetails.createdDate.format(DATE_TIME_FORMAT) : null;
    });
    this.tdsGroupMasterService.query().subscribe(
      (res: HttpResponse<ITdsGroupMaster[]>) => {
        this.tdsgroupmasters = res.body;
      },
      (res: HttpErrorResponse) => this.onError(res.message)
    );
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    this.tdsGroupDetails.lastUpdatedDate = this.lastUpdatedDate != null ? moment(this.lastUpdatedDate, DATE_TIME_FORMAT) : null;
    this.tdsGroupDetails.createdDate = this.createdDate != null ? moment(this.createdDate, DATE_TIME_FORMAT) : null;
    if (this.tdsGroupDetails.id !== undefined) {
      this.subscribeToSaveResponse(this.tdsGroupDetailsService.update(this.tdsGroupDetails));
    } else {
      this.subscribeToSaveResponse(this.tdsGroupDetailsService.create(this.tdsGroupDetails));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITdsGroupDetails>>) {
    result.subscribe((res: HttpResponse<ITdsGroupDetails>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

  trackTdsGroupMasterById(index: number, item: ITdsGroupMaster) {
    return item.id;
  }
}
