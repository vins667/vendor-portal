import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { AssetSubTypeDetailMaster } from 'app/shared/model/asset-sub-type-detail-master.model';
import { AssetSubTypeDetailMasterService } from './asset-sub-type-detail-master.service';
import { AssetSubTypeDetailMasterComponent } from './asset-sub-type-detail-master.component';
import { AssetSubTypeDetailMasterDetailComponent } from './asset-sub-type-detail-master-detail.component';
import { AssetSubTypeDetailMasterUpdateComponent } from './asset-sub-type-detail-master-update.component';
import { AssetSubTypeDetailMasterDeletePopupComponent } from './asset-sub-type-detail-master-delete-dialog.component';
import { IAssetSubTypeDetailMaster } from 'app/shared/model/asset-sub-type-detail-master.model';

@Injectable({ providedIn: 'root' })
export class AssetSubTypeDetailMasterResolve implements Resolve<IAssetSubTypeDetailMaster> {
  constructor(private service: AssetSubTypeDetailMasterService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<AssetSubTypeDetailMaster> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<AssetSubTypeDetailMaster>) => response.ok),
        map((assetSubTypeDetailMaster: HttpResponse<AssetSubTypeDetailMaster>) => assetSubTypeDetailMaster.body)
      );
    }
    return of(new AssetSubTypeDetailMaster());
  }
}

export const assetSubTypeDetailMasterRoute: Routes = [
  {
    path: '',
    component: AssetSubTypeDetailMasterComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      defaultSort: 'id,asc',
      pageTitle: 'AssetSubTypeDetailMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: AssetSubTypeDetailMasterDetailComponent,
    resolve: {
      assetSubTypeDetailMaster: AssetSubTypeDetailMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'AssetSubTypeDetailMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: AssetSubTypeDetailMasterUpdateComponent,
    resolve: {
      assetSubTypeDetailMaster: AssetSubTypeDetailMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'AssetSubTypeDetailMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: AssetSubTypeDetailMasterUpdateComponent,
    resolve: {
      assetSubTypeDetailMaster: AssetSubTypeDetailMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'AssetSubTypeDetailMasters'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const assetSubTypeDetailMasterPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: AssetSubTypeDetailMasterDeletePopupComponent,
    resolve: {
      assetSubTypeDetailMaster: AssetSubTypeDetailMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'AssetSubTypeDetailMasters'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
