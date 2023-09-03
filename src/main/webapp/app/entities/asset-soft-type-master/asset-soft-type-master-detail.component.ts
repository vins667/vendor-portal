import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { IAssetSoftTypeMaster } from 'app/shared/model/asset-soft-type-master.model';

@Component({
  selector: 'jhi-asset-soft-type-master-detail',
  templateUrl: './asset-soft-type-master-detail.component.html'
})
export class AssetSoftTypeMasterDetailComponent implements OnInit {
  assetSoftTypeMaster: IAssetSoftTypeMaster;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ assetSoftTypeMaster }) => {
      this.assetSoftTypeMaster = assetSoftTypeMaster;
    });
  }

  previousState() {
    window.history.back();
  }
}
