import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { FabricSplFinishMaster } from 'app/shared/model/fabric-spl-finish-master.model';
import { FabricSplFinishMasterService } from './fabric-spl-finish-master.service';
import { FabricSplFinishMasterComponent } from './fabric-spl-finish-master.component';
import { FabricSplFinishMasterDetailComponent } from './fabric-spl-finish-master-detail.component';
import { FabricSplFinishMasterUpdateComponent } from './fabric-spl-finish-master-update.component';
import { FabricSplFinishMasterDeletePopupComponent } from './fabric-spl-finish-master-delete-dialog.component';
import { IFabricSplFinishMaster } from 'app/shared/model/fabric-spl-finish-master.model';

@Injectable({ providedIn: 'root' })
export class FabricSplFinishMasterResolve implements Resolve<IFabricSplFinishMaster> {
  constructor(private service: FabricSplFinishMasterService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IFabricSplFinishMaster> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<FabricSplFinishMaster>) => response.ok),
        map((fabricSplFinishMaster: HttpResponse<FabricSplFinishMaster>) => fabricSplFinishMaster.body)
      );
    }
    return of(new FabricSplFinishMaster());
  }
}

export const fabricSplFinishMasterRoute: Routes = [
  {
    path: '',
    component: FabricSplFinishMasterComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      defaultSort: 'id,asc',
      pageTitle: 'FabricSplFinishMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: FabricSplFinishMasterDetailComponent,
    resolve: {
      fabricSplFinishMaster: FabricSplFinishMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'FabricSplFinishMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: FabricSplFinishMasterUpdateComponent,
    resolve: {
      fabricSplFinishMaster: FabricSplFinishMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'FabricSplFinishMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: FabricSplFinishMasterUpdateComponent,
    resolve: {
      fabricSplFinishMaster: FabricSplFinishMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'FabricSplFinishMasters'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const fabricSplFinishMasterPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: FabricSplFinishMasterDeletePopupComponent,
    resolve: {
      fabricSplFinishMaster: FabricSplFinishMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'FabricSplFinishMasters'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
