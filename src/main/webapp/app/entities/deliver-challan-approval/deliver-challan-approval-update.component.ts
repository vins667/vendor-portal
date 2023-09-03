import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { DeliverChallanApprovalService } from './deliver-challan-approval.service';
import { IDeliveryChallan } from 'app/shared/model/delivery-challan.model';
import { DateTimeAdapter, OWL_DATE_TIME_LOCALE, OWL_DATE_TIME_FORMATS } from 'ng-pick-datetime';
import { MomentDateTimeAdapter } from 'ng-pick-datetime-moment';
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
  selector: 'jhi-deliver-challan-approval-update',
  templateUrl: './deliver-challan-approval-update.component.html',
  encapsulation: ViewEncapsulation.None,
  providers: [
    // `MomentDateTimeAdapter` and `OWL_MOMENT_DATE_TIME_FORMATS` can be automatically provided by importing
    // `OwlMomentDateTimeModule` in your applications root module. We provide it at the component level
    // here, due to limitations of our example generation script.
    { provide: DateTimeAdapter, useClass: MomentDateTimeAdapter, deps: [OWL_DATE_TIME_LOCALE] },
    { provide: OWL_DATE_TIME_FORMATS, useValue: MY_MOMENT_FORMATS }
  ]
})
export class DeliverChallanApprovalUpdateComponent implements OnInit {
  deliveryChallan: IDeliveryChallan;
  isSaving: boolean;
  challanDate: any;
  eWayBillDate: any;
  expReturnDate: any;
  acReturnDate: any;
  checkbox1: string;
  checkbox2: string;
  constructor(protected deliverChallanApprovalService: DeliverChallanApprovalService, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ deliveryChallan }) => {
      this.deliveryChallan = deliveryChallan;
      if (this.deliveryChallan && this.deliveryChallan.factCode !== undefined) {
        this.challanDate = this.deliveryChallan.challanDate != null ? moment(this.deliveryChallan.challanDate) : undefined;
        this.eWayBillDate = this.deliveryChallan.eWayBillDate != null ? moment(this.deliveryChallan.eWayBillDate) : undefined;
        this.expReturnDate = this.deliveryChallan.expReturnDate != null ? moment(this.deliveryChallan.expReturnDate) : undefined;
        this.acReturnDate = this.deliveryChallan.acReturnDate != null ? moment(this.deliveryChallan.acReturnDate) : undefined;
        if (this.deliveryChallan.challanType === 'R') {
          this.checkbox1 = 'R';
        } else {
          this.checkbox2 = 'N';
        }
      }
    });
  }

  previousState() {
    window.history.back();
  }

  saveApproved() {
    this.isSaving = true;
    if (this.deliveryChallan.id !== undefined) {
      this.deliveryChallan.flag = 'A';
      this.subscribeToSaveResponse(this.deliverChallanApprovalService.update(this.deliveryChallan));
    }
  }

  saveReject() {
    this.isSaving = true;
    if (this.deliveryChallan.id !== undefined) {
      this.deliveryChallan.flag = 'R';
      this.subscribeToSaveResponse(this.deliverChallanApprovalService.update(this.deliveryChallan));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IDeliveryChallan>>) {
    result.subscribe((res: HttpResponse<IDeliveryChallan>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
}
