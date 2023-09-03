import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICostingProcessMaster } from 'app/shared/model/costing-process-master.model';

@Component({
  selector: 'jhi-costing-process-master-detail',
  templateUrl: './costing-process-master-detail.component.html'
})
export class CostingProcessMasterDetailComponent implements OnInit {
  costingProcessMaster: ICostingProcessMaster;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ costingProcessMaster }) => {
      this.costingProcessMaster = costingProcessMaster;
    });
  }

  previousState() {
    window.history.back();
  }
}
