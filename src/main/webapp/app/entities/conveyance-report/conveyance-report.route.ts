import { Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ConveyanceReportComponent } from './conveyance-report.component';

export const conveyanceReportRoute: Routes = [
  {
    path: '',
    component: ConveyanceReportComponent,
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'Conveyance Report Report'
    },
    canActivate: [UserRouteAccessService]
  }
];
