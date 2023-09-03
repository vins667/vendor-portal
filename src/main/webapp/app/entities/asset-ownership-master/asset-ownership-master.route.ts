import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { AssetOwnershipMaster } from 'app/shared/model/asset-ownership-master.model';
import { AssetOwnershipMasterService } from './asset-ownership-master.service';
import { AssetOwnershipMasterComponent } from './asset-ownership-master.component';
import { AssetOwnershipMasterDetailComponent } from './asset-ownership-master-detail.component';
import { AssetOwnershipMasterUpdateComponent } from './asset-ownership-master-update.component';
import { AssetOwnershipMasterDeletePopupComponent } from './asset-ownership-master-delete-dialog.component';
import { IAssetOwnershipMaster } from 'app/shared/model/asset-ownership-master.model';

@Injectable({ providedIn: 'root' })
export class AssetOwnershipMasterResolve implements Resolve<IAssetOwnershipMaster> {
  constructor(private service: AssetOwnershipMasterService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<AssetOwnershipMaster> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<AssetOwnershipMaster>) => response.ok),
        map((assetOwnershipMaster: HttpResponse<AssetOwnershipMaster>) => assetOwnershipMaster.body)
      );
    }
    return of(new AssetOwnershipMaster());
  }
}

export const assetOwnershipMasterRoute: Routes = [
  {
    path: '',
    component: AssetOwnershipMasterComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      defaultSort: 'id,asc',
      pageTitle: 'AssetOwnershipMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: AssetOwnershipMasterDetailComponent,
    resolve: {
      assetOwnershipMaster: AssetOwnershipMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'AssetOwnershipMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: AssetOwnershipMasterUpdateComponent,
    resolve: {
      assetOwnershipMaster: AssetOwnershipMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'AssetOwnershipMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: AssetOwnershipMasterUpdateComponent,
    resolve: {
      assetOwnershipMaster: AssetOwnershipMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'AssetOwnershipMasters'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const assetOwnershipMasterPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: AssetOwnershipMasterDeletePopupComponent,
    resolve: {
      assetOwnershipMaster: AssetOwnershipMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'AssetOwnershipMasters'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
