import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { AssetSoftTypeMaster } from 'app/shared/model/asset-soft-type-master.model';
import { AssetSoftTypeMasterService } from './asset-soft-type-master.service';
import { AssetSoftTypeMasterComponent } from './asset-soft-type-master.component';
import { AssetSoftTypeMasterDetailComponent } from './asset-soft-type-master-detail.component';
import { AssetSoftTypeMasterUpdateComponent } from './asset-soft-type-master-update.component';
import { AssetSoftTypeMasterDeletePopupComponent } from './asset-soft-type-master-delete-dialog.component';
import { IAssetSoftTypeMaster } from 'app/shared/model/asset-soft-type-master.model';

@Injectable({ providedIn: 'root' })
export class AssetSoftTypeMasterResolve implements Resolve<IAssetSoftTypeMaster> {
  constructor(private service: AssetSoftTypeMasterService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<AssetSoftTypeMaster> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<AssetSoftTypeMaster>) => response.ok),
        map((assetSoftTypeMaster: HttpResponse<AssetSoftTypeMaster>) => assetSoftTypeMaster.body)
      );
    }
    return of(new AssetSoftTypeMaster());
  }
}

export const assetSoftTypeMasterRoute: Routes = [
  {
    path: '',
    component: AssetSoftTypeMasterComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      defaultSort: 'id,asc',
      pageTitle: 'AssetSoftTypeMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: AssetSoftTypeMasterDetailComponent,
    resolve: {
      assetSoftTypeMaster: AssetSoftTypeMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'AssetSoftTypeMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: AssetSoftTypeMasterUpdateComponent,
    resolve: {
      assetSoftTypeMaster: AssetSoftTypeMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'AssetSoftTypeMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: AssetSoftTypeMasterUpdateComponent,
    resolve: {
      assetSoftTypeMaster: AssetSoftTypeMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'AssetSoftTypeMasters'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const assetSoftTypeMasterPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: AssetSoftTypeMasterDeletePopupComponent,
    resolve: {
      assetSoftTypeMaster: AssetSoftTypeMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'AssetSoftTypeMasters'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
