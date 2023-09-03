import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { IAssetSupplierMaster } from 'app/shared/model/asset-supplier-master.model';
import { AssetSupplierMasterService } from './asset-supplier-master.service';

@Component({
  selector: 'jhi-asset-supplier-master-update',
  templateUrl: './asset-supplier-master-update.component.html'
})
export class AssetSupplierMasterUpdateComponent implements OnInit {
  assetSupplierMaster: IAssetSupplierMaster;
  isSaving: boolean;
  lastUpdatedDate: string;
  createdDate: string;

  constructor(protected assetSupplierMasterService: AssetSupplierMasterService, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ assetSupplierMaster }) => {
      this.assetSupplierMaster = assetSupplierMaster;
      this.lastUpdatedDate =
        this.assetSupplierMaster.lastUpdatedDate != null ? this.assetSupplierMaster.lastUpdatedDate.format(DATE_TIME_FORMAT) : null;
      this.createdDate =
        this.assetSupplierMaster.createdDate != null ? this.assetSupplierMaster.createdDate.format(DATE_TIME_FORMAT) : null;
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    this.assetSupplierMaster.lastUpdatedDate = this.lastUpdatedDate != null ? moment(this.lastUpdatedDate, DATE_TIME_FORMAT) : null;
    this.assetSupplierMaster.createdDate = this.createdDate != null ? moment(this.createdDate, DATE_TIME_FORMAT) : null;
    if (this.assetSupplierMaster.id !== undefined) {
      this.subscribeToSaveResponse(this.assetSupplierMasterService.update(this.assetSupplierMaster));
    } else {
      this.subscribeToSaveResponse(this.assetSupplierMasterService.create(this.assetSupplierMaster));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IAssetSupplierMaster>>) {
    result.subscribe((res: HttpResponse<IAssetSupplierMaster>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
}
