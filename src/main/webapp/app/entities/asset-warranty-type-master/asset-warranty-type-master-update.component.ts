import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { IAssetWarrantyTypeMaster } from 'app/shared/model/asset-warranty-type-master.model';
import { AssetWarrantyTypeMasterService } from './asset-warranty-type-master.service';

@Component({
  selector: 'jhi-asset-warranty-type-master-update',
  templateUrl: './asset-warranty-type-master-update.component.html'
})
export class AssetWarrantyTypeMasterUpdateComponent implements OnInit {
  assetWarrantyTypeMaster: IAssetWarrantyTypeMaster;
  isSaving: boolean;
  createdDate: string;
  lastUpdatedDate: string;

  constructor(protected assetWarrantyTypeMasterService: AssetWarrantyTypeMasterService, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ assetWarrantyTypeMaster }) => {
      this.assetWarrantyTypeMaster = assetWarrantyTypeMaster;
      this.createdDate =
        this.assetWarrantyTypeMaster.createdDate != null ? this.assetWarrantyTypeMaster.createdDate.format(DATE_TIME_FORMAT) : null;
      this.lastUpdatedDate =
        this.assetWarrantyTypeMaster.lastUpdatedDate != null ? this.assetWarrantyTypeMaster.lastUpdatedDate.format(DATE_TIME_FORMAT) : null;
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    this.assetWarrantyTypeMaster.createdDate = this.createdDate != null ? moment(this.createdDate, DATE_TIME_FORMAT) : null;
    this.assetWarrantyTypeMaster.lastUpdatedDate = this.lastUpdatedDate != null ? moment(this.lastUpdatedDate, DATE_TIME_FORMAT) : null;
    if (this.assetWarrantyTypeMaster.id !== undefined) {
      this.subscribeToSaveResponse(this.assetWarrantyTypeMasterService.update(this.assetWarrantyTypeMaster));
    } else {
      this.subscribeToSaveResponse(this.assetWarrantyTypeMasterService.create(this.assetWarrantyTypeMaster));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IAssetWarrantyTypeMaster>>) {
    result.subscribe((res: HttpResponse<IAssetWarrantyTypeMaster>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
}
