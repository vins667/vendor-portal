import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IVcutOperationRejectMaster } from 'app/shared/model/vcut-operation-reject-master.model';

@Component({
  selector: 'jhi-vcut-operation-reject-master-detail',
  templateUrl: './vcut-operation-reject-master-detail.component.html'
})
export class VcutOperationRejectMasterDetailComponent implements OnInit {
  vcutOperationRejectMaster: IVcutOperationRejectMaster;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ vcutOperationRejectMaster }) => {
      this.vcutOperationRejectMaster = vcutOperationRejectMaster;
    });
  }

  previousState() {
    window.history.back();
  }
}
