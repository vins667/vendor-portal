import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { IVendorBuyerAuditLinking } from 'app/shared/model/vendor-buyer-audit-linking.model';

@Component({
    selector: 'jhi-vendor-buyer-audit-linking-detail',
    templateUrl: './vendor-buyer-audit-linking-detail.component.html'
})
export class VendorBuyerAuditLinkingDetailComponent implements OnInit {
    vendorBuyerAuditLinking: IVendorBuyerAuditLinking;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ vendorBuyerAuditLinking }) => {
            this.vendorBuyerAuditLinking = vendorBuyerAuditLinking;
        });
    }

    previousState() {
        window.history.back();
    }
}
