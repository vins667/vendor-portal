import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { IAssetLocationMaster } from 'app/shared/model/asset-location-master.model';

@Component({
  selector: 'jhi-asset-location-master-detail',
  templateUrl: './asset-location-master-detail.component.html'
})
export class AssetLocationMasterDetailComponent implements OnInit {
  assetLocationMaster: IAssetLocationMaster;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ assetLocationMaster }) => {
      this.assetLocationMaster = assetLocationMaster;
    });
  }

  previousState() {
    window.history.back();
  }
}
