import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { AssetAuditDetails } from 'app/shared/model/asset-audit-details.model';
import { AssetAuditDetailsService } from './asset-audit-details.service';
import { AssetAuditDetailsComponent } from './asset-audit-details.component';
import { AssetAuditDetailsDetailComponent } from './asset-audit-details-detail.component';
import { AssetAuditDetailsUpdateComponent } from './asset-audit-details-update.component';
import { AssetAuditDetailsDeletePopupComponent } from './asset-audit-details-delete-dialog.component';
import { IAssetAuditDetails } from 'app/shared/model/asset-audit-details.model';

@Injectable({ providedIn: 'root' })
export class AssetAuditDetailsResolve implements Resolve<IAssetAuditDetails> {
  constructor(private service: AssetAuditDetailsService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<AssetAuditDetails> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<AssetAuditDetails>) => response.ok),
        map((assetAuditDetails: HttpResponse<AssetAuditDetails>) => assetAuditDetails.body)
      );
    }
    return of(new AssetAuditDetails());
  }
}

export const assetAuditDetailsRoute: Routes = [
  {
    path: '',
    component: AssetAuditDetailsComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      defaultSort: 'id,asc',
      pageTitle: 'AssetAuditDetails'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: AssetAuditDetailsDetailComponent,
    resolve: {
      assetAuditDetails: AssetAuditDetailsResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'AssetAuditDetails'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: AssetAuditDetailsUpdateComponent,
    resolve: {
      assetAuditDetails: AssetAuditDetailsResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'AssetAuditDetails'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: AssetAuditDetailsUpdateComponent,
    resolve: {
      assetAuditDetails: AssetAuditDetailsResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'AssetAuditDetails'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const assetAuditDetailsPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: AssetAuditDetailsDeletePopupComponent,
    resolve: {
      assetAuditDetails: AssetAuditDetailsResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'AssetAuditDetails'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
