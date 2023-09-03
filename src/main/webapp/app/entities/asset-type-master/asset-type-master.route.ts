import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { AssetTypeMaster } from 'app/shared/model/asset-type-master.model';
import { AssetTypeMasterService } from './asset-type-master.service';
import { AssetTypeMasterComponent } from './asset-type-master.component';
import { AssetTypeMasterDetailComponent } from './asset-type-master-detail.component';
import { AssetTypeMasterUpdateComponent } from './asset-type-master-update.component';
import { AssetTypeMasterDeletePopupComponent } from './asset-type-master-delete-dialog.component';
import { IAssetTypeMaster } from 'app/shared/model/asset-type-master.model';

@Injectable({ providedIn: 'root' })
export class AssetTypeMasterResolve implements Resolve<IAssetTypeMaster> {
  constructor(private service: AssetTypeMasterService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<AssetTypeMaster> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<AssetTypeMaster>) => response.ok),
        map((assetTypeMaster: HttpResponse<AssetTypeMaster>) => assetTypeMaster.body)
      );
    }
    return of(new AssetTypeMaster());
  }
}

export const assetTypeMasterRoute: Routes = [
  {
    path: '',
    component: AssetTypeMasterComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      defaultSort: 'id,asc',
      pageTitle: 'AssetTypeMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: AssetTypeMasterDetailComponent,
    resolve: {
      assetTypeMaster: AssetTypeMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'AssetTypeMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: AssetTypeMasterUpdateComponent,
    resolve: {
      assetTypeMaster: AssetTypeMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'AssetTypeMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: AssetTypeMasterUpdateComponent,
    resolve: {
      assetTypeMaster: AssetTypeMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'AssetTypeMasters'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const assetTypeMasterPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: AssetTypeMasterDeletePopupComponent,
    resolve: {
      assetTypeMaster: AssetTypeMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'AssetTypeMasters'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
