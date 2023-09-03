import { Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { LeavePendingReportComponent } from './leave-pending-report.component';

export const leavePendingReportRoute: Routes = [
  {
    path: '',
    component: LeavePendingReportComponent,
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      defaultSort: 'id,asc',
      pageTitle: 'Leave Pending Reports'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const leavePendingReportPopupRoute: Routes = [{}];
