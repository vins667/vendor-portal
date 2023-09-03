import { Component, OnInit } from '@angular/core';
import { EmployeeView } from 'app/shared/model/employee-view.model';
import { NgbModal, NgbModalConfig } from '@ng-bootstrap/ng-bootstrap';
import { DoctorScheduleService } from 'app/layouts/navbar/doctor-schedule.service';
import { AccountService } from 'app/core/auth/account.service';
import {Router} from '@angular/router';

@Component({
  selector: 'jhi-user-name-details',
  templateUrl: './userdetail.component.html'
})
export class UserDetailComponent implements OnInit {
  currentAccount: any;
  employeeview: EmployeeView;

  constructor(
    private doctorScheduleService: DoctorScheduleService,
    private modalService: NgbModal,
    config: NgbModalConfig,
    private accountService: AccountService,
    protected router: Router,
  ) {
    config.backdrop = 'static';
    config.keyboard = false;
  }

  ngOnInit() {
    this.accountService.identity().then(account => {
      this.currentAccount = account;
      if (this.currentAccount) {
        this.doctorScheduleService.find(this.currentAccount.login).subscribe(employeeview => {
          this.employeeview = employeeview.body;
        });
      }
    });
  }
  openView() {}
}
