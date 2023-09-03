import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { FabricContentMaster } from 'app/shared/model/fabric-content-master.model';
import { FabricContentMasterService } from './fabric-content-master.service';
import { FabricContentMasterComponent } from './fabric-content-master.component';
import { FabricContentMasterDetailComponent } from './fabric-content-master-detail.component';
import { FabricContentMasterUpdateComponent } from './fabric-content-master-update.component';
import { FabricContentMasterDeletePopupComponent } from './fabric-content-master-delete-dialog.component';
import { IFabricContentMaster } from 'app/shared/model/fabric-content-master.model';

@Injectable({ providedIn: 'root' })
export class FabricContentMasterResolve implements Resolve<IFabricContentMaster> {
  constructor(private service: FabricContentMasterService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IFabricContentMaster> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<FabricContentMaster>) => response.ok),
        map((fabricContentMaster: HttpResponse<FabricContentMaster>) => fabricContentMaster.body)
      );
    }
    return of(new FabricContentMaster());
  }
}

export const fabricContentMasterRoute: Routes = [
  {
    path: '',
    component: FabricContentMasterComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      defaultSort: 'id,asc',
      pageTitle: 'FabricContentMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: FabricContentMasterDetailComponent,
    resolve: {
      fabricContentMaster: FabricContentMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'FabricContentMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: FabricContentMasterUpdateComponent,
    resolve: {
      fabricContentMaster: FabricContentMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'FabricContentMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: FabricContentMasterUpdateComponent,
    resolve: {
      fabricContentMaster: FabricContentMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'FabricContentMasters'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const fabricContentMasterPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: FabricContentMasterDeletePopupComponent,
    resolve: {
      fabricContentMaster: FabricContentMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'FabricContentMasters'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
