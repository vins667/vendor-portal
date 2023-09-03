import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { IDeliveryChallan } from 'app/shared/model/delivery-challan.model';
import { DeliveryChallanService } from './delivery-challan.service';
import { DateTimeAdapter, OWL_DATE_TIME_LOCALE, OWL_DATE_TIME_FORMATS } from 'ng-pick-datetime';
import { DATE_TIME_FORMAT, DATE_FORMAT } from 'app/shared/constants/input.constants';
import { MomentDateTimeAdapter } from 'ng-pick-datetime-moment';
import { SnotifyService, SnotifyPosition } from 'ng-snotify';
import { DeliveryChallanDetails } from 'app/shared/model/delivery-challan-details.model';
import { NgbModalRef, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';
import { DeliveryChallanFactoryPopupComponent } from './delivery-challan-factory-popup.component';
import { DeliveryChallanBussinessPopupComponent } from './delivery-challan-bussiness-popup.component';
import { DeliveryChallanTariffPopupComponent } from './delivery-challan-tariff-popup.component';
import { toastConfig } from 'app/core/toast/toast-config';

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
  selector: 'jhi-delivery-challan-update',
  templateUrl: './delivery-challan-update.component.html',
  encapsulation: ViewEncapsulation.None,
  providers: [
    // `MomentDateTimeAdapter` and `OWL_MOMENT_DATE_TIME_FORMATS` can be automatically provided by importing
    // `OwlMomentDateTimeModule` in your applications root module. We provide it at the component level
    // here, due to limitations of our example generation script.
    { provide: DateTimeAdapter, useClass: MomentDateTimeAdapter, deps: [OWL_DATE_TIME_LOCALE] },
    { provide: OWL_DATE_TIME_FORMATS, useValue: MY_MOMENT_FORMATS }
  ]
})
export class DeliveryChallanUpdateComponent implements OnInit {
  deliveryChallan: IDeliveryChallan;
  isSaving: boolean;
  challanDate: any;
  eWayBillDate: any;
  expReturnDate: any;
  acReturnDate: any;
  protected ngbModalRef: NgbModalRef;
  isState: string;
  taxper: number;
  checkbox1: string;
  checkbox2: string;
  constructor(
    protected deliveryChallanService: DeliveryChallanService,
    protected activatedRoute: ActivatedRoute,
    protected snotifyService: SnotifyService,
    protected modalService: NgbModal,
    protected eventManager: JhiEventManager
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.registerChangeInDeliveryChallans();
    this.registerChangeInDeliveryChallans2();
    this.registerChangeInDeliveryChallansTax();
    this.activatedRoute.data.subscribe(({ deliveryChallan }) => {
      this.deliveryChallan = deliveryChallan;
      if (this.deliveryChallan && this.deliveryChallan.eWayBillNo !== undefined) {
        this.challanDate = this.deliveryChallan.challanDate != null ? moment(this.deliveryChallan.challanDate) : undefined;
        this.eWayBillDate = this.deliveryChallan.eWayBillDate != null ? moment(this.deliveryChallan.eWayBillDate) : undefined;
        this.expReturnDate = this.deliveryChallan.expReturnDate != null ? moment(this.deliveryChallan.expReturnDate) : undefined;
        this.acReturnDate = this.deliveryChallan.acReturnDate != null ? moment(this.deliveryChallan.acReturnDate) : undefined;
        if (this.deliveryChallan.challanType === 'R') {
          this.checkbox1 = 'R';
        } else {
          this.checkbox2 = 'N';
        }
      } else {
        this.challanDate = new Date();
        this.challanDate.setHours(0, 0, 0, 0);
        this.getMultipleTermsLine();
        this.deliveryChallan.challanType = 'R';
        this.checkbox1 = 'R';
      }
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    if (this.getValidation()) {
      this.isSaving = true;
      const challanDt = this.challanDate != null ? moment(this.challanDate, DATE_TIME_FORMAT).startOf('day') : null;
      const eWayBillDt = this.eWayBillDate != null ? moment(this.eWayBillDate, DATE_TIME_FORMAT).startOf('day') : null;
      const expReturnDt = this.expReturnDate != null ? moment(this.expReturnDate, DATE_TIME_FORMAT).startOf('day') : null;
      const acReturnDt = this.acReturnDate != null ? moment(this.acReturnDate, DATE_TIME_FORMAT).startOf('day') : null;
      this.deliveryChallan.challanDate = challanDt != null && challanDt.isValid() ? challanDt.format(DATE_FORMAT) : null;
      this.deliveryChallan.eWayBillDate = eWayBillDt != null && eWayBillDt.isValid() ? eWayBillDt.format(DATE_FORMAT) : null;
      this.deliveryChallan.expReturnDate = expReturnDt != null && expReturnDt.isValid() ? expReturnDt.format(DATE_FORMAT) : null;
      this.deliveryChallan.acReturnDate = acReturnDt != null && acReturnDt.isValid() ? acReturnDt.format(DATE_FORMAT) : null;
      if (this.deliveryChallan.id !== undefined) {
        this.subscribeToSaveResponse(this.deliveryChallanService.update(this.deliveryChallan));
      } else {
        this.subscribeToSaveResponse(this.deliveryChallanService.create(this.deliveryChallan));
      }
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

  getFactory() {
    this.ngbModalRef = this.modalService.open(DeliveryChallanFactoryPopupComponent as Component, {
      size: 'lg',
      backdrop: 'static',
      windowClass: 'mediumModal'
    });
  }

  getBussinessPartner() {
    this.ngbModalRef = this.modalService.open(DeliveryChallanBussinessPopupComponent as Component, {
      size: 'lg',
      backdrop: 'static',
      windowClass: 'mediumModal'
    });
  }

  getTariff(index: number) {
    this.ngbModalRef = this.modalService.open(DeliveryChallanTariffPopupComponent as Component, {
      size: 'lg',
      backdrop: 'static',
      windowClass: 'mediumModal'
    });
    this.ngbModalRef.componentInstance.index = index;
    if (this.deliveryChallan.stateCode === this.deliveryChallan.bStateCode) {
      this.ngbModalRef.componentInstance.isState = 'Y';
    } else {
      this.ngbModalRef.componentInstance.isState = 'N';
    }
  }

  getMultipleTermsLine() {
    this.deliveryChallan.deliveryChallanDetails = new Array<DeliveryChallanDetails>();
    for (let i = 0; i < 2; i++) {
      this.deliveryChallan.deliveryChallanDetails.push(new DeliveryChallanDetails());
    }
  }

  addRow() {
    this.deliveryChallan.deliveryChallanDetails.push(new DeliveryChallanDetails());
  }

  removeRow(index: any) {
    if (this.deliveryChallan.deliveryChallanDetails[index].id !== undefined) {
      this.deleteRow(this.deliveryChallan.deliveryChallanDetails[index].id, index);
    } else {
      this.deliveryChallan.deliveryChallanDetails.splice(index, 1);
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
    this.deliveryChallanService.deleteDetail(id).subscribe(any => {
      this.snotifyService.remove(toast.id);
      this.deliveryChallan.deliveryChallanDetails.splice(index, 1);
    });
  }
  registerChangeInDeliveryChallans() {
    this.eventManager.subscribe('deliveryChallan', message => {
      this.deliveryChallan.factCode = message.content.factCode;
      this.deliveryChallan.showAddress1 = message.content.showAddress1;
      this.deliveryChallan.factDescription = message.content.factDescription;
      this.deliveryChallan.fAddressLine1 = message.content.fAddressLine1;
      this.deliveryChallan.fAddressLine2 = message.content.fAddressLine2;
      this.deliveryChallan.fAddressLine3 = message.content.fAddressLine3;
      this.deliveryChallan.fAddressLine4 = message.content.fAddressLine4;
      this.deliveryChallan.fAddressLine5 = message.content.fAddressLine5;
      this.deliveryChallan.postalCode = message.content.postalCode;
      this.deliveryChallan.town = message.content.bTown;
      this.deliveryChallan.district = message.content.district;
      this.deliveryChallan.stateCode = message.content.stateCode;
      this.deliveryChallan.fGSTNumber = message.content.fGSTNumber;
    });
  }

  registerChangeInDeliveryChallans2() {
    this.eventManager.subscribe('deliveryChallanTemp', message => {
      this.deliveryChallan.suppliercode = message.content.suppliercode;
      this.deliveryChallan.bLegalname1 = message.content.bLegalname1;
      this.deliveryChallan.showAddress2 = message.content.showAddress2;
      this.deliveryChallan.bAddressLine1 = message.content.bAddressLine1;
      this.deliveryChallan.bAddressLine2 = message.content.bAddressLine2;
      this.deliveryChallan.bAddressLine3 = message.content.bAddressLine3;
      this.deliveryChallan.bAddressLine4 = message.content.bAddressLine4;
      this.deliveryChallan.bAddressLine5 = message.content.bAddressLine5;
      this.deliveryChallan.bPostalCode = message.content.bPostalCode;
      this.deliveryChallan.bTown = message.content.bTown;
      this.deliveryChallan.bDistrict = message.content.bDistrict;
      this.deliveryChallan.bStateCode = message.content.bStateCode;
      this.deliveryChallan.bGSTNumber = message.content.bGSTNumber;
    });
  }

  registerChangeInDeliveryChallansTax() {
    this.eventManager.subscribe('taxper', message => {
      this.deliveryChallan.deliveryChallanDetails[message.content.index].triffCode = message.content.triffCode;
      this.deliveryChallan.deliveryChallanDetails[message.content.index].taxper = message.content.taxper;
    });
  }

  getAmount(deliveryChallanDetail, index) {
    if (deliveryChallanDetail.quantity !== undefined && deliveryChallanDetail.rate !== undefined) {
      this.deliveryChallan.deliveryChallanDetails[index].amount = deliveryChallanDetail.quantity * deliveryChallanDetail.rate;
    }
    if (
      deliveryChallanDetail.triffCode !== undefined &&
      (deliveryChallanDetail.quantity !== undefined && deliveryChallanDetail.rate !== undefined)
    ) {
      const tempAmt = deliveryChallanDetail.quantity * deliveryChallanDetail.rate;
      const tempVal2 = tempAmt * deliveryChallanDetail.taxper;
      const temptaxval = tempVal2 / 100;
      this.deliveryChallan.deliveryChallanDetails[index].taxval = temptaxval;
      this.deliveryChallan.deliveryChallanDetails[index].totalAmt = tempAmt + temptaxval;
    }
  }

  checkBoxChange1(e) {
    if (e.target.checked) {
      this.checkbox1 = 'R';
      this.deliveryChallan.challanType = 'R';
      this.checkbox2 = null;
    } else {
      this.checkbox1 = null;
      this.deliveryChallan.challanType = null;
    }
  }

  checkBoxChange2(e) {
    if (e.target.checked) {
      this.checkbox2 = 'R';
      this.deliveryChallan.challanType = 'N';
      this.checkbox1 = null;
    } else {
      this.checkbox2 = null;
      this.deliveryChallan.challanType = null;
    }
  }

  getValidation() {
    let keepGoing = true;
    if (this.deliveryChallan.challanType === undefined || this.deliveryChallan.challanType === null) {
      keepGoing = false;
      this.snotifyService.error('Choose Challan Type', '', toastConfig);
    }
    return keepGoing;
  }
}
