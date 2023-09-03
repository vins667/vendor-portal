import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { AssetSubTypeMaster } from 'app/shared/model/asset-sub-type-master.model';
import { AssetSubTypeMasterService } from './asset-sub-type-master.service';
import { AssetSubTypeMasterComponent } from './asset-sub-type-master.component';
import { AssetSubTypeMasterDetailComponent } from './asset-sub-type-master-detail.component';
import { AssetSubTypeMasterUpdateComponent } from './asset-sub-type-master-update.component';
import { AssetSubTypeMasterDeletePopupComponent } from './asset-sub-type-master-delete-dialog.component';
import { IAssetSubTypeMaster } from 'app/shared/model/asset-sub-type-master.model';

@Injectable({ providedIn: 'root' })
export class AssetSubTypeMasterResolve implements Resolve<IAssetSubTypeMaster> {
  constructor(private service: AssetSubTypeMasterService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<AssetSubTypeMaster> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<AssetSubTypeMaster>) => response.ok),
        map((assetSubTypeMaster: HttpResponse<AssetSubTypeMaster>) => assetSubTypeMaster.body)
      );
    }
    return of(new AssetSubTypeMaster());
  }
}

export const assetSubTypeMasterRoute: Routes = [
  {
    path: '',
    component: AssetSubTypeMasterComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      defaultSort: 'id,asc',
      pageTitle: 'AssetSubTypeMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: AssetSubTypeMasterDetailComponent,
    resolve: {
      assetSubTypeMaster: AssetSubTypeMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'AssetSubTypeMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: AssetSubTypeMasterUpdateComponent,
    resolve: {
      assetSubTypeMaster: AssetSubTypeMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'AssetSubTypeMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: AssetSubTypeMasterUpdateComponent,
    resolve: {
      assetSubTypeMaster: AssetSubTypeMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'AssetSubTypeMasters'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const assetSubTypeMasterPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: AssetSubTypeMasterDeletePopupComponent,
    resolve: {
      assetSubTypeMaster: AssetSubTypeMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'AssetSubTypeMasters'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
