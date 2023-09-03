import { Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { OrderProfitabilityComponent } from './order-profitability.component';

export const userPlantRoute: Routes = [
  {
    path: '',
    component: OrderProfitabilityComponent,
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'Order Profitability'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const userPlantPopupRoute: Routes = [];
