import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';
import { IAssetSubTypeMaster } from 'app/shared/model/asset-sub-type-master.model';
import { AssetSubTypeMasterService } from './asset-sub-type-master.service';
import { IAssetTypeMaster } from 'app/shared/model/asset-type-master.model';
import { AssetTypeMasterService } from 'app/entities/asset-type-master';

@Component({
  selector: 'jhi-asset-sub-type-master-update',
  templateUrl: './asset-sub-type-master-update.component.html'
})
export class AssetSubTypeMasterUpdateComponent implements OnInit {
  assetSubTypeMaster: IAssetSubTypeMaster;
  isSaving: boolean;

  assettypemasters: IAssetTypeMaster[];
  createdDate: string;
  lastUpdatedDate: string;

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected assetSubTypeMasterService: AssetSubTypeMasterService,
    protected assetTypeMasterService: AssetTypeMasterService,
    protected activatedRoute: ActivatedRoute
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ assetSubTypeMaster }) => {
      this.assetSubTypeMaster = assetSubTypeMaster;
      this.createdDate = this.assetSubTypeMaster.createdDate != null ? this.assetSubTypeMaster.createdDate.format(DATE_TIME_FORMAT) : null;
      this.lastUpdatedDate =
        this.assetSubTypeMaster.lastUpdatedDate != null ? this.assetSubTypeMaster.lastUpdatedDate.format(DATE_TIME_FORMAT) : null;
    });
    this.assetTypeMasterService.query().subscribe(
      (res: HttpResponse<IAssetTypeMaster[]>) => {
        this.assettypemasters = res.body;
      },
      (res: HttpErrorResponse) => this.onError(res.message)
    );
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    this.assetSubTypeMaster.createdDate = this.createdDate != null ? moment(this.createdDate, DATE_TIME_FORMAT) : null;
    this.assetSubTypeMaster.lastUpdatedDate = this.lastUpdatedDate != null ? moment(this.lastUpdatedDate, DATE_TIME_FORMAT) : null;
    if (this.assetSubTypeMaster.id !== undefined) {
      this.subscribeToSaveResponse(this.assetSubTypeMasterService.update(this.assetSubTypeMaster));
    } else {
      this.subscribeToSaveResponse(this.assetSubTypeMasterService.create(this.assetSubTypeMaster));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IAssetSubTypeMaster>>) {
    result.subscribe((res: HttpResponse<IAssetSubTypeMaster>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

  trackAssetTypeMasterById(index: number, item: IAssetTypeMaster) {
    return item.id;
  }
}
