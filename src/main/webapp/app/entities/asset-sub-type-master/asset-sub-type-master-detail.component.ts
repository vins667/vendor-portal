import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { IAssetSubTypeMaster } from 'app/shared/model/asset-sub-type-master.model';

@Component({
  selector: 'jhi-asset-sub-type-master-detail',
  templateUrl: './asset-sub-type-master-detail.component.html'
})
export class AssetSubTypeMasterDetailComponent implements OnInit {
  assetSubTypeMaster: IAssetSubTypeMaster;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ assetSubTypeMaster }) => {
      this.assetSubTypeMaster = assetSubTypeMaster;
    });
  }

  previousState() {
    window.history.back();
  }
}
