import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { FabricCreationMaster } from 'app/shared/model/fabric-creation-master.model';
import { FabricCreationMasterService } from './fabric-creation-master.service';
import { FabricCreationMasterComponent } from './fabric-creation-master.component';
import { FabricCreationMasterDetailComponent } from './fabric-creation-master-detail.component';
import { FabricCreationMasterUpdateComponent } from './fabric-creation-master-update.component';
import { FabricCreationMasterDeletePopupComponent } from './fabric-creation-master-delete-dialog.component';
import { IFabricCreationMaster } from 'app/shared/model/fabric-creation-master.model';

@Injectable({ providedIn: 'root' })
export class FabricCreationMasterResolve implements Resolve<IFabricCreationMaster> {
  constructor(private service: FabricCreationMasterService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<FabricCreationMaster> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<FabricCreationMaster>) => response.ok),
        map((fabricCreationMaster: HttpResponse<FabricCreationMaster>) => fabricCreationMaster.body)
      );
    }
    return of(new FabricCreationMaster());
  }
}

export const fabricCreationMasterRoute: Routes = [
  {
    path: '',
    component: FabricCreationMasterComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      defaultSort: 'id,asc',
      pageTitle: 'FabricCreationMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: FabricCreationMasterDetailComponent,
    resolve: {
      fabricCreationMaster: FabricCreationMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'FabricCreationMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: FabricCreationMasterUpdateComponent,
    resolve: {
      fabricCreationMaster: FabricCreationMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'FabricCreationMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: FabricCreationMasterUpdateComponent,
    resolve: {
      fabricCreationMaster: FabricCreationMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'FabricCreationMasters'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const fabricCreationMasterPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: FabricCreationMasterDeletePopupComponent,
    resolve: {
      fabricCreationMaster: FabricCreationMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'FabricCreationMasters'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
