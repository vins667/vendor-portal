import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';
import { ITravelLuggageDetails, TravelLuggageDetails } from 'app/shared/model/travel-luggage-details.model';
import { TravelLuggageDetailsService } from './travel-luggage-details.service';
import { ITravelApplicationMaster } from 'app/shared/model/travel-application-master.model';
import { TravelApplicationMasterService } from 'app/entities/travel-application-master/travel-application-master.service';

@Component({
  selector: 'jhi-travel-luggage-details-update',
  templateUrl: './travel-luggage-details-update.component.html'
})
export class TravelLuggageDetailsUpdateComponent implements OnInit {
  isSaving: boolean;

  travelapplicationmasters: ITravelApplicationMaster[];

  editForm = this.fb.group({
    id: [],
    luggageCount: [],
    luggageType: [null, [Validators.maxLength(20)]],
    approxWeight: [],
    extraLuggageReq: [],
    createdBy: [null, [Validators.maxLength(20)]],
    createdDate: [],
    lastUpdatedBy: [null, [Validators.maxLength(20)]],
    lastUpdatedDate: [],
    travelApplicationMaster: [null, Validators.required]
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected travelLuggageDetailsService: TravelLuggageDetailsService,
    protected travelApplicationMasterService: TravelApplicationMasterService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ travelLuggageDetails }) => {
      this.updateForm(travelLuggageDetails);
    });
    this.travelApplicationMasterService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<ITravelApplicationMaster[]>) => mayBeOk.ok),
        map((response: HttpResponse<ITravelApplicationMaster[]>) => response.body)
      )
      .subscribe(
        (res: ITravelApplicationMaster[]) => (this.travelapplicationmasters = res),
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  updateForm(travelLuggageDetails: ITravelLuggageDetails) {
    this.editForm.patchValue({
      id: travelLuggageDetails.id,
      luggageCount: travelLuggageDetails.luggageCount,
      luggageType: travelLuggageDetails.luggageType,
      approxWeight: travelLuggageDetails.approxWeight,
      extraLuggageReq: travelLuggageDetails.extraLuggageReq,
      createdBy: travelLuggageDetails.createdBy,
      createdDate: travelLuggageDetails.createdDate != null ? travelLuggageDetails.createdDate.format(DATE_TIME_FORMAT) : null,
      lastUpdatedBy: travelLuggageDetails.lastUpdatedBy,
      lastUpdatedDate: travelLuggageDetails.lastUpdatedDate != null ? travelLuggageDetails.lastUpdatedDate.format(DATE_TIME_FORMAT) : null,
      travelApplicationMaster: travelLuggageDetails.travelApplicationMaster
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const travelLuggageDetails = this.createFromForm();
    if (travelLuggageDetails.id !== undefined) {
      this.subscribeToSaveResponse(this.travelLuggageDetailsService.update(travelLuggageDetails));
    } else {
      this.subscribeToSaveResponse(this.travelLuggageDetailsService.create(travelLuggageDetails));
    }
  }

  private createFromForm(): ITravelLuggageDetails {
    return {
      ...new TravelLuggageDetails(),
      id: this.editForm.get(['id']).value,
      luggageCount: this.editForm.get(['luggageCount']).value,
      luggageType: this.editForm.get(['luggageType']).value,
      approxWeight: this.editForm.get(['approxWeight']).value,
      extraLuggageReq: this.editForm.get(['extraLuggageReq']).value,
      createdBy: this.editForm.get(['createdBy']).value,
      createdDate:
        this.editForm.get(['createdDate']).value != null ? moment(this.editForm.get(['createdDate']).value, DATE_TIME_FORMAT) : undefined,
      lastUpdatedBy: this.editForm.get(['lastUpdatedBy']).value,
      lastUpdatedDate:
        this.editForm.get(['lastUpdatedDate']).value != null
          ? moment(this.editForm.get(['lastUpdatedDate']).value, DATE_TIME_FORMAT)
          : undefined,
      travelApplicationMaster: this.editForm.get(['travelApplicationMaster']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITravelLuggageDetails>>) {
    result.subscribe(() => this.onSaveSuccess(), () => this.onSaveError());
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

  trackTravelApplicationMasterById(index: number, item: ITravelApplicationMaster) {
    return item.id;
  }
}
