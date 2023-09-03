import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IBuyerCosting } from 'app/shared/model/buyer-costing.model';

@Component({
  selector: 'jhi-buyer-costing-detail',
  templateUrl: './buyer-costing-detail.component.html'
})
export class BuyerCostingDetailComponent implements OnInit {
  buyerCosting: IBuyerCosting;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ buyerCosting }) => {
      this.buyerCosting = buyerCosting;
    });
  }

  previousState() {
    window.history.back();
  }
}
