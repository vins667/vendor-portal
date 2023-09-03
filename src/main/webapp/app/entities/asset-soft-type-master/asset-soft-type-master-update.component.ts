import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { IAssetSoftTypeMaster } from 'app/shared/model/asset-soft-type-master.model';
import { AssetSoftTypeMasterService } from './asset-soft-type-master.service';

@Component({
  selector: 'jhi-asset-soft-type-master-update',
  templateUrl: './asset-soft-type-master-update.component.html'
})
export class AssetSoftTypeMasterUpdateComponent implements OnInit {
  assetSoftTypeMaster: IAssetSoftTypeMaster;
  isSaving: boolean;
  createdDate: string;
  lastUpdatedDate: string;

  constructor(protected assetSoftTypeMasterService: AssetSoftTypeMasterService, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ assetSoftTypeMaster }) => {
      this.assetSoftTypeMaster = assetSoftTypeMaster;
      this.createdDate =
        this.assetSoftTypeMaster.createdDate != null ? this.assetSoftTypeMaster.createdDate.format(DATE_TIME_FORMAT) : null;
      this.lastUpdatedDate =
        this.assetSoftTypeMaster.lastUpdatedDate != null ? this.assetSoftTypeMaster.lastUpdatedDate.format(DATE_TIME_FORMAT) : null;
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    this.assetSoftTypeMaster.createdDate = this.createdDate != null ? moment(this.createdDate, DATE_TIME_FORMAT) : null;
    this.assetSoftTypeMaster.lastUpdatedDate = this.lastUpdatedDate != null ? moment(this.lastUpdatedDate, DATE_TIME_FORMAT) : null;
    if (this.assetSoftTypeMaster.id !== undefined) {
      this.subscribeToSaveResponse(this.assetSoftTypeMasterService.update(this.assetSoftTypeMaster));
    } else {
      this.subscribeToSaveResponse(this.assetSoftTypeMasterService.create(this.assetSoftTypeMaster));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IAssetSoftTypeMaster>>) {
    result.subscribe((res: HttpResponse<IAssetSoftTypeMaster>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
}
