import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { IVehicleMaster } from 'app/shared/model/vehicle-master.model';
import { VehicleMasterService } from './vehicle-master.service';
import { DateTimeAdapter, OWL_DATE_TIME_FORMATS, OWL_DATE_TIME_LOCALE } from 'ng-pick-datetime';
import { MomentDateTimeAdapter } from 'ng-pick-datetime-moment';
import { MY_MOMENT_FORMATS } from 'app/entities/leave-master';
import { IEmployeeView } from 'app/shared/model/employee-view.model';
import { EmployeeViewService } from 'app/entities/employee-view';
import { UserService } from 'app/core/user/user.service';
import { IUser } from 'app/core/user/user.model';

@Component({
  selector: 'jhi-vehicle-master-update',
  templateUrl: './vehicle-master-update.component.html',
  providers: [
    // `MomentDateTimeAdapter` and `OWL_MOMENT_DATE_TIME_FORMATS` can be automatically provided by importing
    // `OwlMomentDateTimeModule` in your applications root module. We provide it at the component level
    // here, due to limitations of our example generation script.
    { provide: DateTimeAdapter, useClass: MomentDateTimeAdapter, deps: [OWL_DATE_TIME_LOCALE] },
    { provide: OWL_DATE_TIME_FORMATS, useValue: MY_MOMENT_FORMATS }
  ]
})
export class VehicleMasterUpdateComponent implements OnInit {
  vehicleMaster: IVehicleMaster;
  isSaving: boolean;
  vehicleDate: string;
  createdDate: string;
  hodApprovedDate: string;
  adminApprovedDate: string;
  employeeView: IEmployeeView;
  hodEmployeeView: IEmployeeView;

  constructor(
    protected vehicleMasterService: VehicleMasterService,
    protected activatedRoute: ActivatedRoute,
    protected userService: UserService,
    protected employeeViewService: EmployeeViewService
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ vehicleMaster }) => {
      this.vehicleMaster = vehicleMaster;
      this.vehicleDate = this.vehicleMaster.vehicleDate != null ? this.vehicleMaster.vehicleDate.format(DATE_TIME_FORMAT) : null;
      this.createdDate = this.vehicleMaster.createdDate != null ? this.vehicleMaster.createdDate.format(DATE_TIME_FORMAT) : null;
      this.hodApprovedDate =
        this.vehicleMaster.hodApprovedDate != null ? this.vehicleMaster.hodApprovedDate.format(DATE_TIME_FORMAT) : null;
      this.adminApprovedDate =
        this.vehicleMaster.adminApprovedDate != null ? this.vehicleMaster.adminApprovedDate.format(DATE_TIME_FORMAT) : null;
    });
    if (this.vehicleMaster && this.vehicleMaster.id === undefined) {
      this.vehicleMaster.noVehicle = 1;
      this.vehicleMaster.flag = 'E';
      this.userService.currentuser().subscribe((res: HttpResponse<IUser>) => {
        this.vehicleMaster.user = res.body;
        this.employeeViewService.find(res.body.login).subscribe((res1: HttpResponse<IEmployeeView>) => {
          this.employeeView = res1.body;
          if (this.employeeView.supervisor !== undefined && this.employeeView.supervisor.includes('(') === true) {
            const hodcardNo = this.employeeView.supervisor.substring(0, this.employeeView.supervisor.indexOf('('));
            this.employeeViewService.findByCard(hodcardNo).subscribe((res2: HttpResponse<IEmployeeView>) => {
              this.hodEmployeeView = res2.body;
              this.vehicleMaster.hodApprovedBy = res2.body.login.toLowerCase();
            });
          }
        });
      });
    }
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    this.vehicleMaster.vehicleDate = this.vehicleDate != null ? moment(this.vehicleDate, DATE_TIME_FORMAT) : null;
    this.vehicleMaster.createdDate = this.createdDate != null ? moment(this.createdDate, DATE_TIME_FORMAT) : null;
    this.vehicleMaster.hodApprovedDate = this.hodApprovedDate != null ? moment(this.hodApprovedDate, DATE_TIME_FORMAT) : null;
    this.vehicleMaster.adminApprovedDate = this.adminApprovedDate != null ? moment(this.adminApprovedDate, DATE_TIME_FORMAT) : null;
    if (this.vehicleMaster.id !== undefined) {
      // this.subscribeToSaveResponse(this.vehicleMasterService.update(this.vehicleMaster));
    } else {
      this.subscribeToSaveResponse(this.vehicleMasterService.create(this.vehicleMaster));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IVehicleMaster>>) {
    result.subscribe((res: HttpResponse<IVehicleMaster>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
}
