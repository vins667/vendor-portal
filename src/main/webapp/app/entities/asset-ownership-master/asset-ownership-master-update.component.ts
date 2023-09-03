import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { IAssetOwnershipMaster } from 'app/shared/model/asset-ownership-master.model';
import { AssetOwnershipMasterService } from './asset-ownership-master.service';

@Component({
  selector: 'jhi-asset-ownership-master-update',
  templateUrl: './asset-ownership-master-update.component.html'
})
export class AssetOwnershipMasterUpdateComponent implements OnInit {
  assetOwnershipMaster: IAssetOwnershipMaster;
  isSaving: boolean;
  createdDate: string;
  lastUpdatedDate: string;

  constructor(protected assetOwnershipMasterService: AssetOwnershipMasterService, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ assetOwnershipMaster }) => {
      this.assetOwnershipMaster = assetOwnershipMaster;
      this.createdDate =
        this.assetOwnershipMaster.createdDate != null ? this.assetOwnershipMaster.createdDate.format(DATE_TIME_FORMAT) : null;
      this.lastUpdatedDate =
        this.assetOwnershipMaster.lastUpdatedDate != null ? this.assetOwnershipMaster.lastUpdatedDate.format(DATE_TIME_FORMAT) : null;
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    this.assetOwnershipMaster.createdDate = this.createdDate != null ? moment(this.createdDate, DATE_TIME_FORMAT) : null;
    this.assetOwnershipMaster.lastUpdatedDate = this.lastUpdatedDate != null ? moment(this.lastUpdatedDate, DATE_TIME_FORMAT) : null;
    if (this.assetOwnershipMaster.id !== undefined) {
      this.subscribeToSaveResponse(this.assetOwnershipMasterService.update(this.assetOwnershipMaster));
    } else {
      this.subscribeToSaveResponse(this.assetOwnershipMasterService.create(this.assetOwnershipMaster));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IAssetOwnershipMaster>>) {
    result.subscribe((res: HttpResponse<IAssetOwnershipMaster>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
}
