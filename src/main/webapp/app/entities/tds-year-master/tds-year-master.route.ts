import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { TdsYearMaster } from 'app/shared/model/tds-year-master.model';
import { TdsYearMasterService } from './tds-year-master.service';
import { TdsYearMasterComponent } from './tds-year-master.component';
import { TdsYearMasterDetailComponent } from './tds-year-master-detail.component';
import { TdsYearMasterUpdateComponent } from './tds-year-master-update.component';
import { TdsYearMasterDeletePopupComponent } from './tds-year-master-delete-dialog.component';
import { ITdsYearMaster } from 'app/shared/model/tds-year-master.model';

@Injectable({ providedIn: 'root' })
export class TdsYearMasterResolve implements Resolve<ITdsYearMaster> {
  constructor(private service: TdsYearMasterService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<TdsYearMaster> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<TdsYearMaster>) => response.ok),
        map((tdsYearMaster: HttpResponse<TdsYearMaster>) => tdsYearMaster.body)
      );
    }
    return of(new TdsYearMaster());
  }
}

export const tdsYearMasterRoute: Routes = [
  {
    path: '',
    component: TdsYearMasterComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_ADMIN', 'ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'TdsYearMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: TdsYearMasterDetailComponent,
    resolve: {
      tdsYearMaster: TdsYearMasterResolve
    },
    data: {
      authorities: ['ROLE_ADMIN', 'ROLE_USER'],
      pageTitle: 'TdsYearMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: TdsYearMasterUpdateComponent,
    resolve: {
      tdsYearMaster: TdsYearMasterResolve
    },
    data: {
      authorities: ['ROLE_ADMIN', 'ROLE_USER'],
      pageTitle: 'TdsYearMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: TdsYearMasterUpdateComponent,
    resolve: {
      tdsYearMaster: TdsYearMasterResolve
    },
    data: {
      authorities: ['ROLE_ADMIN', 'ROLE_USER'],
      pageTitle: 'TdsYearMasters'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const tdsYearMasterPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: TdsYearMasterDeletePopupComponent,
    resolve: {
      tdsYearMaster: TdsYearMasterResolve
    },
    data: {
      authorities: ['ROLE_ADMIN', 'ROLE_USER'],
      pageTitle: 'TdsYearMasters'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
