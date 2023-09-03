import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { AssetSupplierMaster } from 'app/shared/model/asset-supplier-master.model';
import { AssetSupplierMasterService } from './asset-supplier-master.service';
import { AssetSupplierMasterComponent } from './asset-supplier-master.component';
import { AssetSupplierMasterDetailComponent } from './asset-supplier-master-detail.component';
import { AssetSupplierMasterUpdateComponent } from './asset-supplier-master-update.component';
import { AssetSupplierMasterDeletePopupComponent } from './asset-supplier-master-delete-dialog.component';
import { IAssetSupplierMaster } from 'app/shared/model/asset-supplier-master.model';

@Injectable({ providedIn: 'root' })
export class AssetSupplierMasterResolve implements Resolve<IAssetSupplierMaster> {
  constructor(private service: AssetSupplierMasterService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<AssetSupplierMaster> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<AssetSupplierMaster>) => response.ok),
        map((assetSupplierMaster: HttpResponse<AssetSupplierMaster>) => assetSupplierMaster.body)
      );
    }
    return of(new AssetSupplierMaster());
  }
}

export const assetSupplierMasterRoute: Routes = [
  {
    path: '',
    component: AssetSupplierMasterComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      defaultSort: 'id,asc',
      pageTitle: 'AssetSupplierMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: AssetSupplierMasterDetailComponent,
    resolve: {
      assetSupplierMaster: AssetSupplierMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'AssetSupplierMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: AssetSupplierMasterUpdateComponent,
    resolve: {
      assetSupplierMaster: AssetSupplierMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'AssetSupplierMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: AssetSupplierMasterUpdateComponent,
    resolve: {
      assetSupplierMaster: AssetSupplierMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'AssetSupplierMasters'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const assetSupplierMasterPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: AssetSupplierMasterDeletePopupComponent,
    resolve: {
      assetSupplierMaster: AssetSupplierMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'AssetSupplierMasters'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
