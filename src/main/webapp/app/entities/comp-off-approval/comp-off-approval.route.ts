import { Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { CompOffApprovalComponent } from './comp-off-approval.component';

export const compOffApprovalRoute: Routes = [
  {
    path: '',
    component: CompOffApprovalComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      defaultSort: 'id,asc',
      pageTitle: 'CompOffMasters'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const compOffApprovalPopupRoute: Routes = [];
