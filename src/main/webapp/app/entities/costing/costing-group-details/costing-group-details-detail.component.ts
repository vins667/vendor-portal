import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICostingGroupDetails } from 'app/shared/model/costing-group-details.model';

@Component({
  selector: 'jhi-costing-group-details-detail',
  templateUrl: './costing-group-details-detail.component.html'
})
export class CostingGroupDetailsDetailComponent implements OnInit {
  costingGroupDetails: ICostingGroupDetails;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ costingGroupDetails }) => {
      this.costingGroupDetails = costingGroupDetails;
    });
  }

  previousState() {
    window.history.back();
  }
}
