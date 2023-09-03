import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { FabricUomMaster } from 'app/shared/model/fabric-uom-master.model';
import { FabricUomMasterService } from './fabric-uom-master.service';
import { FabricUomMasterComponent } from './fabric-uom-master.component';
import { FabricUomMasterDetailComponent } from './fabric-uom-master-detail.component';
import { FabricUomMasterUpdateComponent } from './fabric-uom-master-update.component';
import { FabricUomMasterDeletePopupComponent } from './fabric-uom-master-delete-dialog.component';
import { IFabricUomMaster } from 'app/shared/model/fabric-uom-master.model';

@Injectable({ providedIn: 'root' })
export class FabricUomMasterResolve implements Resolve<IFabricUomMaster> {
  constructor(private service: FabricUomMasterService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IFabricUomMaster> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<FabricUomMaster>) => response.ok),
        map((fabricUomMaster: HttpResponse<FabricUomMaster>) => fabricUomMaster.body)
      );
    }
    return of(new FabricUomMaster());
  }
}

export const fabricUomMasterRoute: Routes = [
  {
    path: '',
    component: FabricUomMasterComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      defaultSort: 'id,asc',
      pageTitle: 'FabricUomMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: FabricUomMasterDetailComponent,
    resolve: {
      fabricUomMaster: FabricUomMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'FabricUomMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: FabricUomMasterUpdateComponent,
    resolve: {
      fabricUomMaster: FabricUomMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'FabricUomMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: FabricUomMasterUpdateComponent,
    resolve: {
      fabricUomMaster: FabricUomMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'FabricUomMasters'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const fabricUomMasterPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: FabricUomMasterDeletePopupComponent,
    resolve: {
      fabricUomMaster: FabricUomMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'FabricUomMasters'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
