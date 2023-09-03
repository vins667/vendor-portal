import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { PollMaster } from 'app/shared/model/poll-master.model';
import { PollMasterService } from './poll-master.service';
import { PollMasterComponent } from './poll-master.component';
import { PollMasterDetailComponent } from './poll-master-detail.component';
import { PollMasterUpdateComponent } from './poll-master-update.component';
import { PollMasterDeletePopupComponent } from './poll-master-delete-dialog.component';
import { IPollMaster } from 'app/shared/model/poll-master.model';

@Injectable({ providedIn: 'root' })
export class PollMasterResolve implements Resolve<IPollMaster> {
  constructor(private service: PollMasterService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<PollMaster> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<PollMaster>) => response.ok),
        map((pollMaster: HttpResponse<PollMaster>) => pollMaster.body)
      );
    }
    return of(new PollMaster());
  }
}

export const pollMasterRoute: Routes = [
  {
    path: '',
    component: PollMasterComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      defaultSort: 'id,dsc',
      pageTitle: 'PollMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: PollMasterDetailComponent,
    resolve: {
      pollMaster: PollMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'PollMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: PollMasterUpdateComponent,
    resolve: {
      pollMaster: PollMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'PollMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: PollMasterUpdateComponent,
    resolve: {
      pollMaster: PollMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'PollMasters'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const pollMasterPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: PollMasterDeletePopupComponent,
    resolve: {
      pollMaster: PollMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'PollMasters'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
