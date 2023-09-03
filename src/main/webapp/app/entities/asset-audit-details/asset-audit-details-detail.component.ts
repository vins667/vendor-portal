import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { IAssetAuditDetails } from 'app/shared/model/asset-audit-details.model';

@Component({
  selector: 'jhi-asset-audit-details-detail',
  templateUrl: './asset-audit-details-detail.component.html'
})
export class AssetAuditDetailsDetailComponent implements OnInit {
  assetAuditDetails: IAssetAuditDetails;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ assetAuditDetails }) => {
      this.assetAuditDetails = assetAuditDetails;
    });
  }

  previousState() {
    window.history.back();
  }
}
