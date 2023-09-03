import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { IAssetMaster } from 'app/shared/model/asset-master.model';

@Component({
  selector: 'jhi-asset-master-detail',
  templateUrl: './asset-master-detail.component.html'
})
export class AssetMasterDetailComponent implements OnInit {
  assetMaster: IAssetMaster;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ assetMaster }) => {
      this.assetMaster = assetMaster;
    });
  }

  previousState() {
    window.history.back();
  }
}
