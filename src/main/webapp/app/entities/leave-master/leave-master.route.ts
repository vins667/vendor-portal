import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { LeaveMaster } from 'app/shared/model/leave-master.model';
import { LeaveMasterService } from './leave-master.service';
import { LeaveMasterComponent } from './leave-master.component';
import { LeaveMasterDetailComponent } from './leave-master-detail.component';
import { LeaveMasterUpdateComponent } from './leave-master-update.component';
import { LeaveMasterDeletePopupComponent } from './leave-master-delete-dialog.component';
import { ILeaveMaster } from 'app/shared/model/leave-master.model';

@Injectable({ providedIn: 'root' })
export class LeaveMasterResolve implements Resolve<ILeaveMaster> {
  constructor(private service: LeaveMasterService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<LeaveMaster> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<LeaveMaster>) => response.ok),
        map((leaveMaster: HttpResponse<LeaveMaster>) => leaveMaster.body)
      );
    }
    return of(new LeaveMaster());
  }
}

export const leaveMasterRoute: Routes = [
  {
    path: '',
    component: LeaveMasterComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      defaultSort: 'id,dsc',
      pageTitle: 'Leave Entry'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: LeaveMasterDetailComponent,
    resolve: {
      leaveMaster: LeaveMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'Leave Entry View'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: LeaveMasterUpdateComponent,
    resolve: {
      leaveMaster: LeaveMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'Leave Entry'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: LeaveMasterUpdateComponent,
    resolve: {
      leaveMaster: LeaveMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'Leave Entry Edit'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const leaveMasterPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: LeaveMasterDeletePopupComponent,
    resolve: {
      leaveMaster: LeaveMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'LeaveMasters'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
