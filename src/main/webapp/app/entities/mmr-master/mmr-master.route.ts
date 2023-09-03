import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MMRMaster } from 'app/shared/model/mmr-master.model';
import { MMRMasterService } from './mmr-master.service';
import { MMRMasterComponent } from './mmr-master.component';
import { MMRMasterDetailComponent } from './mmr-master-detail.component';
import { MMRMasterUpdateComponent } from './mmr-master-update.component';
import { MMRMasterDeletePopupComponent } from './mmr-master-delete-dialog.component';
import { IMMRMaster } from 'app/shared/model/mmr-master.model';
import { MMRSearchBean } from 'app/shared/model/mmr-search-bean.model';
import * as moment from 'moment';
@Injectable({ providedIn: 'root' })
export class MMRMasterResolve implements Resolve<IMMRMaster> {
  constructor(private service: MMRMasterService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<MMRMaster> {
    const monthYear = route.params['monthYear'] ? route.params['monthYear'] : null;
    const factory = route.params['factory'] ? route.params['factory'] : null;
    if (monthYear) {
      const mmrSearchBean = new MMRSearchBean();
      mmrSearchBean.monthYear = moment(monthYear, 'DDMMYYYY');
      mmrSearchBean.factory = factory;
      return this.service.customFind(mmrSearchBean).pipe(
        filter((response: HttpResponse<MMRMaster>) => response.ok),
        map((mMRMaster: HttpResponse<MMRMaster>) => mMRMaster.body)
      );
    }
    return of(new MMRMaster());
  }
}

export const mMRMasterRoute: Routes = [
  {
    path: '',
    component: MMRMasterComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      defaultSort: 'id,asc',
      pageTitle: 'MMRMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MMRMasterDetailComponent,
    resolve: {
      mMRMaster: MMRMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'MMRMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MMRMasterUpdateComponent,
    resolve: {
      mMRMaster: MMRMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'MMRMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':monthYear/:factory/edit',
    component: MMRMasterUpdateComponent,
    resolve: {
      mMRMaster: MMRMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'MMRMasters'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mMRMasterPopupRoute: Routes = [
  {
    path: 'mmr-master/:id/delete',
    component: MMRMasterDeletePopupComponent,
    resolve: {
      mMRMaster: MMRMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'MMRMasters'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
