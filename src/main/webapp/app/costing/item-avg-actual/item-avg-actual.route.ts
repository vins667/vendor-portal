import { Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ItemAvgActualComponent } from './item-avg-actual.component';

export const itemAvgActualRoute: Routes = [
  {
    path: '',
    component: ItemAvgActualComponent,
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'Product Actual Avg'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const itemAvgActualPopupRoute: Routes = [];
