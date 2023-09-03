import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IPoDatatexApproval } from 'app/shared/model/po-datatex-approval.model';

@Component({
  selector: 'jhi-po-datatex-approval-detail',
  templateUrl: './po-datatex-approval-detail.component.html'
})
export class PoDatatexApprovalDetailComponent implements OnInit {
  poDatatexApproval: IPoDatatexApproval;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ poDatatexApproval }) => {
      this.poDatatexApproval = poDatatexApproval;
    });
  }

  previousState() {
    window.history.back();
  }
}
