import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { IAssetTypeMaster } from 'app/shared/model/asset-type-master.model';
import { AssetTypeMasterService } from './asset-type-master.service';

@Component({
  selector: 'jhi-asset-type-master-update',
  templateUrl: './asset-type-master-update.component.html'
})
export class AssetTypeMasterUpdateComponent implements OnInit {
  assetTypeMaster: IAssetTypeMaster;
  isSaving: boolean;
  createdDate: string;
  lastUpdatedDate: string;

  constructor(protected assetTypeMasterService: AssetTypeMasterService, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ assetTypeMaster }) => {
      this.assetTypeMaster = assetTypeMaster;
      this.createdDate = this.assetTypeMaster.createdDate != null ? this.assetTypeMaster.createdDate.format(DATE_TIME_FORMAT) : null;
      this.lastUpdatedDate =
        this.assetTypeMaster.lastUpdatedDate != null ? this.assetTypeMaster.lastUpdatedDate.format(DATE_TIME_FORMAT) : null;
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    this.assetTypeMaster.createdDate = this.createdDate != null ? moment(this.createdDate, DATE_TIME_FORMAT) : null;
    this.assetTypeMaster.lastUpdatedDate = this.lastUpdatedDate != null ? moment(this.lastUpdatedDate, DATE_TIME_FORMAT) : null;
    if (this.assetTypeMaster.id !== undefined) {
      this.subscribeToSaveResponse(this.assetTypeMasterService.update(this.assetTypeMaster));
    } else {
      this.subscribeToSaveResponse(this.assetTypeMasterService.create(this.assetTypeMaster));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IAssetTypeMaster>>) {
    result.subscribe((res: HttpResponse<IAssetTypeMaster>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
}
