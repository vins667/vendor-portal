import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { AssetAuditSoftwareCompare } from 'app/shared/model/asset-audit-software-compare.model';
import { AssetAuditSoftwareCompareService } from './asset-audit-software-compare.service';
import { AssetAuditSoftwareCompareComponent } from './asset-audit-software-compare.component';
import { IAssetAuditSoftwareCompare } from 'app/shared/model/asset-audit-software-compare.model';

@Injectable({ providedIn: 'root' })
export class AssetAuditSoftwareCompareResolve implements Resolve<IAssetAuditSoftwareCompare> {
  constructor(private service: AssetAuditSoftwareCompareService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<AssetAuditSoftwareCompare> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<AssetAuditSoftwareCompare>) => response.ok),
        map((assetAuditSoftwareCompare: HttpResponse<AssetAuditSoftwareCompare>) => assetAuditSoftwareCompare.body)
      );
    }
    return of(new AssetAuditSoftwareCompare());
  }
}

export const assetAuditSoftwareCompareRoute: Routes = [
  {
    path: '',
    component: AssetAuditSoftwareCompareComponent,
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'AssetAuditSoftwareCompares'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const assetAuditSoftwareComparePopupRoute: Routes = [];
