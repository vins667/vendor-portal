import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { DeliveryChallan } from 'app/shared/model/delivery-challan.model';
import { DeliveryChallanService } from './delivery-challan.service';
import { DeliveryChallanComponent } from './delivery-challan.component';
import { DeliveryChallanDetailComponent } from './delivery-challan-detail.component';
import { DeliveryChallanUpdateComponent } from './delivery-challan-update.component';
import { DeliveryChallanDeletePopupComponent } from './delivery-challan-delete-dialog.component';
import { IDeliveryChallan } from 'app/shared/model/delivery-challan.model';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';

@Injectable({ providedIn: 'root' })
export class DeliveryChallanResolve implements Resolve<IDeliveryChallan> {
  constructor(private service: DeliveryChallanService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<DeliveryChallan> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<DeliveryChallan>) => response.ok),
        map((deliveryChallan: HttpResponse<DeliveryChallan>) => deliveryChallan.body)
      );
    }
    return of(new DeliveryChallan());
  }
}

export const deliveryChallanRoute: Routes = [
  {
    path: '',
    component: DeliveryChallanComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER', 'ADMIN_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'DeliveryChallan'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: DeliveryChallanDetailComponent,
    resolve: {
      deliveryChallan: DeliveryChallanResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ADMIN_USER'],
      pageTitle: 'DeliveryChallan'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: DeliveryChallanUpdateComponent,
    resolve: {
      deliveryChallan: DeliveryChallanResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ADMIN_USER'],
      pageTitle: 'DeliveryChallan'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: DeliveryChallanUpdateComponent,
    resolve: {
      deliveryChallan: DeliveryChallanResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ADMIN_USER'],
      pageTitle: 'DeliveryChallan'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const deliveryChallanPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: DeliveryChallanDeletePopupComponent,
    resolve: {
      deliveryChallan: DeliveryChallanResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ADMIN_USER'],
      pageTitle: 'DeliveryChallan'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
