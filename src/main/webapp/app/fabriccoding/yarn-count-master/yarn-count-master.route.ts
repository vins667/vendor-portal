import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { YarnCountMaster } from 'app/shared/model/yarn-count-master.model';
import { YarnCountMasterService } from './yarn-count-master.service';
import { YarnCountMasterComponent } from './yarn-count-master.component';
import { YarnCountMasterDetailComponent } from './yarn-count-master-detail.component';
import { YarnCountMasterUpdateComponent } from './yarn-count-master-update.component';
import { YarnCountMasterDeletePopupComponent } from './yarn-count-master-delete-dialog.component';
import { IYarnCountMaster } from 'app/shared/model/yarn-count-master.model';

@Injectable({ providedIn: 'root' })
export class YarnCountMasterResolve implements Resolve<IYarnCountMaster> {
  constructor(private service: YarnCountMasterService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IYarnCountMaster> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<YarnCountMaster>) => response.ok),
        map((yarnCountMaster: HttpResponse<YarnCountMaster>) => yarnCountMaster.body)
      );
    }
    return of(new YarnCountMaster());
  }
}

export const yarnCountMasterRoute: Routes = [
  {
    path: '',
    component: YarnCountMasterComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      defaultSort: 'id,asc',
      pageTitle: 'YarnCountMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: YarnCountMasterDetailComponent,
    resolve: {
      yarnCountMaster: YarnCountMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'YarnCountMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: YarnCountMasterUpdateComponent,
    resolve: {
      yarnCountMaster: YarnCountMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'YarnCountMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: YarnCountMasterUpdateComponent,
    resolve: {
      yarnCountMaster: YarnCountMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'YarnCountMasters'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const yarnCountMasterPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: YarnCountMasterDeletePopupComponent,
    resolve: {
      yarnCountMaster: YarnCountMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'YarnCountMasters'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
