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
import { ITravelFlightDetails, TravelFlightDetails } from 'app/shared/model/travel-flight-details.model';
import { TravelFlightDetailsService } from './travel-flight-details.service';
import { ITravelApplicationMaster } from 'app/shared/model/travel-application-master.model';
import { TravelApplicationMasterService } from 'app/entities/travel-application-master/travel-application-master.service';

@Component({
  selector: 'jhi-travel-flight-details-update',
  templateUrl: './travel-flight-details-update.component.html'
})
export class TravelFlightDetailsUpdateComponent implements OnInit {
  isSaving: boolean;

  travelapplicationmasters: ITravelApplicationMaster[];

  editForm = this.fb.group({
    id: [],
    travelDate: [],
    ticketType: [null, [Validators.maxLength(20)]],
    ticketNo: [],
    departureDate: [],
    arrivalDate: [],
    farePrice: [],
    createdBy: [],
    createdDate: [],
    lastUpdatedBy: [null, [Validators.maxLength(20)]],
    lastUpdatedDate: [],
    travelApplicationMaster: [null, Validators.required]
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected travelFlightDetailsService: TravelFlightDetailsService,
    protected travelApplicationMasterService: TravelApplicationMasterService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ travelFlightDetails }) => {
      this.updateForm(travelFlightDetails);
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

  updateForm(travelFlightDetails: ITravelFlightDetails) {
    this.editForm.patchValue({
      id: travelFlightDetails.id,
      travelDate: travelFlightDetails.travelDate != null ? travelFlightDetails.travelDate.format(DATE_TIME_FORMAT) : null,
      ticketType: travelFlightDetails.ticketType,
      ticketNo: travelFlightDetails.ticketNo,
      departureDate: travelFlightDetails.departureDate != null ? travelFlightDetails.departureDate.format(DATE_TIME_FORMAT) : null,
      arrivalDate: travelFlightDetails.arrivalDate != null ? travelFlightDetails.arrivalDate.format(DATE_TIME_FORMAT) : null,
      farePrice: travelFlightDetails.farePrice,
      createdBy: travelFlightDetails.createdBy,
      createdDate: travelFlightDetails.createdDate != null ? travelFlightDetails.createdDate.format(DATE_TIME_FORMAT) : null,
      lastUpdatedBy: travelFlightDetails.lastUpdatedBy,
      lastUpdatedDate: travelFlightDetails.lastUpdatedDate != null ? travelFlightDetails.lastUpdatedDate.format(DATE_TIME_FORMAT) : null,
      travelApplicationMaster: travelFlightDetails.travelApplicationMaster
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const travelFlightDetails = this.createFromForm();
    if (travelFlightDetails.id !== undefined) {
      this.subscribeToSaveResponse(this.travelFlightDetailsService.update(travelFlightDetails));
    } else {
      this.subscribeToSaveResponse(this.travelFlightDetailsService.create(travelFlightDetails));
    }
  }

  private createFromForm(): ITravelFlightDetails {
    return {
      ...new TravelFlightDetails(),
      id: this.editForm.get(['id']).value,
      travelDate:
        this.editForm.get(['travelDate']).value != null ? moment(this.editForm.get(['travelDate']).value, DATE_TIME_FORMAT) : undefined,
      ticketType: this.editForm.get(['ticketType']).value,
      ticketNo: this.editForm.get(['ticketNo']).value,
      departureDate:
        this.editForm.get(['departureDate']).value != null
          ? moment(this.editForm.get(['departureDate']).value, DATE_TIME_FORMAT)
          : undefined,
      arrivalDate:
        this.editForm.get(['arrivalDate']).value != null ? moment(this.editForm.get(['arrivalDate']).value, DATE_TIME_FORMAT) : undefined,
      farePrice: this.editForm.get(['farePrice']).value,
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

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITravelFlightDetails>>) {
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
