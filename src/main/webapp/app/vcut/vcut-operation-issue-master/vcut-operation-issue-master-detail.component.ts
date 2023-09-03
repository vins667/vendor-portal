import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IVcutOperationIssueMaster } from 'app/shared/model/vcut-operation-issue-master.model';

@Component({
  selector: 'jhi-vcut-operation-issue-master-detail',
  templateUrl: './vcut-operation-issue-master-detail.component.html'
})
export class VcutOperationIssueMasterDetailComponent implements OnInit {
  vcutOperationIssueMaster: IVcutOperationIssueMaster;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ vcutOperationIssueMaster }) => {
      this.vcutOperationIssueMaster = vcutOperationIssueMaster;
    });
  }

  previousState() {
    window.history.back();
  }
}
