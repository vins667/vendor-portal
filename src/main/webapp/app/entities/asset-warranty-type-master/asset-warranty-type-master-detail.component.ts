import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { IAssetWarrantyTypeMaster } from 'app/shared/model/asset-warranty-type-master.model';

@Component({
  selector: 'jhi-asset-warranty-type-master-detail',
  templateUrl: './asset-warranty-type-master-detail.component.html'
})
export class AssetWarrantyTypeMasterDetailComponent implements OnInit {
  assetWarrantyTypeMaster: IAssetWarrantyTypeMaster;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ assetWarrantyTypeMaster }) => {
      this.assetWarrantyTypeMaster = assetWarrantyTypeMaster;
    });
  }

  previousState() {
    window.history.back();
  }
}
