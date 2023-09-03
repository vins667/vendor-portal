import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { AssetLocationMaster } from 'app/shared/model/asset-location-master.model';
import { AssetLocationMasterService } from './asset-location-master.service';
import { AssetLocationMasterComponent } from './asset-location-master.component';
import { AssetLocationMasterDetailComponent } from './asset-location-master-detail.component';
import { AssetLocationMasterUpdateComponent } from './asset-location-master-update.component';
import { AssetLocationMasterDeletePopupComponent } from './asset-location-master-delete-dialog.component';
import { IAssetLocationMaster } from 'app/shared/model/asset-location-master.model';

@Injectable({ providedIn: 'root' })
export class AssetLocationMasterResolve implements Resolve<IAssetLocationMaster> {
  constructor(private service: AssetLocationMasterService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<AssetLocationMaster> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<AssetLocationMaster>) => response.ok),
        map((assetLocationMaster: HttpResponse<AssetLocationMaster>) => assetLocationMaster.body)
      );
    }
    return of(new AssetLocationMaster());
  }
}

export const assetLocationMasterRoute: Routes = [
  {
    path: '',
    component: AssetLocationMasterComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      defaultSort: 'id,asc',
      pageTitle: 'AssetLocationMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: AssetLocationMasterDetailComponent,
    resolve: {
      assetLocationMaster: AssetLocationMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'AssetLocationMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: AssetLocationMasterUpdateComponent,
    resolve: {
      assetLocationMaster: AssetLocationMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'AssetLocationMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: AssetLocationMasterUpdateComponent,
    resolve: {
      assetLocationMaster: AssetLocationMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'AssetLocationMasters'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const assetLocationMasterPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: AssetLocationMasterDeletePopupComponent,
    resolve: {
      assetLocationMaster: AssetLocationMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'AssetLocationMasters'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
