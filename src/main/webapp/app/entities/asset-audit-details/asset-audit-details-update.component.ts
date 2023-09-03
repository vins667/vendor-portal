import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { IAssetAuditDetails } from 'app/shared/model/asset-audit-details.model';
import { AssetAuditDetailsService } from './asset-audit-details.service';

@Component({
  selector: 'jhi-asset-audit-details-update',
  templateUrl: './asset-audit-details-update.component.html'
})
export class AssetAuditDetailsUpdateComponent implements OnInit {
  assetAuditDetails: IAssetAuditDetails;
  isSaving: boolean;
  osInstallationDate: string;
  createdDate: string;

  constructor(protected assetAuditDetailsService: AssetAuditDetailsService, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ assetAuditDetails }) => {
      this.assetAuditDetails = assetAuditDetails;
      this.osInstallationDate =
        this.assetAuditDetails.osInstallationDate != null ? this.assetAuditDetails.osInstallationDate.format(DATE_TIME_FORMAT) : null;
      this.createdDate = this.assetAuditDetails.createdDate != null ? this.assetAuditDetails.createdDate.format(DATE_TIME_FORMAT) : null;
    });
  }
  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    this.assetAuditDetails.osInstallationDate = this.osInstallationDate != null ? moment(this.osInstallationDate, DATE_TIME_FORMAT) : null;
    this.assetAuditDetails.createdDate = this.createdDate != null ? moment(this.createdDate, DATE_TIME_FORMAT) : null;
    if (this.assetAuditDetails.id !== undefined) {
      this.subscribeToSaveResponse(this.assetAuditDetailsService.update(this.assetAuditDetails));
    } else {
      this.subscribeToSaveResponse(this.assetAuditDetailsService.create(this.assetAuditDetails));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IAssetAuditDetails>>) {
    result.subscribe((res: HttpResponse<IAssetAuditDetails>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
}
