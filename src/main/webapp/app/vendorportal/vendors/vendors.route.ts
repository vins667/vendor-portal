import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { VendorsService } from './vendors.service';
import { VendorsComponent } from './vendors.component';
import { IVendorsBean, VendorsBean } from 'app/shared/model/vendors-bean.model';
import { TabsComponent } from 'app/vendorportal/vendors/tabs.component';

@Injectable({ providedIn: 'root' })
export class VendorsResolve implements Resolve<IVendorsBean> {
  constructor(private service: VendorsService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<VendorsBean> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.profile(id).pipe(
        filter((response: HttpResponse<VendorsBean>) => response.ok),
        map((vendorsBean: HttpResponse<VendorsBean>) => vendorsBean.body)
      );
    }
    return of(new VendorsBean());
  }
}

export const vendorsRoute: Routes = [
  {
    path: '',
    component: VendorsComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      defaultSort: 'id,asc',
      pageTitle: 'Vendors'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: TabsComponent,
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'Vendors'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const vendorsPopupRoute: Routes = [];
