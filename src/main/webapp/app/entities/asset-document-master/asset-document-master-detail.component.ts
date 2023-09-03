import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { IAssetDocumentMaster } from 'app/shared/model/asset-document-master.model';

@Component({
  selector: 'jhi-asset-document-master-detail',
  templateUrl: './asset-document-master-detail.component.html'
})
export class AssetDocumentMasterDetailComponent implements OnInit {
  assetDocumentMaster: IAssetDocumentMaster;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ assetDocumentMaster }) => {
      this.assetDocumentMaster = assetDocumentMaster;
    });
  }

  previousState() {
    window.history.back();
  }
}
