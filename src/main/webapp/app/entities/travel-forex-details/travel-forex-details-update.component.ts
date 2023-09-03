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
import { ITravelForexDetails, TravelForexDetails } from 'app/shared/model/travel-forex-details.model';
import { TravelForexDetailsService } from './travel-forex-details.service';
import { ITravelApplicationMaster } from 'app/shared/model/travel-application-master.model';
import { TravelApplicationMasterService } from 'app/entities/travel-application-master/travel-application-master.service';

@Component({
  selector: 'jhi-travel-forex-details-update',
  templateUrl: './travel-forex-details-update.component.html'
})
export class TravelForexDetailsUpdateComponent implements OnInit {
  isSaving: boolean;

  travelapplicationmasters: ITravelApplicationMaster[];

  editForm = this.fb.group({
    id: [],
    forexType: [null, [Validators.maxLength(15)]],
    requiredAmount: [],
    approvedAmount: [],
    createdBy: [null, [Validators.maxLength(20)]],
    createdDate: [],
    lastUpdatedBy: [null, [Validators.maxLength(20)]],
    lastUpdatedDate: [],
    travelApplicationMaster: [null, Validators.required]
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected travelForexDetailsService: TravelForexDetailsService,
    protected travelApplicationMasterService: TravelApplicationMasterService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ travelForexDetails }) => {
      this.updateForm(travelForexDetails);
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

  updateForm(travelForexDetails: ITravelForexDetails) {
    this.editForm.patchValue({
      id: travelForexDetails.id,
      forexType: travelForexDetails.forexType,
      requiredAmount: travelForexDetails.requiredAmount,
      approvedAmount: travelForexDetails.approvedAmount,
      createdBy: travelForexDetails.createdBy,
      createdDate: travelForexDetails.createdDate != null ? travelForexDetails.createdDate.format(DATE_TIME_FORMAT) : null,
      lastUpdatedBy: travelForexDetails.lastUpdatedBy,
      lastUpdatedDate: travelForexDetails.lastUpdatedDate != null ? travelForexDetails.lastUpdatedDate.format(DATE_TIME_FORMAT) : null,
      travelApplicationMaster: travelForexDetails.travelApplicationMaster
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const travelForexDetails = this.createFromForm();
    if (travelForexDetails.id !== undefined) {
      this.subscribeToSaveResponse(this.travelForexDetailsService.update(travelForexDetails));
    } else {
      this.subscribeToSaveResponse(this.travelForexDetailsService.create(travelForexDetails));
    }
  }

  private createFromForm(): ITravelForexDetails {
    return {
      ...new TravelForexDetails(),
      id: this.editForm.get(['id']).value,
      forexType: this.editForm.get(['forexType']).value,
      requiredAmount: this.editForm.get(['requiredAmount']).value,
      approvedAmount: this.editForm.get(['approvedAmount']).value,
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

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITravelForexDetails>>) {
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
