import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { IAssetSubTypeDetailMaster } from 'app/shared/model/asset-sub-type-detail-master.model';

@Component({
  selector: 'jhi-asset-sub-type-detail-master-detail',
  templateUrl: './asset-sub-type-detail-master-detail.component.html'
})
export class AssetSubTypeDetailMasterDetailComponent implements OnInit {
  assetSubTypeDetailMaster: IAssetSubTypeDetailMaster;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ assetSubTypeDetailMaster }) => {
      this.assetSubTypeDetailMaster = assetSubTypeDetailMaster;
    });
  }

  previousState() {
    window.history.back();
  }
}
