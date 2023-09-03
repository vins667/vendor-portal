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
import { ITravelAccommodationDetails, TravelAccommodationDetails } from 'app/shared/model/travel-accommodation-details.model';
import { TravelAccommodationDetailsService } from './travel-accommodation-details.service';
import { ITravelApplicationMaster } from 'app/shared/model/travel-application-master.model';
import { TravelApplicationMasterService } from 'app/entities/travel-application-master/travel-application-master.service';

@Component({
  selector: 'jhi-travel-accommodation-details-update',
  templateUrl: './travel-accommodation-details-update.component.html'
})
export class TravelAccommodationDetailsUpdateComponent implements OnInit {
  isSaving: boolean;

  travelapplicationmasters: ITravelApplicationMaster[];

  editForm = this.fb.group({
    id: [],
    accommodationDate: [],
    accommodationType: [],
    accommodationName: [null, [Validators.maxLength(100)]],
    accommodationTarif: [],
    daysStay: [],
    earlyCheckin: [],
    createdBy: [null, [Validators.maxLength(20)]],
    createdDate: [],
    lastUpdatedBy: [null, [Validators.maxLength(20)]],
    lastUpdatedDate: [],
    travelApplicationMaster: [null, Validators.required]
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected travelAccommodationDetailsService: TravelAccommodationDetailsService,
    protected travelApplicationMasterService: TravelApplicationMasterService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ travelAccommodationDetails }) => {
      this.updateForm(travelAccommodationDetails);
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

  updateForm(travelAccommodationDetails: ITravelAccommodationDetails) {
    this.editForm.patchValue({
      id: travelAccommodationDetails.id,
      accommodationDate:
        travelAccommodationDetails.accommodationDate != null ? travelAccommodationDetails.accommodationDate.format(DATE_TIME_FORMAT) : null,
      accommodationType: travelAccommodationDetails.accommodationType,
      accommodationName: travelAccommodationDetails.accommodationName,
      accommodationTarif: travelAccommodationDetails.accommodationTarif,
      daysStay: travelAccommodationDetails.daysStay,
      earlyCheckin: travelAccommodationDetails.earlyCheckin,
      createdBy: travelAccommodationDetails.createdBy,
      createdDate: travelAccommodationDetails.createdDate != null ? travelAccommodationDetails.createdDate.format(DATE_TIME_FORMAT) : null,
      lastUpdatedBy: travelAccommodationDetails.lastUpdatedBy,
      lastUpdatedDate:
        travelAccommodationDetails.lastUpdatedDate != null ? travelAccommodationDetails.lastUpdatedDate.format(DATE_TIME_FORMAT) : null,
      travelApplicationMaster: travelAccommodationDetails.travelApplicationMaster
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const travelAccommodationDetails = this.createFromForm();
    if (travelAccommodationDetails.id !== undefined) {
      this.subscribeToSaveResponse(this.travelAccommodationDetailsService.update(travelAccommodationDetails));
    } else {
      this.subscribeToSaveResponse(this.travelAccommodationDetailsService.create(travelAccommodationDetails));
    }
  }

  private createFromForm(): ITravelAccommodationDetails {
    return {
      ...new TravelAccommodationDetails(),
      id: this.editForm.get(['id']).value,
      accommodationDate:
        this.editForm.get(['accommodationDate']).value != null
          ? moment(this.editForm.get(['accommodationDate']).value, DATE_TIME_FORMAT)
          : undefined,
      accommodationType: this.editForm.get(['accommodationType']).value,
      accommodationName: this.editForm.get(['accommodationName']).value,
      accommodationTarif: this.editForm.get(['accommodationTarif']).value,
      daysStay: this.editForm.get(['daysStay']).value,
      earlyCheckin: this.editForm.get(['earlyCheckin']).value,
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

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITravelAccommodationDetails>>) {
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
