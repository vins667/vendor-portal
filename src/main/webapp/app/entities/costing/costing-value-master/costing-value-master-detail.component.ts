import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICostingValueMaster } from 'app/shared/model/costing-value-master.model';

@Component({
  selector: 'jhi-costing-value-master-detail',
  templateUrl: './costing-value-master-detail.component.html'
})
export class CostingValueMasterDetailComponent implements OnInit {
  costingValueMaster: ICostingValueMaster;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ costingValueMaster }) => {
      this.costingValueMaster = costingValueMaster;
    });
  }

  previousState() {
    window.history.back();
  }
}
