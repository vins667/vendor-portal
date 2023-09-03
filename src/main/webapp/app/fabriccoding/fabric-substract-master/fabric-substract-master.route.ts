import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { FabricSubstractMaster } from 'app/shared/model/fabric-substract-master.model';
import { FabricSubstractMasterService } from './fabric-substract-master.service';
import { FabricSubstractMasterComponent } from './fabric-substract-master.component';
import { FabricSubstractMasterDetailComponent } from './fabric-substract-master-detail.component';
import { FabricSubstractMasterUpdateComponent } from './fabric-substract-master-update.component';
import { FabricSubstractMasterDeletePopupComponent } from './fabric-substract-master-delete-dialog.component';
import { IFabricSubstractMaster } from 'app/shared/model/fabric-substract-master.model';

@Injectable({ providedIn: 'root' })
export class FabricSubstractMasterResolve implements Resolve<IFabricSubstractMaster> {
  constructor(private service: FabricSubstractMasterService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IFabricSubstractMaster> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<FabricSubstractMaster>) => response.ok),
        map((fabricSubstractMaster: HttpResponse<FabricSubstractMaster>) => fabricSubstractMaster.body)
      );
    }
    return of(new FabricSubstractMaster());
  }
}

export const fabricSubstractMasterRoute: Routes = [
  {
    path: '',
    component: FabricSubstractMasterComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      defaultSort: 'id,asc',
      pageTitle: 'FabricSubstractMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: FabricSubstractMasterDetailComponent,
    resolve: {
      fabricSubstractMaster: FabricSubstractMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'FabricSubstractMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: FabricSubstractMasterUpdateComponent,
    resolve: {
      fabricSubstractMaster: FabricSubstractMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'FabricSubstractMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: FabricSubstractMasterUpdateComponent,
    resolve: {
      fabricSubstractMaster: FabricSubstractMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'FabricSubstractMasters'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const fabricSubstractMasterPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: FabricSubstractMasterDeletePopupComponent,
    resolve: {
      fabricSubstractMaster: FabricSubstractMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'FabricSubstractMasters'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
