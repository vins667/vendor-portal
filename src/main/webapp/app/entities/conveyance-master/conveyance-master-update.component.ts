import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { HttpErrorResponse, HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { JhiAlertService } from 'ng-jhipster';
import { IConveyanceMaster, ConveyanceMaster } from 'app/shared/model/conveyance-master.model';
import { ConveyanceMasterService } from './conveyance-master.service';
import { IRateMaster } from 'app/shared/model/rate-master.model';
import { RateMasterService } from 'app/entities/rate-master';
import { IEmployeeView } from 'app/shared/model/employee-view.model';
import { UserService } from 'app/core/user/user.service';
import { IUser } from 'app/core/user/user.model';
import { EmployeeViewService } from '../employee-view/employee-view.service';
import * as FileSaver from 'file-saver';
import { DateTimeAdapter, OWL_DATE_TIME_LOCALE, OWL_DATE_TIME_FORMATS } from 'ng-pick-datetime';
import { MomentDateTimeAdapter } from 'ng-pick-datetime-moment';
import { IConveyanceMasterDetails, ConveyanceMasterDetails } from 'app/shared/model/conveyance-master-details.model';
import { SnotifyPosition, SnotifyService } from 'ng-snotify';
import { DATE_TIME_FORMAT, DATE_FORMAT } from 'app/shared/constants/input.constants';
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
  selector: 'jhi-conveyance-master-update',
  templateUrl: './conveyance-master-update.component.html',
  encapsulation: ViewEncapsulation.None,
  providers: [
    // `MomentDateTimeAdapter` and `OWL_MOMENT_DATE_TIME_FORMATS` can be automatically provided by importing
    // `OwlMomentDateTimeModule` in your applications root module. We provide it at the component level
    // here, due to limitations of our example generation script.
    { provide: DateTimeAdapter, useClass: MomentDateTimeAdapter, deps: [OWL_DATE_TIME_LOCALE] },
    { provide: OWL_DATE_TIME_FORMATS, useValue: MY_MOMENT_FORMATS }
  ]
})
export class ConveyanceMasterUpdateComponent implements OnInit {
  isSaving: boolean;
  displayAttach: Boolean;
  fileNames: string;
  conveyanceMasters: IConveyanceMaster;
  selectedFiles: FileList[] = [];
  currentFileUpload: File[] = [];
  hodEmployeeView: IEmployeeView;
  employeeView: IEmployeeView;
  conveyanceDate: any;
  maxDate: any;
  isChecked: boolean;

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected conveyanceMasterService: ConveyanceMasterService,
    protected rateMasterService: RateMasterService,
    protected activatedRoute: ActivatedRoute,
    protected snotifyService: SnotifyService,
    protected userService: UserService,
    protected employeeViewService: EmployeeViewService
  ) {}

  ngOnInit() {
    this.isSaving = false;
    const date = new Date();
    this.conveyanceDate = moment(date.setHours(0, 0, 0, 0));
    this.maxDate = moment(date.setHours(0, 0, 0, 0));
    this.getApprovedBy();
    this.activatedRoute.data.subscribe(({ conveyanceMaster }) => {
      this.employeeView = conveyanceMaster.approvedBy;
      this.conveyanceMasters = conveyanceMaster;
      if (this.conveyanceMasters && this.conveyanceMasters.conveyanceType) {
        this.conveyanceDate = this.conveyanceMasters.conveyanceDate != null ? moment(this.conveyanceMasters.conveyanceDate) : undefined;
      } else {
        this.conveyanceMasters = new ConveyanceMaster();
        this.getMultipleConvence();
        this.conveyanceMasters.conveyanceType = 'O';
        this.conveyanceMasters.vehicleType = 'T';
        this.rateMasterService.CustQuery(this.conveyanceMasters.vehicleType).subscribe(res => {
          this.conveyanceMasters.rate = res.body.rate;
        });
      }
    });
  }

  changeRateMaster() {
    if (this.conveyanceMasters.vehicleType) {
      this.rateMasterService.CustQuery(this.conveyanceMasters.vehicleType).subscribe(res => {
        this.conveyanceMasters.rate = res.body.rate;
        if (this.conveyanceMasters.conveyanceMasterDetails) {
          this.getTotalDistance(0);
        }
      });
    }
  }

  getApprovedBy() {
    this.userService.currentuser().subscribe((res: HttpResponse<IUser>) => {
      this.employeeViewService.find(res.body.login).subscribe((res1: HttpResponse<IEmployeeView>) => {
        this.employeeView = res1.body;
        if (this.employeeView.supervisor !== undefined && this.employeeView.supervisor.includes('(') === true) {
          const hodcardNo = this.employeeView.supervisor.substring(0, this.employeeView.supervisor.indexOf('('));
          this.employeeViewService.findByCard(hodcardNo).subscribe((res2: HttpResponse<IEmployeeView>) => {
            this.hodEmployeeView = res2.body;
            this.conveyanceMasters.approvedBy = this.hodEmployeeView.login;
          });
        }
      });
    });
  }

  getMultipleConvence() {
    this.conveyanceMasters.conveyanceMasterDetails = new Array<ConveyanceMasterDetails>();
    for (let i = 0; i < 5; i++) {
      this.conveyanceMasters.conveyanceMasterDetails.push(new ConveyanceMasterDetails());
    }
  }

  previousState() {
    window.history.back();
  }

  selectFile(event, index) {
    const selectedFile: FileList = event.target.files;
    this.conveyanceMasters.conveyanceMasterDetails[index].currentFileUpload = selectedFile.item(0);
  }

  download(conveyanceMasterDetails: IConveyanceMasterDetails): any {
    this.conveyanceMasterService.download(conveyanceMasterDetails.id).subscribe(
      res => {
        FileSaver.saveAs(res, `${conveyanceMasterDetails.attachDisplayFile}`);
      },
      res => {
        this.onError(res.message);
      }
    );
  }

  getTotalDistance(index) {
    let value = 0;
    let miscAmt = 0;
    let ctr = 0;
    this.conveyanceMasters.conveyanceMasterDetails.forEach(conveyance => {
      ++ctr;
      if (conveyance.tripStart !== undefined && conveyance.tripEnd !== undefined) {
        if (conveyance.tripEnd < conveyance.tripStart) {
          this.conveyanceMasters.conveyanceMasterDetails[index].tripEnd = null;
          this.snotifyService.error('In-valid End Trip!', '', toastConfig);
        } else {
          const total = conveyance.tripEnd - conveyance.tripStart;
          if (conveyance.miscAmount && conveyance.miscAmount > 0) {
            miscAmt += conveyance.miscAmount;
          }
          value += total;
          this.conveyanceMasters.totalDistance = value;
          this.conveyanceMasters.totalAmount = this.conveyanceMasters.totalDistance * this.conveyanceMasters.rate;
        }
      }
    });
    if (ctr === this.conveyanceMasters.conveyanceMasterDetails.length) {
      this.conveyanceMasters.totalAmount = Number(Number(this.conveyanceMasters.totalAmount + miscAmt).toFixed());
    }
  }

  addRow() {
    if (this.conveyanceMasters.conveyanceMasterDetails) {
      this.conveyanceMasters.conveyanceMasterDetails.push(new ConveyanceMasterDetails());
    }
  }

  removeRow(index: any) {
    if (this.conveyanceMasters.conveyanceMasterDetails[index].id !== undefined) {
      this.deleteRow(this.conveyanceMasters.conveyanceMasterDetails[index].id, index);
    } else {
      this.conveyanceMasters.conveyanceMasterDetails.splice(index, 1);
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
    this.conveyanceMasterService.deleteDetail(id).subscribe(any => {
      this.snotifyService.remove(toast.id);
      this.conveyanceMasters.conveyanceMasterDetails.splice(index, 1);
    });
  }

  save() {
    this.isSaving = true;
    const leaveDate = this.conveyanceDate != null ? moment(this.conveyanceDate, DATE_TIME_FORMAT).startOf('day') : null;
    this.conveyanceMasters.conveyanceDate = leaveDate != null && leaveDate.isValid() ? leaveDate.format(DATE_FORMAT) : null;
    if (this.conveyanceMasters.id !== undefined) {
      this.subscribeToSaveResponse(this.conveyanceMasterService.update(this.conveyanceMasters));
    } else {
      this.subscribeToSaveResponse(this.conveyanceMasterService.create(this.conveyanceMasters));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IConveyanceMaster>>) {
    result.subscribe(() => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError(res.headers));
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(res: HttpHeaders) {
    this.isSaving = false;
    this.snotifyService.error(res.get('X-vamaniportalApp-error'), '', toastConfig);
  }
  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }

  trackRateMasterById(index: number, item: IRateMaster) {
    return item.id;
  }
}
