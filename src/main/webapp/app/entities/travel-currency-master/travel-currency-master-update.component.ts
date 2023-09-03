import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { ITravelCurrencyMaster, TravelCurrencyMaster } from 'app/shared/model/travel-currency-master.model';
import { TravelCurrencyMasterService } from './travel-currency-master.service';

@Component({
  selector: 'jhi-travel-currency-master-update',
  templateUrl: './travel-currency-master-update.component.html'
})
export class TravelCurrencyMasterUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    currencyType: [null, [Validators.required, Validators.maxLength(10)]],
    currencyName: [null, [Validators.required, Validators.maxLength(20)]],
    status: [],
    createdBy: [null, [Validators.maxLength(20)]],
    createdDate: [],
    lastUpdatedBy: [null, [Validators.maxLength(20)]],
    lastUpdatedDate: [],
    conversionFactor: []
  });

  constructor(
    protected travelCurrencyMasterService: TravelCurrencyMasterService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ travelCurrencyMaster }) => {
      this.updateForm(travelCurrencyMaster);
    });
  }

  updateForm(travelCurrencyMaster: ITravelCurrencyMaster) {
    this.editForm.patchValue({
      id: travelCurrencyMaster.id,
      currencyType: travelCurrencyMaster.currencyType,
      currencyName: travelCurrencyMaster.currencyName,
      status: travelCurrencyMaster.status,
      createdBy: travelCurrencyMaster.createdBy,
      createdDate: travelCurrencyMaster.createdDate != null ? travelCurrencyMaster.createdDate.format(DATE_TIME_FORMAT) : null,
      lastUpdatedBy: travelCurrencyMaster.lastUpdatedBy,
      lastUpdatedDate: travelCurrencyMaster.lastUpdatedDate != null ? travelCurrencyMaster.lastUpdatedDate.format(DATE_TIME_FORMAT) : null,
      conversionFactor: travelCurrencyMaster.conversionFactor
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const travelCurrencyMaster = this.createFromForm();
    if (travelCurrencyMaster.id !== undefined) {
      this.subscribeToSaveResponse(this.travelCurrencyMasterService.update(travelCurrencyMaster));
    } else {
      this.subscribeToSaveResponse(this.travelCurrencyMasterService.create(travelCurrencyMaster));
    }
  }

  private createFromForm(): ITravelCurrencyMaster {
    return {
      ...new TravelCurrencyMaster(),
      id: this.editForm.get(['id']).value,
      currencyType: this.editForm.get(['currencyType']).value,
      currencyName: this.editForm.get(['currencyName']).value,
      status: this.editForm.get(['status']).value,
      createdBy: this.editForm.get(['createdBy']).value,
      createdDate:
        this.editForm.get(['createdDate']).value != null ? moment(this.editForm.get(['createdDate']).value, DATE_TIME_FORMAT) : undefined,
      lastUpdatedBy: this.editForm.get(['lastUpdatedBy']).value,
      lastUpdatedDate:
        this.editForm.get(['lastUpdatedDate']).value != null
          ? moment(this.editForm.get(['lastUpdatedDate']).value, DATE_TIME_FORMAT)
          : undefined,
      conversionFactor: this.editForm.get(['conversionFactor']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITravelCurrencyMaster>>) {
    result.subscribe(() => this.onSaveSuccess(), () => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
}
