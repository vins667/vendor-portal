import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { IAssetLocationMaster } from 'app/shared/model/asset-location-master.model';
import { AssetLocationMasterService } from './asset-location-master.service';

@Component({
  selector: 'jhi-asset-location-master-update',
  templateUrl: './asset-location-master-update.component.html'
})
export class AssetLocationMasterUpdateComponent implements OnInit {
  assetLocationMaster: IAssetLocationMaster;
  isSaving: boolean;
  createdDate: string;
  lastUpdatedDate: string;

  constructor(protected assetLocationMasterService: AssetLocationMasterService, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ assetLocationMaster }) => {
      this.assetLocationMaster = assetLocationMaster;
      this.createdDate =
        this.assetLocationMaster.createdDate != null ? this.assetLocationMaster.createdDate.format(DATE_TIME_FORMAT) : null;
      this.lastUpdatedDate =
        this.assetLocationMaster.lastUpdatedDate != null ? this.assetLocationMaster.lastUpdatedDate.format(DATE_TIME_FORMAT) : null;
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    this.assetLocationMaster.createdDate = this.createdDate != null ? moment(this.createdDate, DATE_TIME_FORMAT) : null;
    this.assetLocationMaster.lastUpdatedDate = this.lastUpdatedDate != null ? moment(this.lastUpdatedDate, DATE_TIME_FORMAT) : null;
    if (this.assetLocationMaster.id !== undefined) {
      this.subscribeToSaveResponse(this.assetLocationMasterService.update(this.assetLocationMaster));
    } else {
      this.subscribeToSaveResponse(this.assetLocationMasterService.create(this.assetLocationMaster));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IAssetLocationMaster>>) {
    result.subscribe((res: HttpResponse<IAssetLocationMaster>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
}
