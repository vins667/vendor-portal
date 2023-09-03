import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { IAssetCompanyMaster } from 'app/shared/model/asset-company-master.model';

@Component({
  selector: 'jhi-asset-company-master-detail',
  templateUrl: './asset-company-master-detail.component.html'
})
export class AssetCompanyMasterDetailComponent implements OnInit {
  assetCompanyMaster: IAssetCompanyMaster;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ assetCompanyMaster }) => {
      this.assetCompanyMaster = assetCompanyMaster;
    });
  }

  previousState() {
    window.history.back();
  }
}
