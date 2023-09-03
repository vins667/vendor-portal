import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { IAuditQuesBuyerMapping } from 'app/shared/model/audit-ques-buyer-mapping.model';

@Component({
    selector: 'jhi-audit-ques-buyer-mapping-detail',
    templateUrl: './audit-ques-buyer-mapping-detail.component.html'
})
export class AuditQuesBuyerMappingDetailComponent implements OnInit {
    auditQuesBuyerMapping: IAuditQuesBuyerMapping;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ auditQuesBuyerMapping }) => {
            this.auditQuesBuyerMapping = auditQuesBuyerMapping;
        });
    }

    previousState() {
        window.history.back();
    }
}
