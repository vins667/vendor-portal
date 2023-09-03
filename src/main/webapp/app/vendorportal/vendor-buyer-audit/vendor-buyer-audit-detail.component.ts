import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IVendorBuyerAudit } from 'app/shared/model/vendor-buyer-audit.model';

@Component({
  selector: 'jhi-vendor-buyer-audit-detail',
  templateUrl: './vendor-buyer-audit-detail.component.html'
})
export class VendorBuyerAuditDetailComponent implements OnInit {
  vendorBuyerAudit: IVendorBuyerAudit;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ vendorBuyerAudit }) => {
      this.vendorBuyerAudit = vendorBuyerAudit;
    });
  }

  previousState() {
    window.history.back();
  }
}
