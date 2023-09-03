import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { AssetCompanyMaster } from 'app/shared/model/asset-company-master.model';
import { AssetCompanyMasterService } from './asset-company-master.service';
import { AssetCompanyMasterComponent } from './asset-company-master.component';
import { AssetCompanyMasterDetailComponent } from './asset-company-master-detail.component';
import { AssetCompanyMasterUpdateComponent } from './asset-company-master-update.component';
import { AssetCompanyMasterDeletePopupComponent } from './asset-company-master-delete-dialog.component';
import { IAssetCompanyMaster } from 'app/shared/model/asset-company-master.model';

@Injectable({ providedIn: 'root' })
export class AssetCompanyMasterResolve implements Resolve<IAssetCompanyMaster> {
  constructor(private service: AssetCompanyMasterService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<AssetCompanyMaster> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<AssetCompanyMaster>) => response.ok),
        map((assetCompanyMaster: HttpResponse<AssetCompanyMaster>) => assetCompanyMaster.body)
      );
    }
    return of(new AssetCompanyMaster());
  }
}

export const assetCompanyMasterRoute: Routes = [
  {
    path: '',
    component: AssetCompanyMasterComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      defaultSort: 'id,asc',
      pageTitle: 'AssetCompanyMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: AssetCompanyMasterDetailComponent,
    resolve: {
      assetCompanyMaster: AssetCompanyMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'AssetCompanyMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: AssetCompanyMasterUpdateComponent,
    resolve: {
      assetCompanyMaster: AssetCompanyMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'AssetCompanyMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: AssetCompanyMasterUpdateComponent,
    resolve: {
      assetCompanyMaster: AssetCompanyMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'AssetCompanyMasters'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const assetCompanyMasterPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: AssetCompanyMasterDeletePopupComponent,
    resolve: {
      assetCompanyMaster: AssetCompanyMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'AssetCompanyMasters'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
