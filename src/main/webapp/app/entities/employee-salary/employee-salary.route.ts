import { Routes } from '@angular/router';
import { EmployeeSalaryComponent } from 'app/entities/employee-salary/employee-salary.component';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';

export const employeeSalaryRoute: Routes = [
  {
    path: '',
    component: EmployeeSalaryComponent,
    data: {
      authorities: ['ROLE_ADMIN', 'ROLE_USER'],
      pageTitle: 'Salary'
    },
    canActivate: [UserRouteAccessService]
  }
];
