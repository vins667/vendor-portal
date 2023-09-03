import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { VcutSessionMaster } from 'app/shared/model/vcut-session-master.model';
import { VcutSessionMasterService } from './vcut-session-master.service';
import { VcutSessionMasterComponent } from './vcut-session-master.component';
import { VcutSessionMasterDetailComponent } from './vcut-session-master-detail.component';
import { VcutSessionMasterUpdateComponent } from './vcut-session-master-update.component';
import { VcutSessionMasterDeletePopupComponent } from './vcut-session-master-delete-dialog.component';
import { IVcutSessionMaster } from 'app/shared/model/vcut-session-master.model';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';

@Injectable({ providedIn: 'root' })
export class VcutSessionMasterResolve implements Resolve<IVcutSessionMaster> {
  constructor(private service: VcutSessionMasterService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<VcutSessionMaster> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<VcutSessionMaster>) => response.ok),
        map((vcutSessionMaster: HttpResponse<VcutSessionMaster>) => vcutSessionMaster.body)
      );
    }
    return of(new VcutSessionMaster());
  }
}

export const vcutSessionMasterRoute: Routes = [
  {
    path: '',
    component: VcutSessionMasterComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      defaultSort: 'id,asc',
      pageTitle: 'Session Plan Master'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: VcutSessionMasterDetailComponent,
    resolve: {
      vcutSessionMaster: VcutSessionMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'Session Plan Master'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: VcutSessionMasterUpdateComponent,
    resolve: {
      vcutSessionMaster: VcutSessionMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'Session Plan Master'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: VcutSessionMasterUpdateComponent,
    resolve: {
      vcutSessionMaster: VcutSessionMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'Session Plan Master'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const vcutSessionMasterPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: VcutSessionMasterDeletePopupComponent,
    resolve: {
      vcutSessionMaster: VcutSessionMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'Session Plan Master'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
