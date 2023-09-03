import { Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { CutBundleLockComponent } from 'app/cutting/cut-bundle-lock/cut-bundle-lock.component';

export const CutBundleLockRoute: Routes = [
  {
    path: '',
    component: CutBundleLockComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      defaultSort: 'id,desc',
      pageTitle: 'Pulse | Cut Bundle Lock'
    },
    canActivate: [UserRouteAccessService]
  }
];
