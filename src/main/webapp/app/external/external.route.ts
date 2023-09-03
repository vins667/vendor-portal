import { Route } from '@angular/router';
import { ExternalComponent } from './';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';

export const EXTERNAL_ROUTE: Route = {
  path: '',
  component: ExternalComponent,
  data: {
    authorities: ['ROLE_USER', 'ROLE_ADMIN'],
    pageTitle: 'Vamani Pulse'
  },
  canActivate: [UserRouteAccessService]
};
