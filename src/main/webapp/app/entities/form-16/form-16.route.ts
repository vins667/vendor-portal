import { Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';

import { Form16Component } from './form-16.component';

export const Form16Route: Routes = [
  {
    path: '',
    component: Form16Component,
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'Form16s'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const Form16PopupRoute: Routes = [];
