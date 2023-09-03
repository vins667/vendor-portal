import { Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { NdaComponent } from './nda.component';
import { NdaViewComponent } from './nda-view.component';

export const NDA_ROUTE: Routes = [
  {
    path: '',
    component: NdaComponent,
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'Vamani Pulse'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'view',
    component: NdaViewComponent,
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'Vamani Pulse'
    },
    canActivate: [UserRouteAccessService]
  }
];
