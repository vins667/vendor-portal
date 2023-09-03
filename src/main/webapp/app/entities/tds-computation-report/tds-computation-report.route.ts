import { Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { TdsComputationReportComponent } from './tds-computation-report.component';

export const tdsComputationReportRoute: Routes = [
  {
    path: '',
    component: TdsComputationReportComponent,
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'TDS Computation Report'
    },
    canActivate: [UserRouteAccessService]
  }
];
