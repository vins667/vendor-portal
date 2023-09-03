import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { AssetWarrantyTypeMaster } from 'app/shared/model/asset-warranty-type-master.model';
import { AssetWarrantyTypeMasterService } from './asset-warranty-type-master.service';
import { AssetWarrantyTypeMasterComponent } from './asset-warranty-type-master.component';
import { AssetWarrantyTypeMasterDetailComponent } from './asset-warranty-type-master-detail.component';
import { AssetWarrantyTypeMasterUpdateComponent } from './asset-warranty-type-master-update.component';
import { AssetWarrantyTypeMasterDeletePopupComponent } from './asset-warranty-type-master-delete-dialog.component';
import { IAssetWarrantyTypeMaster } from 'app/shared/model/asset-warranty-type-master.model';

@Injectable({ providedIn: 'root' })
export class AssetWarrantyTypeMasterResolve implements Resolve<IAssetWarrantyTypeMaster> {
  constructor(private service: AssetWarrantyTypeMasterService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<AssetWarrantyTypeMaster> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<AssetWarrantyTypeMaster>) => response.ok),
        map((assetWarrantyTypeMaster: HttpResponse<AssetWarrantyTypeMaster>) => assetWarrantyTypeMaster.body)
      );
    }
    return of(new AssetWarrantyTypeMaster());
  }
}

export const assetWarrantyTypeMasterRoute: Routes = [
  {
    path: '',
    component: AssetWarrantyTypeMasterComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      defaultSort: 'id,asc',
      pageTitle: 'AssetWarrantyTypeMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: AssetWarrantyTypeMasterDetailComponent,
    resolve: {
      assetWarrantyTypeMaster: AssetWarrantyTypeMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'AssetWarrantyTypeMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: AssetWarrantyTypeMasterUpdateComponent,
    resolve: {
      assetWarrantyTypeMaster: AssetWarrantyTypeMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'AssetWarrantyTypeMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: AssetWarrantyTypeMasterUpdateComponent,
    resolve: {
      assetWarrantyTypeMaster: AssetWarrantyTypeMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'AssetWarrantyTypeMasters'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const assetWarrantyTypeMasterPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: AssetWarrantyTypeMasterDeletePopupComponent,
    resolve: {
      assetWarrantyTypeMaster: AssetWarrantyTypeMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'AssetWarrantyTypeMasters'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
