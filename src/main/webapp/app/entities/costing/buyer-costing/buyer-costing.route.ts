import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { BuyerCosting } from 'app/shared/model/buyer-costing.model';
import { BuyerCostingService } from './buyer-costing.service';
import { BuyerCostingComponent } from './buyer-costing.component';
import { BuyerCostingDetailComponent } from './buyer-costing-detail.component';
import { BuyerCostingUpdateComponent } from './buyer-costing-update.component';
import { BuyerCostingDeletePopupComponent } from './buyer-costing-delete-dialog.component';
import { IBuyerCosting } from 'app/shared/model/buyer-costing.model';

@Injectable({ providedIn: 'root' })
export class BuyerCostingResolve implements Resolve<IBuyerCosting> {
  constructor(private service: BuyerCostingService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IBuyerCosting> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<BuyerCosting>) => response.ok),
        map((buyerCosting: HttpResponse<BuyerCosting>) => buyerCosting.body)
      );
    }
    return of(new BuyerCosting());
  }
}

export const buyerCostingRoute: Routes = [
  {
    path: '',
    component: BuyerCostingComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      defaultSort: 'id,asc',
      pageTitle: 'BuyerCostings'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: BuyerCostingDetailComponent,
    resolve: {
      buyerCosting: BuyerCostingResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'BuyerCostings'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: BuyerCostingUpdateComponent,
    resolve: {
      buyerCosting: BuyerCostingResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'BuyerCostings'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: BuyerCostingUpdateComponent,
    resolve: {
      buyerCosting: BuyerCostingResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'BuyerCostings'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const buyerCostingPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: BuyerCostingDeletePopupComponent,
    resolve: {
      buyerCosting: BuyerCostingResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'BuyerCostings'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
