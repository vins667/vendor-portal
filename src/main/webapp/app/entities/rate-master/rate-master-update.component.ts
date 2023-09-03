import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { IRateMaster, RateMaster } from 'app/shared/model/rate-master.model';
import { RateMasterService } from './rate-master.service';
import { DateTimeAdapter, OWL_DATE_TIME_LOCALE, OWL_DATE_TIME_FORMATS } from 'ng-pick-datetime';
import { MomentDateTimeAdapter } from 'ng-pick-datetime-moment';
import { MY_MOMENT_FORMATS } from '../comp-off-master/comp-off-master-update.component';

@Component({
  selector: 'jhi-rate-master-update',
  templateUrl: './rate-master-update.component.html',
  encapsulation: ViewEncapsulation.None,
  providers: [
    // `MomentDateTimeAdapter` and `OWL_MOMENT_DATE_TIME_FORMATS` can be automatically provided by importing
    // `OwlMomentDateTimeModule` in your applications root module. We provide it at the component level
    // here, due to limitations of our example generation script.
    { provide: DateTimeAdapter, useClass: MomentDateTimeAdapter, deps: [OWL_DATE_TIME_LOCALE] },
    { provide: OWL_DATE_TIME_FORMATS, useValue: MY_MOMENT_FORMATS }
  ]
})
export class RateMasterUpdateComponent implements OnInit {
  isSaving: boolean;
  leaveDateFrom: any;

  editForm = this.fb.group({
    id: [],
    rate: [null, [Validators.required]],
    startDate: [null],
    endDate: [],
    createdBy: [null, [Validators.maxLength(50)]],
    createdDate: [],
    lastUpdatedBy: [null, [Validators.maxLength(50)]],
    lastUpdatedDate: [],
    leaveDateFrom: []
  });

  constructor(protected rateMasterService: RateMasterService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ rateMaster }) => {
      this.leaveDateFrom = rateMaster.startDate != null ? rateMaster.startDate : null;
      this.updateForm(rateMaster);
    });
  }

  updateForm(rateMaster: IRateMaster) {
    this.editForm.patchValue({
      id: rateMaster.id,
      rate: rateMaster.rate,
      startDate: rateMaster.startDate != null ? rateMaster.startDate.format(DATE_TIME_FORMAT) : null,
      endDate: rateMaster.endDate != null ? rateMaster.endDate.format(DATE_TIME_FORMAT) : null,
      createdBy: rateMaster.createdBy,
      createdDate: rateMaster.createdDate != null ? rateMaster.createdDate.format(DATE_TIME_FORMAT) : null,
      lastUpdatedBy: rateMaster.lastUpdatedBy,
      lastUpdatedDate: rateMaster.lastUpdatedDate != null ? rateMaster.lastUpdatedDate.format(DATE_TIME_FORMAT) : null,
      leaveDateFrom: new Date()
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const rateMaster = this.createFromForm();
    if (rateMaster.id !== undefined) {
      this.subscribeToSaveResponse(this.rateMasterService.update(rateMaster));
    } else {
      this.subscribeToSaveResponse(this.rateMasterService.create(rateMaster));
    }
  }

  private createFromForm(): IRateMaster {
    return {
      ...new RateMaster(),
      id: this.editForm.get(['id']).value,
      rate: this.editForm.get(['rate']).value,
      startDate:
        this.editForm.get(['leaveDateFrom']).value != null
          ? moment(this.editForm.get(['leaveDateFrom']).value, DATE_TIME_FORMAT)
          : undefined,
      endDate: this.editForm.get(['endDate']).value != null ? moment(this.editForm.get(['endDate']).value, DATE_TIME_FORMAT) : undefined,
      createdBy: this.editForm.get(['createdBy']).value,
      createdDate:
        this.editForm.get(['createdDate']).value != null ? moment(this.editForm.get(['createdDate']).value, DATE_TIME_FORMAT) : undefined,
      lastUpdatedBy: this.editForm.get(['lastUpdatedBy']).value,
      lastUpdatedDate:
        this.editForm.get(['lastUpdatedDate']).value != null
          ? moment(this.editForm.get(['lastUpdatedDate']).value, DATE_TIME_FORMAT)
          : undefined
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IRateMaster>>) {
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
