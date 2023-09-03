import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { DeliveryTermMaster } from 'app/shared/model/delivery-term-master.model';
import { DeliveryTermMasterService } from './delivery-term-master.service';
import { DeliveryTermMasterComponent } from './delivery-term-master.component';
import { DeliveryTermMasterDetailComponent } from './delivery-term-master-detail.component';
import { DeliveryTermMasterUpdateComponent } from './delivery-term-master-update.component';
import { DeliveryTermMasterDeletePopupComponent } from './delivery-term-master-delete-dialog.component';
import { IDeliveryTermMaster } from 'app/shared/model/delivery-term-master.model';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';

@Injectable({ providedIn: 'root' })
export class DeliveryTermMasterResolve implements Resolve<IDeliveryTermMaster> {
  constructor(private service: DeliveryTermMasterService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IDeliveryTermMaster> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<DeliveryTermMaster>) => response.ok),
        map((deliveryTermMaster: HttpResponse<DeliveryTermMaster>) => deliveryTermMaster.body)
      );
    }
    return of(new DeliveryTermMaster());
  }
}

export const deliveryTermMasterRoute: Routes = [
  {
    path: '',
    component: DeliveryTermMasterComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_ADMIN', 'ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'DeliveryTermMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: DeliveryTermMasterDetailComponent,
    resolve: {
      deliveryTermMaster: DeliveryTermMasterResolve
    },
    data: {
      authorities: ['ROLE_ADMIN', 'ROLE_USER'],
      pageTitle: 'DeliveryTermMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: DeliveryTermMasterUpdateComponent,
    resolve: {
      deliveryTermMaster: DeliveryTermMasterResolve
    },
    data: {
      authorities: ['ROLE_ADMIN', 'ROLE_USER'],
      pageTitle: 'DeliveryTermMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: DeliveryTermMasterUpdateComponent,
    resolve: {
      deliveryTermMaster: DeliveryTermMasterResolve
    },
    data: {
      authorities: ['ROLE_ADMIN', 'ROLE_USER'],
      pageTitle: 'DeliveryTermMasters'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const deliveryTermMasterPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: DeliveryTermMasterDeletePopupComponent,
    resolve: {
      deliveryTermMaster: DeliveryTermMasterResolve
    },
    data: {
      authorities: ['ROLE_ADMIN', 'ROLE_USER'],
      pageTitle: 'DeliveryTermMasters'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
