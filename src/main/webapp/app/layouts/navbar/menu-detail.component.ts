import { Component, OnInit } from '@angular/core';
import { EmployeeView } from 'app/shared/model/employee-view.model';
import { ProfileService } from 'app/layouts';
import { DoctorScheduleService } from './doctor-schedule.service';
import { AccountService } from 'app/core/auth/account.service';

@Component({
  selector: 'jhi-menu-details',
  templateUrl: './menu-detail.component.html',
  styleUrls: ['./navbar.scss']
})
export class MenuDetailComponent implements OnInit {
  inProduction: boolean;
  employeeview: EmployeeView;
  sidebarItems: any;
  swaggerEnabled: boolean;
  isNavbarCollapsed: boolean;
  currentAccount: any;

  constructor(
    private profileService: ProfileService,
    private doctorScheduleService: DoctorScheduleService,
    private accountService: AccountService
  ) {
    this.profileService.getProfileInfo().then(profileInfo => {
      this.inProduction = profileInfo.inProduction;
      this.swaggerEnabled = profileInfo.swaggerEnabled;
    });
  }

  ngOnInit() {
    this.accountService.identity().then(account => {
      this.currentAccount = account;
      if (this.currentAccount) {
        this.doctorScheduleService.access(this.currentAccount.login).subscribe(menuDetails => {
          this.sidebarItems = menuDetails.body;
        });
        this.doctorScheduleService.find(this.currentAccount.login).subscribe(employeeview => {
          this.employeeview = employeeview.body;
        });
      }
    });
  }

  public collapseMenu(item) {
    if (item.isCollapsed === true) {
      item.isCollapsed = false;
    } else {
      item.isCollapsed = true;
    }
  }

  collapseNavbar() {
    this.isNavbarCollapsed = true;
  }
}
