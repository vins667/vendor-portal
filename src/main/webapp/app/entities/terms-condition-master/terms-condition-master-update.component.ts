import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { ITermsConditionMaster, TermsConditionMaster } from 'app/shared/model/terms-condition-master.model';
import { TermsConditionMasterService } from './terms-condition-master.service';
import { IReportTypeMaster } from 'app/shared/model/report-type-master.model';
import { ReportTypeMasterService } from 'app/entities/report-type-master';
import { TermsConditionDetails } from 'app/shared/model/terms-condition-details.model';
import { DateTimeAdapter, OWL_DATE_TIME_LOCALE, OWL_DATE_TIME_FORMATS } from 'ng-pick-datetime';
import { DATE_TIME_FORMAT, DATE_FORMAT } from 'app/shared/constants/input.constants';
import { MomentDateTimeAdapter } from 'ng-pick-datetime-moment';
import { SnotifyPosition, SnotifyService } from 'ng-snotify';
import * as moment from 'moment';

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
  selector: 'jhi-terms-condition-master-update',
  templateUrl: './terms-condition-master-update.component.html',
  encapsulation: ViewEncapsulation.None,
  providers: [
    // `MomentDateTimeAdapter` and `OWL_MOMENT_DATE_TIME_FORMATS` can be automatically provided by importing
    // `OwlMomentDateTimeModule` in your applications root module. We provide it at the component level
    // here, due to limitations of our example generation script.
    { provide: DateTimeAdapter, useClass: MomentDateTimeAdapter, deps: [OWL_DATE_TIME_LOCALE] },
    { provide: OWL_DATE_TIME_FORMATS, useValue: MY_MOMENT_FORMATS }
  ]
})
export class TermsConditionMasterUpdateComponent implements OnInit {
  isSaving: boolean;
  reporttypemasters: IReportTypeMaster[];
  termsConditionMaster: ITermsConditionMaster;
  applicableDateDp: any;
  closedDateDp: any;
  constructor(
    protected jhiAlertService: JhiAlertService,
    protected termsConditionMasterService: TermsConditionMasterService,
    protected reportTypeMasterService: ReportTypeMasterService,
    protected activatedRoute: ActivatedRoute,
    protected snotifyService: SnotifyService
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ termsConditionMaster }) => {
      this.termsConditionMaster = termsConditionMaster;
      if (this.termsConditionMaster && this.termsConditionMaster.id) {
        this.applicableDateDp =
          this.termsConditionMaster.applicableDate != null ? moment(this.termsConditionMaster.applicableDate) : undefined;
        this.closedDateDp = this.termsConditionMaster.closedDate != null ? moment(this.termsConditionMaster.closedDate) : undefined;
      } else {
        this.applicableDateDp = new Date();
        this.applicableDateDp.setHours(0, 0, 0, 0);
        this.closedDateDp = new Date();
        this.closedDateDp.setHours(0, 0, 0, 0);
        this.termsConditionMaster = new TermsConditionMaster();
        this.getMultipleTermsLine();
      }
    });
    this.reportTypeMasterService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IReportTypeMaster[]>) => mayBeOk.ok),
        map((response: HttpResponse<IReportTypeMaster[]>) => response.body)
      )
      .subscribe((res: IReportTypeMaster[]) => (this.reporttypemasters = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const applicableDt = this.applicableDateDp != null ? moment(this.applicableDateDp, DATE_TIME_FORMAT).startOf('day') : null;
    const closedDt = this.closedDateDp != null ? moment(this.closedDateDp, DATE_TIME_FORMAT).startOf('day') : null;
    this.termsConditionMaster.applicableDate = applicableDt != null && applicableDt.isValid() ? applicableDt.format(DATE_FORMAT) : null;
    this.termsConditionMaster.closedDate = closedDt != null && closedDt.isValid() ? closedDt.format(DATE_FORMAT) : null;
    if (this.termsConditionMaster.id !== null && this.termsConditionMaster.id !== undefined) {
      this.subscribeToSaveResponse(this.termsConditionMasterService.update(this.termsConditionMaster));
    } else {
      this.subscribeToSaveResponse(this.termsConditionMasterService.create(this.termsConditionMaster));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITermsConditionMaster>>) {
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

  trackReportTypeMasterById(index: number, item: IReportTypeMaster) {
    return item.id;
  }

  getMultipleTermsLine() {
    this.termsConditionMaster.termsConditionDetails = new Array<TermsConditionDetails>();
    for (let i = 0; i < 5; i++) {
      this.termsConditionMaster.termsConditionDetails.push(new TermsConditionDetails());
    }
  }

  addRow() {
    this.termsConditionMaster.termsConditionDetails.push(new TermsConditionDetails());
  }

  removeRow(index: any) {
    if (this.termsConditionMaster.termsConditionDetails[index].id !== undefined) {
      this.deleteRow(this.termsConditionMaster.termsConditionDetails[index].id, index);
    } else {
      this.termsConditionMaster.termsConditionDetails.splice(index, 1);
    }
  }

  deleteRow(id, index) {
    this.snotifyService.confirm('Are you sure to delete row?', 'Confirm', {
      timeout: 25000,
      showProgressBar: false,
      closeOnClick: false,
      pauseOnHover: true,
      position: SnotifyPosition.centerTop,
      buttons: [
        { text: 'Yes', action: toast => this.delete(toast, id, index), bold: false },
        { text: 'No', action: toast => this.snotifyService.remove(toast.id) }
      ]
    });
  }
  delete(toast, id, index) {
    this.termsConditionMasterService.deleteDetail(id).subscribe(any => {
      this.snotifyService.remove(toast.id);
      this.termsConditionMaster.termsConditionDetails.splice(index, 1);
    });
  }
}
