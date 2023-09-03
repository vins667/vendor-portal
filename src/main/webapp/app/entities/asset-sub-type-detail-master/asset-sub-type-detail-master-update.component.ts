import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { JhiAlertService } from 'ng-jhipster';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { IAssetSubTypeDetailMaster } from 'app/shared/model/asset-sub-type-detail-master.model';
import { AssetSubTypeDetailMasterService } from './asset-sub-type-detail-master.service';
import { IAssetSubTypeMaster } from 'app/shared/model/asset-sub-type-master.model';
import { AssetSubTypeMasterService } from 'app/entities/asset-sub-type-master';

@Component({
  selector: 'jhi-asset-sub-type-detail-master-update',
  templateUrl: './asset-sub-type-detail-master-update.component.html'
})
export class AssetSubTypeDetailMasterUpdateComponent implements OnInit {
  assetSubTypeDetailMaster: IAssetSubTypeDetailMaster;
  isSaving: boolean;

  assetsubtypemasters: IAssetSubTypeMaster[];
  createdDate: string;
  lastUpdatedDate: string;

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected assetSubTypeDetailMasterService: AssetSubTypeDetailMasterService,
    protected assetSubTypeMasterService: AssetSubTypeMasterService,
    protected activatedRoute: ActivatedRoute
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ assetSubTypeDetailMaster }) => {
      this.assetSubTypeDetailMaster = assetSubTypeDetailMaster;
      this.createdDate =
        this.assetSubTypeDetailMaster.createdDate != null ? this.assetSubTypeDetailMaster.createdDate.format(DATE_TIME_FORMAT) : null;
      this.lastUpdatedDate =
        this.assetSubTypeDetailMaster.lastUpdatedDate != null
          ? this.assetSubTypeDetailMaster.lastUpdatedDate.format(DATE_TIME_FORMAT)
          : null;
    });
    this.assetSubTypeMasterService.query().subscribe(
      (res: HttpResponse<IAssetSubTypeMaster[]>) => {
        this.assetsubtypemasters = res.body;
      },
      (res: HttpErrorResponse) => this.onError(res.message)
    );
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    this.assetSubTypeDetailMaster.createdDate = this.createdDate != null ? moment(this.createdDate, DATE_TIME_FORMAT) : null;
    this.assetSubTypeDetailMaster.lastUpdatedDate = this.lastUpdatedDate != null ? moment(this.lastUpdatedDate, DATE_TIME_FORMAT) : null;
    if (this.assetSubTypeDetailMaster.id !== undefined) {
      this.subscribeToSaveResponse(this.assetSubTypeDetailMasterService.update(this.assetSubTypeDetailMaster));
    } else {
      this.subscribeToSaveResponse(this.assetSubTypeDetailMasterService.create(this.assetSubTypeDetailMaster));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IAssetSubTypeDetailMaster>>) {
    result.subscribe(
      (res: HttpResponse<IAssetSubTypeDetailMaster>) => this.onSaveSuccess(),
      (res: HttpErrorResponse) => this.onSaveError()
    );
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }

  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }
}
