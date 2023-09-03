import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { TdsGroupMaster } from 'app/shared/model/tds-group-master.model';
import { TdsGroupMasterService } from './tds-group-master.service';
import { TdsGroupMasterComponent } from './tds-group-master.component';
import { TdsGroupMasterDetailComponent } from './tds-group-master-detail.component';
import { TdsGroupMasterUpdateComponent } from './tds-group-master-update.component';
import { TdsGroupMasterDeletePopupComponent } from './tds-group-master-delete-dialog.component';
import { ITdsGroupMaster } from 'app/shared/model/tds-group-master.model';

@Injectable({ providedIn: 'root' })
export class TdsGroupMasterResolve implements Resolve<ITdsGroupMaster> {
  constructor(private service: TdsGroupMasterService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<TdsGroupMaster> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<TdsGroupMaster>) => response.ok),
        map((tdsGroupMaster: HttpResponse<TdsGroupMaster>) => tdsGroupMaster.body)
      );
    }
    return of(new TdsGroupMaster());
  }
}

export const tdsGroupMasterRoute: Routes = [
  {
    path: '',
    component: TdsGroupMasterComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      defaultSort: 'id,asc',
      pageTitle: 'Tds Group Masters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: TdsGroupMasterDetailComponent,
    resolve: {
      tdsGroupMaster: TdsGroupMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'Tds Group Masters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: TdsGroupMasterUpdateComponent,
    resolve: {
      tdsGroupMaster: TdsGroupMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'Tds Group Masters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: TdsGroupMasterUpdateComponent,
    resolve: {
      tdsGroupMaster: TdsGroupMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'Tds Group Masters'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const tdsGroupMasterPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: TdsGroupMasterDeletePopupComponent,
    resolve: {
      tdsGroupMaster: TdsGroupMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'Tds Group Masters'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
