import { Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ConnectMeComponent } from './connect-me.component';

export const CONNECT_ME_ROUTE: Routes = [
  {
    path: '',
    component: ConnectMeComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      defaultSort: 'id,asc',
      pageTitle: 'Connect Me'
    },
    canActivate: [UserRouteAccessService]
  }
];
