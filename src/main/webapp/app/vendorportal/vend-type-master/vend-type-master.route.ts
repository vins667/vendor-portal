import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { VendTypeMaster } from 'app/shared/model/vend-type-master.model';
import { VendTypeMasterService } from './vend-type-master.service';
import { VendTypeMasterComponent } from './vend-type-master.component';
import { VendTypeMasterDetailComponent } from './vend-type-master-detail.component';
import { VendTypeMasterUpdateComponent } from './vend-type-master-update.component';
import { VendTypeMasterDeletePopupComponent } from './vend-type-master-delete-dialog.component';
import { IVendTypeMaster } from 'app/shared/model/vend-type-master.model';

@Injectable({ providedIn: 'root' })
export class VendTypeMasterResolve implements Resolve<IVendTypeMaster> {
  constructor(private service: VendTypeMasterService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<VendTypeMaster> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<VendTypeMaster>) => response.ok),
        map((vendTypeMaster: HttpResponse<VendTypeMaster>) => vendTypeMaster.body)
      );
    }
    return of(new VendTypeMaster());
  }
}

export const vendTypeMasterRoute: Routes = [
  {
    path: '',
    component: VendTypeMasterComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      defaultSort: 'id,asc',
      pageTitle: 'VendTypeMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: VendTypeMasterDetailComponent,
    resolve: {
      vendTypeMaster: VendTypeMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'VendTypeMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: VendTypeMasterUpdateComponent,
    resolve: {
      vendTypeMaster: VendTypeMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'VendTypeMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: VendTypeMasterUpdateComponent,
    resolve: {
      vendTypeMaster: VendTypeMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'VendTypeMasters'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const vendTypeMasterPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: VendTypeMasterDeletePopupComponent,
    resolve: {
      vendTypeMaster: VendTypeMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'VendTypeMasters'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
