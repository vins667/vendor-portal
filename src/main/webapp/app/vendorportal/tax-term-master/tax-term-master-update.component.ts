import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';
import { ITaxTermMaster } from 'app/shared/model/tax-term-master.model';
import { TaxTermMasterService } from './tax-term-master.service';

@Component({
  selector: 'jhi-tax-term-master-update',
  templateUrl: './tax-term-master-update.component.html'
})
export class TaxTermMasterUpdateComponent implements OnInit {
  taxTermMaster: ITaxTermMaster;
  isSaving: boolean;

  createdDate: string;
  lastUpdatedDate: string;

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected taxTermMasterService: TaxTermMasterService,
    protected activatedRoute: ActivatedRoute
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ taxTermMaster }) => {
      this.taxTermMaster = taxTermMaster;
      this.createdDate = this.taxTermMaster.createdDate != null ? this.taxTermMaster.createdDate.format(DATE_TIME_FORMAT) : null;
      this.lastUpdatedDate =
        this.taxTermMaster.lastUpdatedDate != null ? this.taxTermMaster.lastUpdatedDate.format(DATE_TIME_FORMAT) : null;
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    this.taxTermMaster.createdDate = this.createdDate != null ? moment(this.createdDate, DATE_TIME_FORMAT) : null;
    this.taxTermMaster.lastUpdatedDate = this.lastUpdatedDate != null ? moment(this.lastUpdatedDate, DATE_TIME_FORMAT) : null;
    if (this.taxTermMaster.id !== undefined) {
      this.subscribeToSaveResponse(this.taxTermMasterService.update(this.taxTermMaster));
    } else {
      this.subscribeToSaveResponse(this.taxTermMasterService.create(this.taxTermMaster));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITaxTermMaster>>) {
    result.subscribe((res: HttpResponse<ITaxTermMaster>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
