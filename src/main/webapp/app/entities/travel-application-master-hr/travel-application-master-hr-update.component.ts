import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { TravelApplicationMasterHrService } from './travel-application-master-hr.service';
import { DateTimeAdapter, OWL_DATE_TIME_LOCALE, OWL_DATE_TIME_FORMATS } from 'ng-pick-datetime';
import * as _moment from 'moment';
import { MomentDateTimeAdapter } from 'ng-pick-datetime-moment';
import { ITravelApplicationMaster, TravelApplicationMaster } from 'app/shared/model/travel-application-master.model';
import { ITravelCurrencyMaster } from 'app/shared/model/travel-currency-master.model';
import { JhiAlertService, JhiEventManager } from 'ng-jhipster';
import { TravelCurrencyMasterService } from '../travel-currency-master/travel-currency-master.service';
import { SnotifyPosition, SnotifyService } from 'ng-snotify';
import { toastConfig } from 'app/core/toast/toast-config';
import * as moment from 'moment';
import { TravelFlightDetails } from 'app/shared/model/travel-flight-details.model';
import { TravelAccommodationDetails } from 'app/shared/model/travel-accommodation-details.model';
import { TravelForexDetails } from 'app/shared/model/travel-forex-details.model';
import { EmployeeViewService } from 'app/entities/employee-view';
import { EmployeeView } from 'app/shared/model/employee-view.model';
import { TravelPassengerDetails } from 'app/shared/model/travel-passenger-details.model';
import { TravelApplicationMasterService } from 'app/entities/travel-application-master/travel-application-master.service';
import { TravelLuggageDetails } from 'app/shared/model/travel-luggage-details.model';
import { NgbModalRef, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { TravelApplicationMasterHrPopupComponent } from './travel-application-master-hr-popup.component';

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
  selector: 'jhi-travel-application-master-hr-update',
  templateUrl: './travel-application-master-hr-update.component.html',
  providers: [
    { provide: DateTimeAdapter, useClass: MomentDateTimeAdapter, deps: [OWL_DATE_TIME_LOCALE] },
    { provide: OWL_DATE_TIME_FORMATS, useValue: MY_MOMENT_FORMATS }
  ]
})
export class TravelApplicationMasterHrUpdateComponent implements OnInit {
  isSaving: boolean;
  travelApplicationMaster: ITravelApplicationMaster;
  travelCurrencyMaster: ITravelCurrencyMaster[];
  fromDate: any;
  toDate: any;
  empDetail: [];
  travelDate: any;
  accommodationDate: any;
  departureDate: any;
  arrivalDate: any;
  leaveDateFrom: any;
  leaveDateTo: any;
  hodEmployeeView: EmployeeView;
  protected ngbModalRef: NgbModalRef;

  constructor(
    protected travelApplicationMasterHrService: TravelApplicationMasterHrService,
    protected travelApplicationMasterService: TravelApplicationMasterService,
    protected jhiAlertService: JhiAlertService,
    protected travelCurrencyMasterService: TravelCurrencyMasterService,
    protected activatedRoute: ActivatedRoute,
    protected snotifyService: SnotifyService,
    protected modalService: NgbModal,
    protected eventManager: JhiEventManager,
    protected employeeViewService: EmployeeViewService
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.loadCurrencyList();
    this.activatedRoute.data.subscribe(({ travelApplicationMaster }) => {
      this.travelApplicationMaster = travelApplicationMaster;
      if (this.travelApplicationMaster && this.travelApplicationMaster.hodCode) {
        this.employeeViewService.findByCard(this.travelApplicationMaster.hodCode).subscribe(hodEmployeeView => {
          this.hodEmployeeView = hodEmployeeView.body;
        });
      }
      if (this.travelApplicationMaster && this.travelApplicationMaster.travelLuggageDetails) {
        this.loadLuggageDetail(this.travelApplicationMaster.travelLuggageDetails.length);
      } else {
        this.loadLuggageDetail(0);
      }
      this.leaveDateFrom =
        this.travelApplicationMaster.travelFromdate != null ? moment(this.travelApplicationMaster.travelFromdate) : undefined;
      this.leaveDateTo = this.travelApplicationMaster.travelTodate != null ? moment(this.travelApplicationMaster.travelTodate) : undefined;
    });
  }

  loadLuggageDetail(count) {
    if (count === 0) {
      this.travelApplicationMaster.travelLuggageDetails = new Array<TravelLuggageDetails>();
    }
    for (let i = count; i < 2; i++) {
      this.travelApplicationMaster.travelLuggageDetails.push(new TravelLuggageDetails());
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

  save() {
    this.isSaving = true;
    if (this.travelApplicationMaster.id !== undefined) {
      this.subscribeToSaveResponse(this.travelApplicationMasterHrService.update(this.travelApplicationMaster));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITravelApplicationMaster>>) {
    result.subscribe(() => this.onSaveSuccess(), () => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.snotifyService.success('save successfully!!!', '', toastConfig);
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }

  loadAttachments() {
    this.travelApplicationMasterHrService.findAttach(this.travelApplicationMaster.id).subscribe(travelMasterAttach => {
      this.ngbModalRef = this.modalService.open(TravelApplicationMasterHrPopupComponent as Component, {
        size: 'lg',
        backdrop: 'static',
        windowClass: 'mediumModal'
      });
      this.ngbModalRef.componentInstance.travelMasterAttaches = travelMasterAttach.body;
      this.ngbModalRef.componentInstance.travelApplicationMaster = this.travelApplicationMaster;
    });
  }
}
