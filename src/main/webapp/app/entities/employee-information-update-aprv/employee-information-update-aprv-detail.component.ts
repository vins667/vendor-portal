import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { IEmployeeInformationUpdateAprv } from 'app/shared/model/employee-information-update-aprv.model';

@Component({
  selector: 'jhi-employee-information-update-aprv-detail',
  templateUrl: './employee-information-update-aprv-detail.component.html'
})
export class EmployeeInformationUpdateAprvDetailComponent implements OnInit {
  employeeInformationUpdateAprv: IEmployeeInformationUpdateAprv;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ employeeInformationUpdateAprv }) => {
      this.employeeInformationUpdateAprv = employeeInformationUpdateAprv;
    });
  }

  previousState() {
    window.history.back();
  }
}
