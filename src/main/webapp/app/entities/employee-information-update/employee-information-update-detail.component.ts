import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { IEmployeeInformationUpdate } from 'app/shared/model/employee-information-update.model';

@Component({
  selector: 'jhi-employee-information-update-detail',
  templateUrl: './employee-information-update-detail.component.html'
})
export class EmployeeInformationUpdateDetailComponent implements OnInit {
  employeeInformationUpdate: IEmployeeInformationUpdate;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ employeeInformationUpdate }) => {
      this.employeeInformationUpdate = employeeInformationUpdate;
    });
  }

  previousState() {
    window.history.back();
  }
}
