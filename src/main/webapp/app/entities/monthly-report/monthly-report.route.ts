import { Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { MonthlyReportComponent } from './monthly-report.component';

export const monthlyReportRoute: Routes = [
  {
    path: '',
    component: MonthlyReportComponent,
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'Salary Summary Report'
    },
    canActivate: [UserRouteAccessService]
  }
];
