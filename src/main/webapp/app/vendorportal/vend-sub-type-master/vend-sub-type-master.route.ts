import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { VendSubTypeMaster } from 'app/shared/model/vend-sub-type-master.model';
import { VendSubTypeMasterService } from './vend-sub-type-master.service';
import { VendSubTypeMasterComponent } from './vend-sub-type-master.component';
import { VendSubTypeMasterDetailComponent } from './vend-sub-type-master-detail.component';
import { VendSubTypeMasterUpdateComponent } from './vend-sub-type-master-update.component';
import { VendSubTypeMasterDeletePopupComponent } from './vend-sub-type-master-delete-dialog.component';
import { IVendSubTypeMaster } from 'app/shared/model/vend-sub-type-master.model';

@Injectable({ providedIn: 'root' })
export class VendSubTypeMasterResolve implements Resolve<IVendSubTypeMaster> {
  constructor(private service: VendSubTypeMasterService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<VendSubTypeMaster> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<VendSubTypeMaster>) => response.ok),
        map((vendSubTypeMaster: HttpResponse<VendSubTypeMaster>) => vendSubTypeMaster.body)
      );
    }
    return of(new VendSubTypeMaster());
  }
}

export const vendSubTypeMasterRoute: Routes = [
  {
    path: '',
    component: VendSubTypeMasterComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      defaultSort: 'id,asc',
      pageTitle: 'VendSubTypeMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: VendSubTypeMasterDetailComponent,
    resolve: {
      vendSubTypeMaster: VendSubTypeMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'VendSubTypeMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: VendSubTypeMasterUpdateComponent,
    resolve: {
      vendSubTypeMaster: VendSubTypeMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'VendSubTypeMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: VendSubTypeMasterUpdateComponent,
    resolve: {
      vendSubTypeMaster: VendSubTypeMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'VendSubTypeMasters'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const vendSubTypeMasterPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: VendSubTypeMasterDeletePopupComponent,
    resolve: {
      vendSubTypeMaster: VendSubTypeMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'VendSubTypeMasters'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
