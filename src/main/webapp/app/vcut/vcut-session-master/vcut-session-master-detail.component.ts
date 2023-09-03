import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IVcutSessionMaster } from 'app/shared/model/vcut-session-master.model';

@Component({
  selector: 'jhi-vcut-session-master-detail',
  templateUrl: './vcut-session-master-detail.component.html'
})
export class VcutSessionMasterDetailComponent implements OnInit {
  vcutSessionMaster: IVcutSessionMaster;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ vcutSessionMaster }) => {
      this.vcutSessionMaster = vcutSessionMaster;
    });
  }

  previousState() {
    window.history.back();
  }
}
