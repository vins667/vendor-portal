import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { IAssetTypeMaster } from 'app/shared/model/asset-type-master.model';

@Component({
  selector: 'jhi-asset-type-master-detail',
  templateUrl: './asset-type-master-detail.component.html'
})
export class AssetTypeMasterDetailComponent implements OnInit {
  assetTypeMaster: IAssetTypeMaster;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ assetTypeMaster }) => {
      this.assetTypeMaster = assetTypeMaster;
    });
  }

  previousState() {
    window.history.back();
  }
}
