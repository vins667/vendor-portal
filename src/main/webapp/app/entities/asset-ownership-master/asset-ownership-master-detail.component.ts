import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { IAssetOwnershipMaster } from 'app/shared/model/asset-ownership-master.model';

@Component({
  selector: 'jhi-asset-ownership-master-detail',
  templateUrl: './asset-ownership-master-detail.component.html'
})
export class AssetOwnershipMasterDetailComponent implements OnInit {
  assetOwnershipMaster: IAssetOwnershipMaster;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ assetOwnershipMaster }) => {
      this.assetOwnershipMaster = assetOwnershipMaster;
    });
  }

  previousState() {
    window.history.back();
  }
}
