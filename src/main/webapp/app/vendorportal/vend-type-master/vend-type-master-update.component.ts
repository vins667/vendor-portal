import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { IVendTypeMaster } from 'app/shared/model/vend-type-master.model';
import { VendTypeMasterService } from './vend-type-master.service';

@Component({
  selector: 'jhi-vend-type-master-update',
  templateUrl: './vend-type-master-update.component.html'
})
export class VendTypeMasterUpdateComponent implements OnInit {
  vendTypeMaster: IVendTypeMaster;
  isSaving: boolean;
  createdDate: string;
  lastUpdatedDate: string;

  constructor(protected vendTypeMasterService: VendTypeMasterService, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ vendTypeMaster }) => {
      this.vendTypeMaster = vendTypeMaster;
      this.createdDate = this.vendTypeMaster.createdDate != null ? this.vendTypeMaster.createdDate.format(DATE_TIME_FORMAT) : null;
      this.lastUpdatedDate =
        this.vendTypeMaster.lastUpdatedDate != null ? this.vendTypeMaster.lastUpdatedDate.format(DATE_TIME_FORMAT) : null;
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    this.vendTypeMaster.createdDate = this.createdDate != null ? moment(this.createdDate, DATE_TIME_FORMAT) : null;
    this.vendTypeMaster.lastUpdatedDate = this.lastUpdatedDate != null ? moment(this.lastUpdatedDate, DATE_TIME_FORMAT) : null;
    if (this.vendTypeMaster.id !== undefined) {
      this.subscribeToSaveResponse(this.vendTypeMasterService.update(this.vendTypeMaster));
    } else {
      this.subscribeToSaveResponse(this.vendTypeMasterService.create(this.vendTypeMaster));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IVendTypeMaster>>) {
    result.subscribe((res: HttpResponse<IVendTypeMaster>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
}
