import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICostingGroupMaster } from 'app/shared/model/costing-group-master.model';

@Component({
  selector: 'jhi-costing-group-master-detail',
  templateUrl: './costing-group-master-detail.component.html'
})
export class CostingGroupMasterDetailComponent implements OnInit {
  costingGroupMaster: ICostingGroupMaster;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ costingGroupMaster }) => {
      this.costingGroupMaster = costingGroupMaster;
    });
  }

  previousState() {
    window.history.back();
  }
}
