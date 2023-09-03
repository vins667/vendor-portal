import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';
import { IDeliveryTermMaster } from 'app/shared/model/delivery-term-master.model';
import { DeliveryTermMasterService } from './delivery-term-master.service';

@Component({
  selector: 'jhi-delivery-term-master-update',
  templateUrl: './delivery-term-master-update.component.html'
})
export class DeliveryTermMasterUpdateComponent implements OnInit {
  deliveryTermMaster: IDeliveryTermMaster;
  isSaving: boolean;

  createdDate: string;
  lastUpdatedDate: string;

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected deliveryTermMasterService: DeliveryTermMasterService,

    protected activatedRoute: ActivatedRoute
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ deliveryTermMaster }) => {
      this.deliveryTermMaster = deliveryTermMaster;
      this.createdDate = this.deliveryTermMaster.createdDate != null ? this.deliveryTermMaster.createdDate.format(DATE_TIME_FORMAT) : null;
      this.lastUpdatedDate =
        this.deliveryTermMaster.lastUpdatedDate != null ? this.deliveryTermMaster.lastUpdatedDate.format(DATE_TIME_FORMAT) : null;
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    this.deliveryTermMaster.createdDate = this.createdDate != null ? moment(this.createdDate, DATE_TIME_FORMAT) : null;
    this.deliveryTermMaster.lastUpdatedDate = this.lastUpdatedDate != null ? moment(this.lastUpdatedDate, DATE_TIME_FORMAT) : null;
    if (this.deliveryTermMaster.id !== undefined) {
      this.subscribeToSaveResponse(this.deliveryTermMasterService.update(this.deliveryTermMaster));
    } else {
      this.subscribeToSaveResponse(this.deliveryTermMasterService.create(this.deliveryTermMaster));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IDeliveryTermMaster>>) {
    result.subscribe((res: HttpResponse<IDeliveryTermMaster>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
