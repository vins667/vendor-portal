import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { IOrganizationType } from 'app/shared/model/organization-type.model';
import { OrganizationTypeService } from './organization-type.service';

@Component({
  selector: 'jhi-organization-type-update',
  templateUrl: './organization-type-update.component.html'
})
export class OrganizationTypeUpdateComponent implements OnInit {
  organizationType: IOrganizationType;
  isSaving: boolean;
  createdDate: string;
  lastUpdatedDate: string;

  constructor(protected organizationTypeService: OrganizationTypeService, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ organizationType }) => {
      this.organizationType = organizationType;
      this.createdDate = this.organizationType.createdDate != null ? this.organizationType.createdDate.format(DATE_TIME_FORMAT) : null;
      this.lastUpdatedDate =
        this.organizationType.lastUpdatedDate != null ? this.organizationType.lastUpdatedDate.format(DATE_TIME_FORMAT) : null;
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    this.organizationType.createdDate = this.createdDate != null ? moment(this.createdDate, DATE_TIME_FORMAT) : null;
    this.organizationType.lastUpdatedDate = this.lastUpdatedDate != null ? moment(this.lastUpdatedDate, DATE_TIME_FORMAT) : null;
    if (this.organizationType.id !== undefined) {
      this.subscribeToSaveResponse(this.organizationTypeService.update(this.organizationType));
    } else {
      this.subscribeToSaveResponse(this.organizationTypeService.create(this.organizationType));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IOrganizationType>>) {
    result.subscribe((res: HttpResponse<IOrganizationType>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
}
