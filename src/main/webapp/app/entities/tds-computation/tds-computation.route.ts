import { Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { TdsComputationUpdateComponent } from './tds-computation-update.component';

export const tdsComputationRoute: Routes = [
  {
    path: '',
    component: TdsComputationUpdateComponent,
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'TDS Computation'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const tdsComputationPopupRoute: Routes = [];
