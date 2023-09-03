import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICostingEfficiencyMaste } from 'app/shared/model/costing-efficiency-maste.model';

@Component({
  selector: 'jhi-costing-efficiency-maste-detail',
  templateUrl: './costing-efficiency-maste-detail.component.html'
})
export class CostingEfficiencyMasteDetailComponent implements OnInit {
  costingEfficiencyMaste: ICostingEfficiencyMaste;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ costingEfficiencyMaste }) => {
      this.costingEfficiencyMaste = costingEfficiencyMaste;
    });
  }

  previousState() {
    window.history.back();
  }
}
