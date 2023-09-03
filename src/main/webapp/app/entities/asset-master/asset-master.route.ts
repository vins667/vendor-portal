import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { AssetMaster } from 'app/shared/model/asset-master.model';
import { AssetMasterService } from './asset-master.service';
import { AssetMasterComponent } from './asset-master.component';
import { AssetMasterDetailComponent } from './asset-master-detail.component';
import { AssetMasterUpdateComponent } from './asset-master-update.component';
import { AssetMasterDeletePopupComponent } from './asset-master-delete-dialog.component';
import { IAssetMaster } from 'app/shared/model/asset-master.model';

@Injectable({ providedIn: 'root' })
export class AssetMasterResolve implements Resolve<IAssetMaster> {
  constructor(private service: AssetMasterService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<AssetMaster> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<AssetMaster>) => response.ok),
        map((assetMaster: HttpResponse<AssetMaster>) => assetMaster.body)
      );
    }
    return of(new AssetMaster());
  }
}

export const assetMasterRoute: Routes = [
  {
    path: '',
    component: AssetMasterComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      defaultSort: 'id,asc',
      pageTitle: 'AssetMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: AssetMasterDetailComponent,
    resolve: {
      assetMaster: AssetMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'AssetMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: AssetMasterUpdateComponent,
    resolve: {
      assetMaster: AssetMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'AssetMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: AssetMasterUpdateComponent,
    resolve: {
      assetMaster: AssetMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'AssetMasters'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const assetMasterPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: AssetMasterDeletePopupComponent,
    resolve: {
      assetMaster: AssetMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'AssetMasters'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
