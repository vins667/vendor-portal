import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IGstReconciliation } from 'app/shared/model/gst-reconciliation.model';

@Component({
  selector: 'jhi-gst-reconciliation-detail',
  templateUrl: './gst-reconciliation-detail.component.html'
})
export class GstReconciliationDetailComponent implements OnInit {
  gstReconciliation: IGstReconciliation;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ gstReconciliation }) => {
      this.gstReconciliation = gstReconciliation;
    });
  }

  previousState() {
    window.history.back();
  }
}
