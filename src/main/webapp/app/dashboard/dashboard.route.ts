import { Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { DashboardComponent } from 'app/dashboard/dashboard.component';

export const DASHBOARD_ROUTE: Routes = [
  {
    path: '',
    component: DashboardComponent,
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'Vamani Pulse'
    },
    canActivate: [UserRouteAccessService]
  }
];
