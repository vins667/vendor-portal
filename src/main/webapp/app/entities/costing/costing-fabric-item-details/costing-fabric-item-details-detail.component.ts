import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICostingFabricItemDetails } from 'app/shared/model/costing-fabric-item-details.model';

@Component({
  selector: 'jhi-costing-fabric-item-details-detail',
  templateUrl: './costing-fabric-item-details-detail.component.html'
})
export class CostingFabricItemDetailsDetailComponent implements OnInit {
  costingFabricItemDetails: ICostingFabricItemDetails;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ costingFabricItemDetails }) => {
      this.costingFabricItemDetails = costingFabricItemDetails;
    });
  }

  previousState() {
    window.history.back();
  }
}
