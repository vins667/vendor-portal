import { Routes } from '@angular/router';
import { trackerRoute, userMgmtRoute } from './';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';

const ADMIN_ROUTES = [trackerRoute, ...userMgmtRoute];

export const adminState: Routes = [
  {
    path: '',
    data: {
      authorities: ['ROLE_ADMIN', 'ROLE_USER']
    },
    canActivate: [UserRouteAccessService],
    children: ADMIN_ROUTES
  }
];
