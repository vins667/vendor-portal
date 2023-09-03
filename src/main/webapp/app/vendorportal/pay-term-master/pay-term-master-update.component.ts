import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';
import { IPayTermMaster } from 'app/shared/model/pay-term-master.model';
import { PayTermMasterService } from './pay-term-master.service';

@Component({
  selector: 'jhi-pay-term-master-update',
  templateUrl: './pay-term-master-update.component.html'
})
export class PayTermMasterUpdateComponent implements OnInit {
  payTermMaster: IPayTermMaster;
  isSaving: boolean;

  createdDate: string;
  lastUpdatedDate: string;

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected payTermMasterService: PayTermMasterService,
    protected activatedRoute: ActivatedRoute
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ payTermMaster }) => {
      this.payTermMaster = payTermMaster;
      this.createdDate = this.payTermMaster.createdDate != null ? this.payTermMaster.createdDate.format(DATE_TIME_FORMAT) : null;
      this.lastUpdatedDate =
        this.payTermMaster.lastUpdatedDate != null ? this.payTermMaster.lastUpdatedDate.format(DATE_TIME_FORMAT) : null;
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    this.payTermMaster.createdDate = this.createdDate != null ? moment(this.createdDate, DATE_TIME_FORMAT) : null;
    this.payTermMaster.lastUpdatedDate = this.lastUpdatedDate != null ? moment(this.lastUpdatedDate, DATE_TIME_FORMAT) : null;
    if (this.payTermMaster.id !== undefined) {
      this.subscribeToSaveResponse(this.payTermMasterService.update(this.payTermMaster));
    } else {
      this.subscribeToSaveResponse(this.payTermMasterService.create(this.payTermMaster));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IPayTermMaster>>) {
    result.subscribe((res: HttpResponse<IPayTermMaster>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
