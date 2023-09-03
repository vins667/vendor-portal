import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { FabricOthersMaster } from 'app/shared/model/fabric-others-master.model';
import { FabricOthersMasterService } from './fabric-others-master.service';
import { FabricOthersMasterComponent } from './fabric-others-master.component';
import { FabricOthersMasterDetailComponent } from './fabric-others-master-detail.component';
import { FabricOthersMasterUpdateComponent } from './fabric-others-master-update.component';
import { FabricOthersMasterDeletePopupComponent } from './fabric-others-master-delete-dialog.component';
import { IFabricOthersMaster } from 'app/shared/model/fabric-others-master.model';

@Injectable({ providedIn: 'root' })
export class FabricOthersMasterResolve implements Resolve<IFabricOthersMaster> {
  constructor(private service: FabricOthersMasterService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IFabricOthersMaster> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<FabricOthersMaster>) => response.ok),
        map((fabricOthersMaster: HttpResponse<FabricOthersMaster>) => fabricOthersMaster.body)
      );
    }
    return of(new FabricOthersMaster());
  }
}

export const fabricOthersMasterRoute: Routes = [
  {
    path: '',
    component: FabricOthersMasterComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      defaultSort: 'id,asc',
      pageTitle: 'FabricOthersMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: FabricOthersMasterDetailComponent,
    resolve: {
      fabricOthersMaster: FabricOthersMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'FabricOthersMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: FabricOthersMasterUpdateComponent,
    resolve: {
      fabricOthersMaster: FabricOthersMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'FabricOthersMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: FabricOthersMasterUpdateComponent,
    resolve: {
      fabricOthersMaster: FabricOthersMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'FabricOthersMasters'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const fabricOthersMasterPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: FabricOthersMasterDeletePopupComponent,
    resolve: {
      fabricOthersMaster: FabricOthersMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'FabricOthersMasters'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
