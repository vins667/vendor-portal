import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { IVendSubTypeMaster } from 'app/shared/model/vend-sub-type-master.model';
import { VendSubTypeMasterService } from './vend-sub-type-master.service';
import { IVendTypeMaster } from 'app/shared/model/vend-type-master.model';
import { VendTypeMasterService } from 'app/vendorportal/vend-type-master';

@Component({
  selector: 'jhi-vend-sub-type-master-update',
  templateUrl: './vend-sub-type-master-update.component.html'
})
export class VendSubTypeMasterUpdateComponent implements OnInit {
  vendSubTypeMaster: IVendSubTypeMaster;
  isSaving: boolean;

  vendtypemasters: IVendTypeMaster[];
  createdDate: string;
  lastUpdatedDate: string;

  constructor(
    protected vendSubTypeMasterService: VendSubTypeMasterService,
    protected vendTypeMasterService: VendTypeMasterService,
    protected activatedRoute: ActivatedRoute
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ vendSubTypeMaster }) => {
      this.vendSubTypeMaster = vendSubTypeMaster;
      this.createdDate = this.vendSubTypeMaster.createdDate != null ? this.vendSubTypeMaster.createdDate.format(DATE_TIME_FORMAT) : null;
      this.lastUpdatedDate =
        this.vendSubTypeMaster.lastUpdatedDate != null ? this.vendSubTypeMaster.lastUpdatedDate.format(DATE_TIME_FORMAT) : null;
    });
    this.vendTypeMasterService.query().subscribe((res: HttpResponse<IVendTypeMaster[]>) => {
      this.vendtypemasters = res.body;
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    this.vendSubTypeMaster.createdDate = this.createdDate != null ? moment(this.createdDate, DATE_TIME_FORMAT) : null;
    this.vendSubTypeMaster.lastUpdatedDate = this.lastUpdatedDate != null ? moment(this.lastUpdatedDate, DATE_TIME_FORMAT) : null;
    if (this.vendSubTypeMaster.id !== undefined) {
      this.subscribeToSaveResponse(this.vendSubTypeMasterService.update(this.vendSubTypeMaster));
    } else {
      this.subscribeToSaveResponse(this.vendSubTypeMasterService.create(this.vendSubTypeMaster));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IVendSubTypeMaster>>) {
    result.subscribe((res: HttpResponse<IVendSubTypeMaster>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
}
