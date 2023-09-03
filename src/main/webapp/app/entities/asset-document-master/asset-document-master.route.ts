import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { AssetDocumentMaster } from 'app/shared/model/asset-document-master.model';
import { AssetDocumentMasterService } from './asset-document-master.service';
import { AssetDocumentMasterComponent } from './asset-document-master.component';
import { AssetDocumentMasterDetailComponent } from './asset-document-master-detail.component';
import { AssetDocumentMasterUpdateComponent } from './asset-document-master-update.component';
import { AssetDocumentMasterDeletePopupComponent } from './asset-document-master-delete-dialog.component';
import { IAssetDocumentMaster } from 'app/shared/model/asset-document-master.model';

@Injectable({ providedIn: 'root' })
export class AssetDocumentMasterResolve implements Resolve<IAssetDocumentMaster> {
  constructor(private service: AssetDocumentMasterService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<AssetDocumentMaster> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<AssetDocumentMaster>) => response.ok),
        map((assetDocumentMaster: HttpResponse<AssetDocumentMaster>) => assetDocumentMaster.body)
      );
    }
    return of(new AssetDocumentMaster());
  }
}

export const assetDocumentMasterRoute: Routes = [
  {
    path: '',
    component: AssetDocumentMasterComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      defaultSort: 'id,asc',
      pageTitle: 'AssetDocumentMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: AssetDocumentMasterDetailComponent,
    resolve: {
      assetDocumentMaster: AssetDocumentMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'AssetDocumentMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: AssetDocumentMasterUpdateComponent,
    resolve: {
      assetDocumentMaster: AssetDocumentMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'AssetDocumentMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: AssetDocumentMasterUpdateComponent,
    resolve: {
      assetDocumentMaster: AssetDocumentMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'AssetDocumentMasters'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const assetDocumentMasterPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: AssetDocumentMasterDeletePopupComponent,
    resolve: {
      assetDocumentMaster: AssetDocumentMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'AssetDocumentMasters'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
