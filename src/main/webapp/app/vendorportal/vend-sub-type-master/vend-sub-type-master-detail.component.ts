import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { IVendSubTypeMaster } from 'app/shared/model/vend-sub-type-master.model';

@Component({
  selector: 'jhi-vend-sub-type-master-detail',
  templateUrl: './vend-sub-type-master-detail.component.html'
})
export class VendSubTypeMasterDetailComponent implements OnInit {
  vendSubTypeMaster: IVendSubTypeMaster;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ vendSubTypeMaster }) => {
      this.vendSubTypeMaster = vendSubTypeMaster;
    });
  }

  previousState() {
    window.history.back();
  }
}
