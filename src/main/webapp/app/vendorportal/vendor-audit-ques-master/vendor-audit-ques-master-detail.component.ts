import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { IVendorAuditQuesMaster } from 'app/shared/model/vendor-audit-ques-master.model';

@Component({
    selector: 'jhi-vendor-audit-ques-master-detail',
    templateUrl: './vendor-audit-ques-master-detail.component.html'
})
export class VendorAuditQuesMasterDetailComponent implements OnInit {
    vendorAuditQuesMaster: IVendorAuditQuesMaster;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ vendorAuditQuesMaster }) => {
            this.vendorAuditQuesMaster = vendorAuditQuesMaster;
        });
    }

    previousState() {
        window.history.back();
    }
}
