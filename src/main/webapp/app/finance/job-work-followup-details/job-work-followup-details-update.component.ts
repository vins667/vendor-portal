import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { IJobWorkFollowupDetails, JobWorkFollowupDetails } from './job-work-followup-details.model';
import { JobWorkFollowupDetailsService } from './job-work-followup-details.service';
import { DateTimeAdapter, OWL_DATE_TIME_FORMATS, OWL_DATE_TIME_LOCALE } from 'ng-pick-datetime';
import { MY_MOMENT_FORMATS } from 'app/entities/asset-master';
import { MomentDateTimeAdapter } from 'ng-pick-datetime-moment';

@Component({
  selector: 'jhi-job-work-followup-details-update',
  templateUrl: './job-work-followup-details-update.component.html',
  providers: [
    // `MomentDateTimeAdapter` and `OWL_MOMENT_DATE_TIME_FORMATS` can be automatically provided by importing
    // `OwlMomentDateTimeModule` in your applications root module. We provide it at the component level
    // here, due to limitations of our example generation script.
    { provide: DateTimeAdapter, useClass: MomentDateTimeAdapter, deps: [OWL_DATE_TIME_LOCALE] },
    { provide: OWL_DATE_TIME_FORMATS, useValue: MY_MOMENT_FORMATS }
  ]
})
export class JobWorkFollowupDetailsUpdateComponent implements OnInit {
  isSaving: boolean;
  jobWorkFollowupDetails: IJobWorkFollowupDetails;
  createdDate: string;

  editForm = this.fb.group({
    id: [],
    finYear: [null, [Validators.required]],
    jobWorkDate: [null, [Validators.required]],
    submitDate: [null, [Validators.required]],
    remarks: [null, [Validators.maxLength(2000)]],
    jobWorkFollowup: []
  });

  constructor(
    protected jobWorkFollowupDetailsService: JobWorkFollowupDetailsService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ jobWorkFollowupDetails }) => {
      this.jobWorkFollowupDetails = jobWorkFollowupDetails;
      this.updateForm(jobWorkFollowupDetails);
    });
  }

  updateForm(jobWorkFollowupDetails: IJobWorkFollowupDetails) {
    this.editForm.patchValue({
      id: jobWorkFollowupDetails.id,
      finYear: jobWorkFollowupDetails.finYear,
      jobWorkDate: jobWorkFollowupDetails.jobWorkDate != null ? jobWorkFollowupDetails.jobWorkDate.format(DATE_TIME_FORMAT) : null,
      submitDate: jobWorkFollowupDetails.submitDate != null ? jobWorkFollowupDetails.submitDate.format(DATE_TIME_FORMAT) : null,
      remarks: jobWorkFollowupDetails.remarks,
      jobWorkFollowup: jobWorkFollowupDetails.jobWorkFollowup
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const jobWorkFollowupDetails = this.createFromForm();
    if (jobWorkFollowupDetails.id !== undefined) {
      this.subscribeToSaveResponse(this.jobWorkFollowupDetailsService.update(jobWorkFollowupDetails));
    } else {
      this.subscribeToSaveResponse(this.jobWorkFollowupDetailsService.create(jobWorkFollowupDetails));
    }
  }

  private createFromForm(): IJobWorkFollowupDetails {
    return {
      ...new JobWorkFollowupDetails(),
      id: this.editForm.get(['id']).value,
      finYear: this.editForm.get(['finYear']).value,
      jobWorkDate:
        this.editForm.get(['jobWorkDate']).value != null ? moment(this.editForm.get(['jobWorkDate']).value, DATE_TIME_FORMAT) : undefined,
      submitDate:
        this.editForm.get(['submitDate']).value != null ? moment(this.editForm.get(['submitDate']).value, DATE_TIME_FORMAT) : undefined,
      remarks: this.editForm.get(['remarks']).value,
      jobWorkFollowup: this.editForm.get(['jobWorkFollowup']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IJobWorkFollowupDetails>>) {
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
