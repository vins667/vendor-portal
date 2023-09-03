import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { IAssetDocumentMaster } from 'app/shared/model/asset-document-master.model';
import { AssetDocumentMasterService } from './asset-document-master.service';

@Component({
  selector: 'jhi-asset-document-master-update',
  templateUrl: './asset-document-master-update.component.html'
})
export class AssetDocumentMasterUpdateComponent implements OnInit {
  assetDocumentMaster: IAssetDocumentMaster;
  isSaving: boolean;
  createdDate: string;
  lastUpdatedDate: string;

  constructor(protected assetDocumentMasterService: AssetDocumentMasterService, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ assetDocumentMaster }) => {
      this.assetDocumentMaster = assetDocumentMaster;
      this.createdDate =
        this.assetDocumentMaster.createdDate != null ? this.assetDocumentMaster.createdDate.format(DATE_TIME_FORMAT) : null;
      this.lastUpdatedDate =
        this.assetDocumentMaster.lastUpdatedDate != null ? this.assetDocumentMaster.lastUpdatedDate.format(DATE_TIME_FORMAT) : null;
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    this.assetDocumentMaster.createdDate = this.createdDate != null ? moment(this.createdDate, DATE_TIME_FORMAT) : null;
    this.assetDocumentMaster.lastUpdatedDate = this.lastUpdatedDate != null ? moment(this.lastUpdatedDate, DATE_TIME_FORMAT) : null;
    if (this.assetDocumentMaster.id !== undefined) {
      this.subscribeToSaveResponse(this.assetDocumentMasterService.update(this.assetDocumentMaster));
    } else {
      this.subscribeToSaveResponse(this.assetDocumentMasterService.create(this.assetDocumentMaster));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IAssetDocumentMaster>>) {
    result.subscribe((res: HttpResponse<IAssetDocumentMaster>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
}
