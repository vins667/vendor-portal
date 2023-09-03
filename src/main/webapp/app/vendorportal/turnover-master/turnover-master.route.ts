import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { TurnoverMaster } from 'app/shared/model/turnover-master.model';
import { TurnoverMasterService } from './turnover-master.service';
import { TurnoverMasterComponent } from './turnover-master.component';
import { TurnoverMasterDetailComponent } from './turnover-master-detail.component';
import { TurnoverMasterUpdateComponent } from './turnover-master-update.component';
import { TurnoverMasterDeletePopupComponent } from './turnover-master-delete-dialog.component';
import { ITurnoverMaster } from 'app/shared/model/turnover-master.model';

@Injectable({ providedIn: 'root' })
export class TurnoverMasterResolve implements Resolve<ITurnoverMaster> {
  constructor(private service: TurnoverMasterService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<TurnoverMaster> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<TurnoverMaster>) => response.ok),
        map((turnoverMaster: HttpResponse<TurnoverMaster>) => turnoverMaster.body)
      );
    }
    return of(new TurnoverMaster());
  }
}

export const turnoverMasterRoute: Routes = [
  {
    path: '',
    component: TurnoverMasterComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      defaultSort: 'id,asc',
      pageTitle: 'TurnoverMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: TurnoverMasterDetailComponent,
    resolve: {
      turnoverMaster: TurnoverMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'TurnoverMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: TurnoverMasterUpdateComponent,
    resolve: {
      turnoverMaster: TurnoverMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'TurnoverMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: TurnoverMasterUpdateComponent,
    resolve: {
      turnoverMaster: TurnoverMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'TurnoverMasters'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const turnoverMasterPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: TurnoverMasterDeletePopupComponent,
    resolve: {
      turnoverMaster: TurnoverMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'TurnoverMasters'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
