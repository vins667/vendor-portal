import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as _moment from 'moment';
import { ITravelApplicationMaster, TravelApplicationMaster } from 'app/shared/model/travel-application-master.model';
import { TravelApplicationMasterService } from './travel-application-master.service';
import { SnotifyService, SnotifyPosition } from 'ng-snotify';
import { TravelFlightDetails } from 'app/shared/model/travel-flight-details.model';
import { TravelAccommodationDetails } from 'app/shared/model/travel-accommodation-details.model';
import { DateTimeAdapter, OWL_DATE_TIME_LOCALE, OWL_DATE_TIME_FORMATS } from 'ng-pick-datetime';
import { MomentDateTimeAdapter } from 'ng-pick-datetime-moment';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';
import { TravelLuggageDetails } from 'app/shared/model/travel-luggage-details.model';
import { TravelForexDetails } from 'app/shared/model/travel-forex-details.model';
import { ITravelCurrencyMaster, TravelCurrencyMaster } from 'app/shared/model/travel-currency-master.model';
import { TravelCurrencyMasterService } from '../travel-currency-master/travel-currency-master.service';
import { IEmployeeView, EmployeeView } from 'app/shared/model/employee-view.model';
import { UserService } from 'app/core/user/user.service';
import { EmployeeViewService } from '../employee-view';
import { IUser } from 'app/core/user/user.model';
import { TravelPassengerDetails } from 'app/shared/model/travel-passenger-details.model';
import { NgbModalRef, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { TravelMasterAttachComponent } from './travel-master-attach.component';

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
  selector: 'jhi-travel-application-master-update',
  templateUrl: './travel-application-master-update.component.html',
  providers: [
    { provide: DateTimeAdapter, useClass: MomentDateTimeAdapter, deps: [OWL_DATE_TIME_LOCALE] },
    { provide: OWL_DATE_TIME_FORMATS, useValue: MY_MOMENT_FORMATS }
  ]
})
export class TravelApplicationMasterUpdateComponent implements OnInit, OnDestroy {
  isSaving: boolean;
  travelApplicationMaster: ITravelApplicationMaster;
  travelCurrencyMaster: ITravelCurrencyMaster[];
  travelFromdate: any;
  travelTodate: any;
  travelDate: any;
  accommodationDate: any;
  departureDate: any;
  arrivalDate: any;
  hodEmployeeView: IEmployeeView;
  employeeView: IEmployeeView;
  protected ngbModalRef: NgbModalRef;
  constructor(
    protected jhiAlertService: JhiAlertService,
    protected travelApplicationMasterService: TravelApplicationMasterService,
    protected travelCurrencyMasterService: TravelCurrencyMasterService,
    protected activatedRoute: ActivatedRoute,
    protected snotifyService: SnotifyService,
    protected eventManager: JhiEventManager,
    protected userService: UserService,
    protected employeeViewService: EmployeeViewService,
    private fb: FormBuilder,
    protected modalService: NgbModal
  ) {}

  ngOnInit() {
    this.loadDate();
    this.LoadData();
    this.loadCurrencyList();
    this.activatedRoute.data.subscribe(({ travelApplicationMaster }) => {
      this.travelApplicationMaster = travelApplicationMaster;
      if (this.travelApplicationMaster !== null && this.travelApplicationMaster.id !== undefined) {
      } else {
        this.loadFlightDetail();
      }
      if (
        this.travelApplicationMaster !== null &&
        this.travelApplicationMaster.id !== undefined &&
        this.travelApplicationMaster.travelAccommodationDetails.length > 0
      ) {
      } else {
        this.loadAccomdDetail();
      }
      if (
        this.travelApplicationMaster !== null &&
        this.travelApplicationMaster.id !== undefined &&
        this.travelApplicationMaster.travelLuggageDetails.length > 0
      ) {
      } else {
        this.loadLuggageDetail();
      }
      if (
        this.travelApplicationMaster !== null &&
        this.travelApplicationMaster.id !== undefined &&
        this.travelApplicationMaster.travelForexDetails.length > 0
      ) {
      } else {
        this.loadForexDetail();
      }
    });
    this.isSaving = false;
  }

  loadDate() {
    const FirstDay = new Date();
    const LastDay = new Date();
    const traveldt = new Date();
    const accomdt = new Date();
    const deptDate = new Date();
    const arrvDate = new Date();
    this.travelFromdate = FirstDay;
    this.travelFromdate.setHours(0, 0, 0, 0);
    this.travelTodate = LastDay;
    this.travelTodate.setHours(0, 0, 0, 0);
    this.travelDate = traveldt;
    this.travelDate.setHours(0, 0, 0, 0);
    this.accommodationDate = accomdt;
    this.accommodationDate.setHours(0, 0, 0, 0);
    this.departureDate = traveldt;
    this.departureDate.setHours(0, 0, 0, 0);
    this.arrivalDate = accomdt;
    this.arrivalDate.setHours(0, 0, 0, 0);
  }

  LoadData() {
    this.userService.currentuser().subscribe((res: HttpResponse<IUser>) => {
      this.employeeViewService.find(res.body.login).subscribe((res1: HttpResponse<IEmployeeView>) => {
        this.employeeView = res1.body;
        if (this.travelApplicationMaster.travelPassengerDetails === undefined) {
          this.travelApplicationMaster.travelPassengerDetails = [];
          const travelPassDetail = new TravelPassengerDetails();
          travelPassDetail.passengerName = this.employeeView.name;
          travelPassDetail.phoneNo = this.employeeView.phone;
          travelPassDetail.emailId = this.employeeView.email;
          this.travelApplicationMaster.travelPassengerDetails.push(travelPassDetail);
        }
        if (this.employeeView.supervisor !== undefined && this.employeeView.supervisor.includes('(') === true) {
          const hodcardNo = this.employeeView.supervisor.substring(0, this.employeeView.supervisor.indexOf('('));
          this.employeeViewService.findByCard(hodcardNo).subscribe(hodEmployeeView => {
            this.hodEmployeeView = hodEmployeeView.body;
          });
          this.travelApplicationMaster.hodCode = hodcardNo;
          this.travelApplicationMaster.empCode = this.employeeView.name;
        }
      });
    });
  }

  loadFlightDetail() {
    this.travelApplicationMaster.travelFlightDetails = new Array<TravelFlightDetails>();
    for (let i = 0; i < 1; i++) {
      this.travelApplicationMaster.travelFlightDetails.push(new TravelFlightDetails());
    }
  }

  loadAccomdDetail() {
    this.travelApplicationMaster.travelAccommodationDetails = new Array<TravelAccommodationDetails>();
    for (let i = 0; i < 1; i++) {
      this.travelApplicationMaster.travelAccommodationDetails.push(new TravelAccommodationDetails());
    }
  }

  loadLuggageDetail() {
    this.travelApplicationMaster.travelLuggageDetails = new Array<TravelLuggageDetails>();
    for (let i = 0; i < 2; i++) {
      this.travelApplicationMaster.travelLuggageDetails.push(new TravelLuggageDetails());
    }
  }

  loadForexDetail() {
    this.travelApplicationMaster.travelForexDetails = new Array<TravelForexDetails>();
    for (let i = 0; i < 1; i++) {
      this.travelApplicationMaster.travelForexDetails.push(new TravelForexDetails());
    }
  }

  loadCurrencyList() {
    this.travelCurrencyMasterService.query().subscribe((res: HttpResponse<ITravelCurrencyMaster[]>) => {
      this.travelCurrencyMaster = res.body;
    });
  }

  addRowFlight() {
    if (this.travelApplicationMaster.travelFlightDetails) {
      this.travelApplicationMaster.travelFlightDetails.push(new TravelFlightDetails());
    } else {
      this.travelApplicationMaster.travelFlightDetails = [];
      this.travelApplicationMaster.travelFlightDetails.push(new TravelFlightDetails());
    }
  }

  addRowHotel() {
    if (this.travelApplicationMaster.travelAccommodationDetails) {
      this.travelApplicationMaster.travelAccommodationDetails.push(new TravelAccommodationDetails());
    } else {
      this.travelApplicationMaster.travelAccommodationDetails = [];
      this.travelApplicationMaster.travelAccommodationDetails.push(new TravelAccommodationDetails());
    }
  }

  addRowForex() {
    if (this.travelApplicationMaster.travelForexDetails) {
      this.travelApplicationMaster.travelForexDetails.push(new TravelForexDetails());
    } else {
      this.travelApplicationMaster.travelForexDetails = [];
      this.travelApplicationMaster.travelForexDetails.push(new TravelForexDetails());
    }
  }

  addRowPassenger() {
    if (this.travelApplicationMaster.travelPassengerDetails) {
      this.travelApplicationMaster.travelPassengerDetails.push(new TravelPassengerDetails());
    } else {
      this.travelApplicationMaster.travelPassengerDetails = [];
      this.travelApplicationMaster.travelPassengerDetails.push(new TravelPassengerDetails());
    }
  }

  removeRowFlight(index: any) {
    if (this.travelApplicationMaster.travelFlightDetails[index].id !== undefined) {
      this.deleteRow(this.travelApplicationMaster.travelFlightDetails[index].id, index);
    } else {
      this.travelApplicationMaster.travelFlightDetails.splice(index, 1);
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
    this.travelApplicationMasterService.deleteDetailRow(id).subscribe(any => {
      this.snotifyService.remove(toast.id);
      this.travelApplicationMaster.travelFlightDetails.splice(index, 1);
    });
  }

  removeRowHotel(index1: any) {
    if (this.travelApplicationMaster.travelAccommodationDetails[index1].id !== undefined) {
      this.deleteRowHotel(this.travelApplicationMaster.travelAccommodationDetails[index1].id, index1);
    } else {
      this.travelApplicationMaster.travelAccommodationDetails.splice(index1, 1);
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
    this.travelApplicationMasterService.deleteHotelDtlRow(id).subscribe(any => {
      this.snotifyService.remove(toast.id);
      this.travelApplicationMaster.travelAccommodationDetails.splice(index, 1);
    });
  }

  removeRowForex(index: any) {
    if (this.travelApplicationMaster.travelForexDetails[index].id !== undefined) {
      this.deleteRowFx(this.travelApplicationMaster.travelForexDetails[index].id, index);
    } else {
      this.travelApplicationMaster.travelForexDetails.splice(index, 1);
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
    this.travelApplicationMasterService.deleteDetailRowFx(id).subscribe(any => {
      this.snotifyService.remove(toast.id);
      this.travelApplicationMaster.travelForexDetails.splice(index, 1);
    });
  }

  removeRowPassg(index: any) {
    if (this.travelApplicationMaster.travelPassengerDetails[index].id !== undefined) {
      this.deleteRowPassg(this.travelApplicationMaster.travelPassengerDetails[index].id, index);
    } else {
      this.travelApplicationMaster.travelPassengerDetails.splice(index, 1);
    }
  }

  deleteRowPassg(id, index) {
    this.snotifyService.confirm('Are you sure to delete row?', 'Confirm', {
      timeout: 25000,
      showProgressBar: false,
      closeOnClick: false,
      pauseOnHover: true,
      position: SnotifyPosition.centerTop,
      buttons: [
        { text: 'Yes', action: toast => this.deletePassg(toast, id, index), bold: false },
        { text: 'No', action: toast => this.snotifyService.remove(toast.id) }
      ]
    });
  }

  deletePassg(toast, id, index) {
    this.travelApplicationMasterService.deleteDetailRowPassg(id).subscribe(any => {
      this.snotifyService.remove(toast.id);
      this.travelApplicationMaster.travelPassengerDetails.splice(index, 1);
    });
  }

  previousState() {
    window.history.back();
  }

  method01() {
    this.travelApplicationMaster.travelDays = moment(this.travelApplicationMaster.travelTodate).diff(
      moment(this.travelApplicationMaster.travelFromdate),
      'days'
    );
  }

  save() {
    this.isSaving = true;
    console.log('values: ', this.travelApplicationMaster);
    if (this.travelApplicationMaster.id !== undefined) {
      this.subscribeToSaveResponse(this.travelApplicationMasterService.update(this.travelApplicationMaster));
    } else {
      this.subscribeToSaveResponse(this.travelApplicationMasterService.create(this.travelApplicationMaster));
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

  ngOnDestroy() {
    this.ngbModalRef = null;
  }

  attachment() {
    this.travelApplicationMasterService.findAttach(this.travelApplicationMaster.id).subscribe(travelMasterAttach => {
      this.ngbModalRef = this.modalService.open(TravelMasterAttachComponent as Component, {
        size: 'lg',
        backdrop: 'static',
        windowClass: 'mediumModal'
      });
      this.ngbModalRef.componentInstance.travelMasterAttaches = travelMasterAttach.body;
      this.ngbModalRef.componentInstance.travelApplicationMaster = this.travelApplicationMaster;
    });
  }
}
