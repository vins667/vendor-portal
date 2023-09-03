import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { IMobileVersion } from 'app/shared/model/mobile-version.model';
import { MobileVersionService } from './mobile-version.service';

@Component({
  selector: 'jhi-mobile-version-update',
  templateUrl: './mobile-version-update.component.html'
})
export class MobileVersionUpdateComponent implements OnInit {
  mobileVersion: IMobileVersion;
  isSaving: boolean;
  closedDate: string;
  createdDate: string;

  constructor(protected mobileVersionService: MobileVersionService, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mobileVersion }) => {
      this.mobileVersion = mobileVersion;
      this.closedDate = this.mobileVersion.closedDate != null ? this.mobileVersion.closedDate.format(DATE_TIME_FORMAT) : null;
      this.createdDate = this.mobileVersion.createdDate != null ? this.mobileVersion.createdDate.format(DATE_TIME_FORMAT) : null;
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    this.mobileVersion.closedDate = this.closedDate != null ? moment(this.closedDate, DATE_TIME_FORMAT) : null;
    this.mobileVersion.createdDate = this.createdDate != null ? moment(this.createdDate, DATE_TIME_FORMAT) : null;
    if (this.mobileVersion.id !== undefined) {
      this.subscribeToSaveResponse(this.mobileVersionService.update(this.mobileVersion));
    } else {
      this.subscribeToSaveResponse(this.mobileVersionService.create(this.mobileVersion));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMobileVersion>>) {
    result.subscribe((res: HttpResponse<IMobileVersion>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
}
