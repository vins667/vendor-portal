import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as _moment from 'moment';
import { SnotifyService, SnotifyPosition } from 'ng-snotify';
import { TravelApplicationMasterHodService } from './travel-application-master-hod.service';
import { DateTimeAdapter, OWL_DATE_TIME_LOCALE, OWL_DATE_TIME_FORMATS } from 'ng-pick-datetime';
import { MomentDateTimeAdapter } from 'ng-pick-datetime-moment';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';
import { ITravelCurrencyMaster, TravelCurrencyMaster } from 'app/shared/model/travel-currency-master.model';
import { TravelCurrencyMasterService } from '../travel-currency-master/travel-currency-master.service';
import { IEmployeeView } from 'app/shared/model/employee-view.model';
import { UserService } from 'app/core/user/user.service';
import { EmployeeViewService } from '../employee-view';
import { IUser } from 'app/core/user/user.model';
import { ITravelApplicationMaster } from 'app/shared/model/travel-application-master.model';
import { TravelFlightDetails } from 'app/shared/model/travel-flight-details.model';
import { TravelAccommodationDetails } from 'app/shared/model/travel-accommodation-details.model';
import { TravelLuggageDetails } from 'app/shared/model/travel-luggage-details.model';
import { TravelForexDetails } from 'app/shared/model/travel-forex-details.model';
import { toastConfig } from 'app/core/toast/toast-config';
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
  selector: 'jhi-travel-application-master-update-hod',
  templateUrl: './travel-application-master-update-hod.component.html',
  providers: [
    { provide: DateTimeAdapter, useClass: MomentDateTimeAdapter, deps: [OWL_DATE_TIME_LOCALE] },
    { provide: OWL_DATE_TIME_FORMATS, useValue: MY_MOMENT_FORMATS }
  ]
})
export class TravelApplicationMasterUpdateHodComponent implements OnInit {
  isSaving: boolean;
  travelApplicationMasterHod: ITravelApplicationMaster;
  travelCurrencyMaster: ITravelCurrencyMaster[];
  fromDate: any;
  toDate: any;
  empDetail: [];
  travelDate: any;
  accommodationDate: any;
  departureDate: any;
  arrivalDate: any;
  hodEmployeeView: IEmployeeView;
  employeeView: IEmployeeView;

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected travelApplicationMasterHodService: TravelApplicationMasterHodService,
    protected travelCurrencyMasterService: TravelCurrencyMasterService,
    protected activatedRoute: ActivatedRoute,
    protected snotifyService: SnotifyService,
    protected eventManager: JhiEventManager,
    protected userService: UserService,
    protected employeeViewService: EmployeeViewService,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.loadCurrencyList();
    this.activatedRoute.data.subscribe(({ travelApplicationMasterHod }) => {
      this.travelApplicationMasterHod = travelApplicationMasterHod;
      if (this.travelApplicationMasterHod && this.travelApplicationMasterHod.hodCode) {
        this.employeeViewService.find(this.travelApplicationMasterHod.hodCode).subscribe((res1: HttpResponse<IEmployeeView>) => {
          this.hodEmployeeView = res1.body;
        });
      }
    });
    this.isSaving = false;
  }

  loadFlightDetail() {
    this.travelApplicationMasterHod.travelFlightDetails = new Array<TravelFlightDetails>();
    for (let i = 0; i < 1; i++) {
      this.travelApplicationMasterHod.travelFlightDetails.push(new TravelFlightDetails());
    }
  }

  loadAccomdDetail() {
    this.travelApplicationMasterHod.travelAccommodationDetails = new Array<TravelAccommodationDetails>();
    for (let i = 0; i < 1; i++) {
      this.travelApplicationMasterHod.travelAccommodationDetails.push(new TravelAccommodationDetails());
    }
  }

  loadLuggageDetail() {
    this.travelApplicationMasterHod.travelLuggageDetails = new Array<TravelLuggageDetails>();
    for (let i = 0; i < 2; i++) {
      this.travelApplicationMasterHod.travelLuggageDetails.push(new TravelLuggageDetails());
    }
  }

  loadForexDetail() {
    this.travelApplicationMasterHod.travelForexDetails = new Array<TravelForexDetails>();
    for (let i = 0; i < 1; i++) {
      this.travelApplicationMasterHod.travelForexDetails.push(new TravelForexDetails());
    }
  }

  loadCurrencyList() {
    this.travelCurrencyMasterService.query().subscribe((res: HttpResponse<ITravelCurrencyMaster[]>) => {
      this.travelCurrencyMaster = res.body;
    });
  }

  addRowFlight() {
    if (this.travelApplicationMasterHod.travelFlightDetails) {
      this.travelApplicationMasterHod.travelFlightDetails.push(new TravelFlightDetails());
    } else {
      this.travelApplicationMasterHod.travelFlightDetails = [];
      this.travelApplicationMasterHod.travelFlightDetails.push(new TravelFlightDetails());
    }
  }

  addRowHotel() {
    if (this.travelApplicationMasterHod.travelAccommodationDetails) {
      this.travelApplicationMasterHod.travelAccommodationDetails.push(new TravelAccommodationDetails());
    } else {
      this.travelApplicationMasterHod.travelAccommodationDetails = [];
      this.travelApplicationMasterHod.travelAccommodationDetails.push(new TravelAccommodationDetails());
    }
  }

  addRowForex() {
    if (this.travelApplicationMasterHod.travelForexDetails) {
      this.travelApplicationMasterHod.travelForexDetails.push(new TravelForexDetails());
    } else {
      this.travelApplicationMasterHod.travelForexDetails = [];
      this.travelApplicationMasterHod.travelForexDetails.push(new TravelForexDetails());
    }
  }

  removeRowFlight(index: any) {
    if (this.travelApplicationMasterHod.travelFlightDetails[index].id !== undefined) {
      this.deleteRow(this.travelApplicationMasterHod.travelFlightDetails[index].id, index);
    } else {
      this.travelApplicationMasterHod.travelFlightDetails.splice(index, 1);
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
    this.travelApplicationMasterHodService.deleteDetailRow(id).subscribe(any => {
      this.snotifyService.remove(toast.id);
      this.travelApplicationMasterHod.travelFlightDetails.splice(index, 1);
    });
  }

  removeRowHotel(index1: any) {
    console.log('index1 : ', this.travelApplicationMasterHod.travelAccommodationDetails[index1].id);
    if (this.travelApplicationMasterHod.travelAccommodationDetails[index1].id !== undefined) {
      this.deleteRowHotel(this.travelApplicationMasterHod.travelAccommodationDetails[index1].id, index1);
    } else {
      this.travelApplicationMasterHod.travelAccommodationDetails.splice(index1, 1);
    }
  }

  deleteRowHotel(id, index) {
    this.snotifyService.confirm('Are you sure to delete row?', 'Confirm', {
      timeout: 25000,
      showProgressBar: false,
      closeOnClick: false,
      pauseOnHover: true,
      position: SnotifyPosition.centerTop,
      buttons: [
        { text: 'Yes', action: toast => this.delete2(toast, id, index), bold: false },
        { text: 'No', action: toast => this.snotifyService.remove(toast.id) }
      ]
    });
  }

  delete2(toast, id, index) {
    this.travelApplicationMasterHodService.deleteHotelDtlRow(id).subscribe(any => {
      this.snotifyService.remove(toast.id);
      this.travelApplicationMasterHod.travelFlightDetails.splice(index, 1);
    });
  }

  removeRowForex(index: any) {
    if (this.travelApplicationMasterHod.travelForexDetails[index].id !== undefined) {
      this.deleteRowFx(this.travelApplicationMasterHod.travelForexDetails[index].id, index);
    } else {
      this.travelApplicationMasterHod.travelForexDetails.splice(index, 1);
    }
  }

  deleteRowFx(id, index) {
    this.snotifyService.confirm('Are you sure to delete row?', 'Confirm', {
      timeout: 25000,
      showProgressBar: false,
      closeOnClick: false,
      pauseOnHover: true,
      position: SnotifyPosition.centerTop,
      buttons: [
        { text: 'Yes', action: toast => this.deleteFx(toast, id, index), bold: false },
        { text: 'No', action: toast => this.snotifyService.remove(toast.id) }
      ]
    });
  }

  deleteFx(toast, id, index) {
    this.travelApplicationMasterHodService.deleteDetailRowFx(id).subscribe(any => {
      this.snotifyService.remove(toast.id);
      this.travelApplicationMasterHod.travelForexDetails.splice(index, 1);
    });
  }

  previousState() {
    window.history.back();
  }

  method01() {
    this.travelApplicationMasterHod.travelDays = moment(this.travelApplicationMasterHod.toDate).diff(
      moment(this.travelApplicationMasterHod.fromDate),
      'days'
    );
  }

  save(flag: string, toast: any) {
    this.isSaving = true;
    if (this.travelApplicationMasterHod.id !== undefined) {
      this.travelApplicationMasterHodService.approve(this.travelApplicationMasterHod.id, flag).subscribe(
        value => {
          this.snotifyService.remove(toast.id);
          if (flag === 'A') {
            this.snotifyService.success('Approved Successfully!!!', '', toastConfig);
          } else {
            this.snotifyService.success('Rejected Successfully!!!', '', toastConfig);
          }
          this.previousState();
        },
        error => {
          this.snotifyService.remove(toast.id);
          this.snotifyService.error('Record not save', '', toastConfig);
        }
      );
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITravelApplicationMaster>>) {
    result.subscribe(() => this.onSaveSuccess(), () => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }

  saveApproved() {
    this.snotifyService.confirm('Are you sure to Approve?', 'Confirm', {
      timeout: 2500,
      showProgressBar: false,
      closeOnClick: false,
      pauseOnHover: true,
      position: SnotifyPosition.centerTop,
      buttons: [
        { text: 'Yes', action: toast => this.save('A', toast), bold: false },
        { text: 'No', action: toast => this.snotifyService.remove(toast.id) }
      ]
    });
  }

  saveReject() {
    this.snotifyService.confirm('Are you sure to Reject?', 'Confirm', {
      timeout: 2500,
      showProgressBar: false,
      closeOnClick: false,
      pauseOnHover: true,
      position: SnotifyPosition.centerTop,
      buttons: [
        { text: 'Yes', action: toast => this.save('R', toast), bold: false },
        { text: 'No', action: toast => this.snotifyService.remove(toast.id) }
      ]
    });
  }
}
