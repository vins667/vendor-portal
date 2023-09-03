import { RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { SalesOrderClosingComponent } from './sales-order-closing.component';
import { SalesOrderClosingUpdateComponent } from './sales-order-closing-update.component';

export const salesOrderClosingRoute: Routes = [
  {
    path: '',
    component: SalesOrderClosingComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: '1,asc',
      pageTitle: 'SalesOrderClosings'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'edit',
    component: SalesOrderClosingUpdateComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'SalesOrderClosings'
    },
    canActivate: [UserRouteAccessService]
  }
];
