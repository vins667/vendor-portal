import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { IAssetCompanyMaster } from 'app/shared/model/asset-company-master.model';
import { AssetCompanyMasterService } from './asset-company-master.service';

@Component({
  selector: 'jhi-asset-company-master-update',
  templateUrl: './asset-company-master-update.component.html'
})
export class AssetCompanyMasterUpdateComponent implements OnInit {
  assetCompanyMaster: IAssetCompanyMaster;
  isSaving: boolean;
  createdDate: string;
  lastUpdatedDate: string;

  constructor(protected assetCompanyMasterService: AssetCompanyMasterService, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ assetCompanyMaster }) => {
      this.assetCompanyMaster = assetCompanyMaster;
      this.createdDate = this.assetCompanyMaster.createdDate != null ? this.assetCompanyMaster.createdDate.format(DATE_TIME_FORMAT) : null;
      this.lastUpdatedDate =
        this.assetCompanyMaster.lastUpdatedDate != null ? this.assetCompanyMaster.lastUpdatedDate.format(DATE_TIME_FORMAT) : null;
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    this.assetCompanyMaster.createdDate = this.createdDate != null ? moment(this.createdDate, DATE_TIME_FORMAT) : null;
    this.assetCompanyMaster.lastUpdatedDate = this.lastUpdatedDate != null ? moment(this.lastUpdatedDate, DATE_TIME_FORMAT) : null;
    if (this.assetCompanyMaster.id !== undefined) {
      this.subscribeToSaveResponse(this.assetCompanyMasterService.update(this.assetCompanyMaster));
    } else {
      this.subscribeToSaveResponse(this.assetCompanyMasterService.create(this.assetCompanyMaster));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IAssetCompanyMaster>>) {
    result.subscribe((res: HttpResponse<IAssetCompanyMaster>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
}
