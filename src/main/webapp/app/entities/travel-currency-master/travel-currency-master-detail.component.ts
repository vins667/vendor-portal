import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITravelCurrencyMaster } from 'app/shared/model/travel-currency-master.model';

@Component({
  selector: 'jhi-travel-currency-master-detail',
  templateUrl: './travel-currency-master-detail.component.html'
})
export class TravelCurrencyMasterDetailComponent implements OnInit {
  travelCurrencyMaster: ITravelCurrencyMaster;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ travelCurrencyMaster }) => {
      this.travelCurrencyMaster = travelCurrencyMaster;
    });
  }

  previousState() {
    window.history.back();
  }
}
