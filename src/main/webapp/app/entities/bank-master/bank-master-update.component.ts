import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { IBankMaster } from 'app/shared/model/bank-master.model';
import { BankMasterService } from './bank-master.service';

@Component({
  selector: 'jhi-bank-master-update',
  templateUrl: './bank-master-update.component.html'
})
export class BankMasterUpdateComponent implements OnInit {
  bankMaster: IBankMaster;
  isSaving: boolean;
  createdDate: string;
  lastUpdatedDate: string;

  constructor(protected bankMasterService: BankMasterService, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ bankMaster }) => {
      this.bankMaster = bankMaster;
      this.createdDate = this.bankMaster.createdDate != null ? this.bankMaster.createdDate.format(DATE_TIME_FORMAT) : null;
      this.lastUpdatedDate = this.bankMaster.lastUpdatedDate != null ? this.bankMaster.lastUpdatedDate.format(DATE_TIME_FORMAT) : null;
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    this.bankMaster.createdDate = this.createdDate != null ? moment(this.createdDate, DATE_TIME_FORMAT) : null;
    this.bankMaster.lastUpdatedDate = this.lastUpdatedDate != null ? moment(this.lastUpdatedDate, DATE_TIME_FORMAT) : null;
    if (this.bankMaster.id !== undefined) {
      this.subscribeToSaveResponse(this.bankMasterService.update(this.bankMaster));
    } else {
      this.subscribeToSaveResponse(this.bankMasterService.create(this.bankMaster));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IBankMaster>>) {
    result.subscribe((res: HttpResponse<IBankMaster>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
}
