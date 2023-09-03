import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IAuditGroupMaster } from 'app/shared/model/audit-group-master.model';

@Component({
  selector: 'jhi-audit-group-master-detail',
  templateUrl: './audit-group-master-detail.component.html'
})
export class AuditGroupMasterDetailComponent implements OnInit {
  auditGroupMaster: IAuditGroupMaster;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ auditGroupMaster }) => {
      this.auditGroupMaster = auditGroupMaster;
    });
  }

  previousState() {
    window.history.back();
  }
}
