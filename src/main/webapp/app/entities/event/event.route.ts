import { Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { EventComponent } from './event.component';

export const EVENT_ROUTE: Routes = [
  {
    path: '',
    component: EventComponent,
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'Event!'
    },
    canActivate: [UserRouteAccessService]
  }
];
