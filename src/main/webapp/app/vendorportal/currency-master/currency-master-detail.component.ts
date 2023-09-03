import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ICurrencyMaster } from 'app/shared/model/currency-master.model';

@Component({
  selector: 'jhi-currency-master-detail',
  templateUrl: './currency-master-detail.component.html'
})
export class CurrencyMasterDetailComponent implements OnInit {
  currencyMaster: ICurrencyMaster;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ currencyMaster }) => {
      this.currencyMaster = currencyMaster;
    });
  }

  previousState() {
    window.history.back();
  }
}
