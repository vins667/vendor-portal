import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IVcutPlanChangeMaster } from 'app/shared/model/vcut-plan-change-master.model';

@Component({
  selector: 'jhi-vcut-plan-change-master-detail',
  templateUrl: './vcut-plan-change-master-detail.component.html'
})
export class VcutPlanChangeMasterDetailComponent implements OnInit {
  vcutPlanChangeMaster: IVcutPlanChangeMaster;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ vcutPlanChangeMaster }) => {
      this.vcutPlanChangeMaster = vcutPlanChangeMaster;
    });
  }

  previousState() {
    window.history.back();
  }
}
