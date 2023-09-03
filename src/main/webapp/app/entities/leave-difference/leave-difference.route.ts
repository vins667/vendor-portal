import { Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { LeaveDifferenceComponent } from './leave-difference.component';
export const leaveDifferenceRoute: Routes = [
  {
    path: '',
    component: LeaveDifferenceComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      defaultSort: 'id,asc',
      pageTitle: 'Leave Differences'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const leaveDifferencePopupRoute: Routes = [{}];
