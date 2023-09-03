import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { ITransactionNature } from 'app/shared/model/transaction-nature.model';
import { TransactionNatureService } from './transaction-nature.service';

@Component({
  selector: 'jhi-transaction-nature-update',
  templateUrl: './transaction-nature-update.component.html'
})
export class TransactionNatureUpdateComponent implements OnInit {
  transactionNature: ITransactionNature;
  isSaving: boolean;
  createdDate: string;
  lastUpdatedDate: string;

  constructor(protected transactionNatureService: TransactionNatureService, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ transactionNature }) => {
      this.transactionNature = transactionNature;
      this.createdDate = this.transactionNature.createdDate != null ? this.transactionNature.createdDate.format(DATE_TIME_FORMAT) : null;
      this.lastUpdatedDate =
        this.transactionNature.lastUpdatedDate != null ? this.transactionNature.lastUpdatedDate.format(DATE_TIME_FORMAT) : null;
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    this.transactionNature.createdDate = this.createdDate != null ? moment(this.createdDate, DATE_TIME_FORMAT) : null;
    this.transactionNature.lastUpdatedDate = this.lastUpdatedDate != null ? moment(this.lastUpdatedDate, DATE_TIME_FORMAT) : null;
    if (this.transactionNature.id !== undefined) {
      this.subscribeToSaveResponse(this.transactionNatureService.update(this.transactionNature));
    } else {
      this.subscribeToSaveResponse(this.transactionNatureService.create(this.transactionNature));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITransactionNature>>) {
    result.subscribe((res: HttpResponse<ITransactionNature>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
}
