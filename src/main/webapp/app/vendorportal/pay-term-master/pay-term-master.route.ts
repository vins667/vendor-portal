import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { PayTermMaster } from 'app/shared/model/pay-term-master.model';
import { PayTermMasterService } from './pay-term-master.service';
import { PayTermMasterComponent } from './pay-term-master.component';
import { PayTermMasterDetailComponent } from './pay-term-master-detail.component';
import { PayTermMasterUpdateComponent } from './pay-term-master-update.component';
import { PayTermMasterDeletePopupComponent } from './pay-term-master-delete-dialog.component';
import { IPayTermMaster } from 'app/shared/model/pay-term-master.model';

@Injectable({ providedIn: 'root' })
export class PayTermMasterResolve implements Resolve<IPayTermMaster> {
  constructor(private service: PayTermMasterService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IPayTermMaster> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<PayTermMaster>) => response.ok),
        map((payTermMaster: HttpResponse<PayTermMaster>) => payTermMaster.body)
      );
    }
    return of(new PayTermMaster());
  }
}

export const payTermMasterRoute: Routes = [
  {
    path: '',
    component: PayTermMasterComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_ADMIN', 'ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'PayTermMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: PayTermMasterDetailComponent,
    resolve: {
      payTermMaster: PayTermMasterResolve
    },
    data: {
      authorities: ['ROLE_ADMIN', 'ROLE_USER'],
      pageTitle: 'PayTermMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: PayTermMasterUpdateComponent,
    resolve: {
      payTermMaster: PayTermMasterResolve
    },
    data: {
      authorities: ['ROLE_ADMIN', 'ROLE_USER'],
      pageTitle: 'PayTermMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: PayTermMasterUpdateComponent,
    resolve: {
      payTermMaster: PayTermMasterResolve
    },
    data: {
      authorities: ['ROLE_ADMIN', 'ROLE_USER'],
      pageTitle: 'PayTermMasters'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const payTermMasterPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: PayTermMasterDeletePopupComponent,
    resolve: {
      payTermMaster: PayTermMasterResolve
    },
    data: {
      authorities: ['ROLE_ADMIN', 'ROLE_USER'],
      pageTitle: 'PayTermMasters'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
