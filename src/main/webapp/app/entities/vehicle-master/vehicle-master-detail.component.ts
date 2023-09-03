import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { IVehicleMaster } from 'app/shared/model/vehicle-master.model';
import { IEmployeeView } from 'app/shared/model/employee-view.model';
import { HttpResponse } from '@angular/common/http';
import { EmployeeViewService } from 'app/entities/employee-view';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

@Component({
  selector: 'jhi-vehicle-master-detail',
  templateUrl: './vehicle-master-detail.component.html'
})
export class VehicleMasterDetailComponent implements OnInit {
  vehicleMaster: IVehicleMaster;
  hodEmployeeView: IEmployeeView;
  vehicleDate: any;

  constructor(protected activatedRoute: ActivatedRoute, protected employeeViewService: EmployeeViewService) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ vehicleMaster }) => {
      this.vehicleMaster = vehicleMaster;
      this.vehicleDate = this.vehicleMaster.vehicleDate != null ? this.vehicleMaster.vehicleDate.format(DATE_TIME_FORMAT) : null;
      const hodcardNo = this.vehicleMaster.hodApprovedBy;
      this.employeeViewService.findByCard(hodcardNo).subscribe((res2: HttpResponse<IEmployeeView>) => {
        this.hodEmployeeView = res2.body;
      });
    });
  }

  previousState() {
    window.history.back();
  }
}
