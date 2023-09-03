import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { YarnTypeMaster } from 'app/shared/model/yarn-type-master.model';
import { YarnTypeMasterService } from './yarn-type-master.service';
import { YarnTypeMasterComponent } from './yarn-type-master.component';
import { YarnTypeMasterDetailComponent } from './yarn-type-master-detail.component';
import { YarnTypeMasterUpdateComponent } from './yarn-type-master-update.component';
import { YarnTypeMasterDeletePopupComponent } from './yarn-type-master-delete-dialog.component';
import { IYarnTypeMaster } from 'app/shared/model/yarn-type-master.model';

@Injectable({ providedIn: 'root' })
export class YarnTypeMasterResolve implements Resolve<IYarnTypeMaster> {
  constructor(private service: YarnTypeMasterService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IYarnTypeMaster> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<YarnTypeMaster>) => response.ok),
        map((yarnTypeMaster: HttpResponse<YarnTypeMaster>) => yarnTypeMaster.body)
      );
    }
    return of(new YarnTypeMaster());
  }
}

export const yarnTypeMasterRoute: Routes = [
  {
    path: '',
    component: YarnTypeMasterComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      defaultSort: 'id,asc',
      pageTitle: 'YarnTypeMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: YarnTypeMasterDetailComponent,
    resolve: {
      yarnTypeMaster: YarnTypeMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'YarnTypeMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: YarnTypeMasterUpdateComponent,
    resolve: {
      yarnTypeMaster: YarnTypeMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'YarnTypeMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: YarnTypeMasterUpdateComponent,
    resolve: {
      yarnTypeMaster: YarnTypeMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'YarnTypeMasters'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const yarnTypeMasterPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: YarnTypeMasterDeletePopupComponent,
    resolve: {
      yarnTypeMaster: YarnTypeMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'YarnTypeMasters'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
