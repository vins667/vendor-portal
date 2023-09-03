import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { ICurrencyMaster } from 'app/shared/model/currency-master.model';
import { CurrencyMasterService } from './currency-master.service';

@Component({
  selector: 'jhi-currency-master-update',
  templateUrl: './currency-master-update.component.html'
})
export class CurrencyMasterUpdateComponent implements OnInit {
  currencyMaster: ICurrencyMaster;
  isSaving: boolean;
  createdDate: string;
  lastUpdatedDate: string;

  constructor(protected currencyMasterService: CurrencyMasterService, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ currencyMaster }) => {
      this.currencyMaster = currencyMaster;
      this.createdDate = this.currencyMaster.createdDate != null ? this.currencyMaster.createdDate.format(DATE_TIME_FORMAT) : null;
      this.lastUpdatedDate =
        this.currencyMaster.lastUpdatedDate != null ? this.currencyMaster.lastUpdatedDate.format(DATE_TIME_FORMAT) : null;
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    this.currencyMaster.createdDate = this.createdDate != null ? moment(this.createdDate, DATE_TIME_FORMAT) : null;
    this.currencyMaster.lastUpdatedDate = this.lastUpdatedDate != null ? moment(this.lastUpdatedDate, DATE_TIME_FORMAT) : null;
    if (this.currencyMaster.id !== undefined) {
      this.subscribeToSaveResponse(this.currencyMasterService.update(this.currencyMaster));
    } else {
      this.subscribeToSaveResponse(this.currencyMasterService.create(this.currencyMaster));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICurrencyMaster>>) {
    result.subscribe((res: HttpResponse<ICurrencyMaster>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
}
