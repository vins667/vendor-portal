import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IVcutDeviceLineMaster } from 'app/shared/model/vcut-device-line-master.model';

@Component({
  selector: 'jhi-vcut-device-line-master-detail',
  templateUrl: './vcut-device-line-master-detail.component.html'
})
export class VcutDeviceLineMasterDetailComponent implements OnInit {
  vcutDeviceLineMaster: IVcutDeviceLineMaster;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ vcutDeviceLineMaster }) => {
      this.vcutDeviceLineMaster = vcutDeviceLineMaster;
    });
  }

  previousState() {
    window.history.back();
  }
}
