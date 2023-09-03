import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { DateTimeAdapter, OWL_DATE_TIME_FORMATS, OWL_DATE_TIME_LOCALE } from 'ng-pick-datetime';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as _moment from 'moment';
import { MomentDateTimeAdapter } from 'ng-pick-datetime-moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { IPreviousEmploymentDetails, PreviousEmploymentDetails } from 'app/shared/model/previous-employment-details.model';
import { PreviousEmploymentDetailsService } from './previous-employment-details.service';
import { ITdsYearMaster } from 'app/shared/model/tds-year-master.model';
const moment = (_moment as any).default ? (_moment as any).default : _moment;
export const MY_MOMENT_FORMATS = {
  parseInput: 'DD-MM-YYYY LT',
  fullPickerInput: 'DD-MM-YYYY LT',
  datePickerInput: 'DD-MM-YYYY',
  timePickerInput: 'HH:mm',
  monthYearLabel: 'MMM YYYY',
  dateA11yLabel: 'LL',
  monthYearA11yLabel: 'MMMM YYYY'
};
@Component({
  selector: 'jhi-previous-employment-details-update',
  templateUrl: './previous-employment-details-update.component.html',
  encapsulation: ViewEncapsulation.None,
  providers: [
    // `MomentDateTimeAdapter` and `OWL_MOMENT_DATE_TIME_FORMATS` can be automatically provided by importing
    // `OwlMomentDateTimeModule` in your applications root module. We provide it at the component level
    // here, due to limitations of our example generation script.
    { provide: DateTimeAdapter, useClass: MomentDateTimeAdapter, deps: [OWL_DATE_TIME_LOCALE] },
    { provide: OWL_DATE_TIME_FORMATS, useValue: MY_MOMENT_FORMATS }
  ]
})
export class PreviousEmploymentDetailsUpdateComponent implements OnInit {
  isSaving: boolean;
  dateFrom: any;
  dateTo: any;
  tdsYearMaster: ITdsYearMaster;
  editForm = this.fb.group({
    id: [],
    cardNo: [],
    financeYear: [],
    dateFrom: [],
    dateTo: [],
    previousEmployer: [null, [Validators.required, Validators.maxLength(100)]],
    landLordName: [null, [Validators.maxLength(100)]],
    landLordPan: [],
    landLordAddress: [null, [Validators.maxLength(200)]],
    monthRent: [],
    basic: [],
    hra: [],
    cta: [],
    spa: [],
    mda: [],
    others: [],
    epf: [],
    vpf: [],
    tds: [],
    createdBy: [null, [Validators.maxLength(50)]],
    createdDate: [],
    lastUpdatedBy: [null, [Validators.maxLength(50)]],
    lastUpdatedDate: []
  });

  constructor(
    protected previousEmploymentDetailsService: PreviousEmploymentDetailsService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    const dt = new Date();
    const month = dt.getMonth(),
      year = dt.getFullYear();
    const FirstDay = new Date(year, month, 1);
    const LastDay = new Date(year, month + 1, 0);
    this.dateFrom = FirstDay;
    this.dateFrom.setHours(0, 0, 0, 0);
    this.dateTo = LastDay;
    this.dateTo.setHours(0, 0, 0, 0);
    this.activatedRoute.data.subscribe(({ previousEmploymentDetails }) => {
      this.updateForm(previousEmploymentDetails);
    });
  }

  updateForm(previousEmploymentDetails: IPreviousEmploymentDetails) {
    this.editForm.patchValue({
      id: previousEmploymentDetails.id,
      financeYear: previousEmploymentDetails.financeYear,
      cardNo: previousEmploymentDetails.cardNo,
      dateFrom: previousEmploymentDetails.dateFrom != null ? previousEmploymentDetails.dateFrom.format(DATE_TIME_FORMAT) : null,
      dateTo: previousEmploymentDetails.dateTo != null ? previousEmploymentDetails.dateTo.format(DATE_TIME_FORMAT) : null,
      previousEmployer: previousEmploymentDetails.previousEmployer,
      landLordName: previousEmploymentDetails.landLordName,
      landLordPan: previousEmploymentDetails.landLordPan,
      landLordAddress: previousEmploymentDetails.landLordAddress,
      monthRent: previousEmploymentDetails.monthRent,
      basic: previousEmploymentDetails.basic,
      hra: previousEmploymentDetails.hra,
      cta: previousEmploymentDetails.cta,
      spa: previousEmploymentDetails.spa,
      mda: previousEmploymentDetails.mda,
      others: previousEmploymentDetails.others,
      epf: previousEmploymentDetails.epf,
      vpf: previousEmploymentDetails.vpf,
      tds: previousEmploymentDetails.tds,
      createdBy: previousEmploymentDetails.createdBy,
      createdDate: previousEmploymentDetails.createdDate != null ? previousEmploymentDetails.createdDate.format(DATE_TIME_FORMAT) : null,
      lastUpdatedBy: previousEmploymentDetails.lastUpdatedBy,
      lastUpdatedDate:
        previousEmploymentDetails.lastUpdatedDate != null ? previousEmploymentDetails.lastUpdatedDate.format(DATE_TIME_FORMAT) : null
    });
    if (previousEmploymentDetails && previousEmploymentDetails.tdsYearMaster) {
      this.tdsYearMaster = previousEmploymentDetails.tdsYearMaster;
    } else {
      this.previousEmploymentDetailsService.active().subscribe(tdsYearMaster => {
        this.tdsYearMaster = tdsYearMaster.body;
      });
    }
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const previousEmploymentDetails = this.createFromForm();
    if (previousEmploymentDetails.id !== undefined) {
      this.subscribeToSaveResponse(this.previousEmploymentDetailsService.update(previousEmploymentDetails));
    } else {
      this.subscribeToSaveResponse(this.previousEmploymentDetailsService.create(previousEmploymentDetails));
    }
  }

  private createFromForm(): IPreviousEmploymentDetails {
    return {
      ...new PreviousEmploymentDetails(),
      id: this.editForm.get(['id']).value,
      financeYear:
        this.editForm.get(['financeYear']) && this.editForm.get(['financeYear']).value != null
          ? this.editForm.get(['financeYear']).value
          : 'DUMMY',
      cardNo: this.editForm.get(['cardNo']) && this.editForm.get(['cardNo']).value != null ? this.editForm.get(['cardNo']).value : 'DUMMY',
      dateFrom: this.editForm.get(['dateFrom']).value != null ? moment(this.editForm.get(['dateFrom']).value, DATE_TIME_FORMAT) : undefined,
      dateTo: this.editForm.get(['dateTo']).value != null ? moment(this.editForm.get(['dateTo']).value, DATE_TIME_FORMAT) : undefined,
      previousEmployer: this.editForm.get(['previousEmployer']).value,
      landLordName: this.editForm.get(['landLordName']).value,
      landLordPan: this.editForm.get(['landLordPan']).value,
      landLordAddress: this.editForm.get(['landLordAddress']).value,
      monthRent: this.editForm.get(['monthRent']).value,
      basic: this.editForm.get(['basic']).value,
      hra: this.editForm.get(['hra']).value,
      cta: this.editForm.get(['cta']).value,
      others: this.editForm.get(['others']).value,
      spa: this.editForm.get(['spa']).value,
      mda: this.editForm.get(['mda']).value,
      epf: this.editForm.get(['epf']).value,
      vpf: this.editForm.get(['vpf']).value,
      tds: this.editForm.get(['tds']).value,
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

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IPreviousEmploymentDetails>>) {
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
