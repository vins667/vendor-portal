import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { IVendTypeMaster } from 'app/shared/model/vend-type-master.model';

@Component({
  selector: 'jhi-vend-type-master-detail',
  templateUrl: './vend-type-master-detail.component.html'
})
export class VendTypeMasterDetailComponent implements OnInit {
  vendTypeMaster: IVendTypeMaster;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ vendTypeMaster }) => {
      this.vendTypeMaster = vendTypeMaster;
    });
  }

  previousState() {
    window.history.back();
  }
}
