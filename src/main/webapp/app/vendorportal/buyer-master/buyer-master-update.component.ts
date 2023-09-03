import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { IBuyerMaster } from 'app/shared/model/buyer-master.model';
import { BuyerMasterService } from './buyer-master.service';

@Component({
  selector: 'jhi-buyer-master-update',
  templateUrl: './buyer-master-update.component.html'
})
export class BuyerMasterUpdateComponent implements OnInit {
  buyerMaster: IBuyerMaster;
  isSaving: boolean;
  createdDate: string;
  lastUpdatedDate: string;

  constructor(protected buyerMasterService: BuyerMasterService, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ buyerMaster }) => {
      this.buyerMaster = buyerMaster;
      this.createdDate = this.buyerMaster.createdDate != null ? this.buyerMaster.createdDate.format(DATE_TIME_FORMAT) : null;
      this.lastUpdatedDate = this.buyerMaster.lastUpdatedDate != null ? this.buyerMaster.lastUpdatedDate.format(DATE_TIME_FORMAT) : null;
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    this.buyerMaster.createdDate = this.createdDate != null ? moment(this.createdDate, DATE_TIME_FORMAT) : null;
    this.buyerMaster.lastUpdatedDate = this.lastUpdatedDate != null ? moment(this.lastUpdatedDate, DATE_TIME_FORMAT) : null;
    if (this.buyerMaster.id !== undefined) {
      this.subscribeToSaveResponse(this.buyerMasterService.update(this.buyerMaster));
    } else {
      this.subscribeToSaveResponse(this.buyerMasterService.create(this.buyerMaster));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IBuyerMaster>>) {
    result.subscribe((res: HttpResponse<IBuyerMaster>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
}
