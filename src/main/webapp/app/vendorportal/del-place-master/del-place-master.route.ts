import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { DelPlaceMaster } from 'app/shared/model/del-place-master.model';
import { DelPlaceMasterService } from './del-place-master.service';
import { DelPlaceMasterComponent } from './del-place-master.component';
import { DelPlaceMasterDetailComponent } from './del-place-master-detail.component';
import { DelPlaceMasterUpdateComponent } from './del-place-master-update.component';
import { DelPlaceMasterDeletePopupComponent } from './del-place-master-delete-dialog.component';
import { IDelPlaceMaster } from 'app/shared/model/del-place-master.model';

@Injectable({ providedIn: 'root' })
export class DelPlaceMasterResolve implements Resolve<IDelPlaceMaster> {
  constructor(private service: DelPlaceMasterService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IDelPlaceMaster> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<DelPlaceMaster>) => response.ok),
        map((delPlaceMaster: HttpResponse<DelPlaceMaster>) => delPlaceMaster.body)
      );
    }
    return of(new DelPlaceMaster());
  }
}

export const delPlaceMasterRoute: Routes = [
  {
    path: '',
    component: DelPlaceMasterComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_ADMIN', 'ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'DelPlaceMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: DelPlaceMasterDetailComponent,
    resolve: {
      delPlaceMaster: DelPlaceMasterResolve
    },
    data: {
      authorities: ['ROLE_ADMIN', 'ROLE_USER'],
      pageTitle: 'DelPlaceMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: DelPlaceMasterUpdateComponent,
    resolve: {
      delPlaceMaster: DelPlaceMasterResolve
    },
    data: {
      authorities: ['ROLE_ADMIN', 'ROLE_USER'],
      pageTitle: 'DelPlaceMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: DelPlaceMasterUpdateComponent,
    resolve: {
      delPlaceMaster: DelPlaceMasterResolve
    },
    data: {
      authorities: ['ROLE_ADMIN', 'ROLE_USER'],
      pageTitle: 'DelPlaceMasters'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const delPlaceMasterPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: DelPlaceMasterDeletePopupComponent,
    resolve: {
      delPlaceMaster: DelPlaceMasterResolve
    },
    data: {
      authorities: ['ROLE_ADMIN', 'ROLE_USER'],
      pageTitle: 'DelPlaceMasters'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
