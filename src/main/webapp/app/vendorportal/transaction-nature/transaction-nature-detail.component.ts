import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ITransactionNature } from 'app/shared/model/transaction-nature.model';

@Component({
  selector: 'jhi-transaction-nature-detail',
  templateUrl: './transaction-nature-detail.component.html'
})
export class TransactionNatureDetailComponent implements OnInit {
  transactionNature: ITransactionNature;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ transactionNature }) => {
      this.transactionNature = transactionNature;
    });
  }

  previousState() {
    window.history.back();
  }
}
