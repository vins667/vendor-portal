import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { IAssetSupplierMaster } from 'app/shared/model/asset-supplier-master.model';

@Component({
  selector: 'jhi-asset-supplier-master-detail',
  templateUrl: './asset-supplier-master-detail.component.html'
})
export class AssetSupplierMasterDetailComponent implements OnInit {
  assetSupplierMaster: IAssetSupplierMaster;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ assetSupplierMaster }) => {
      this.assetSupplierMaster = assetSupplierMaster;
    });
  }

  previousState() {
    window.history.back();
  }
}
