import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { CostingFabricItemDetails } from 'app/shared/model/costing-fabric-item-details.model';
import { CostingFabricItemDetailsService } from './costing-fabric-item-details.service';
import { CostingFabricItemDetailsComponent } from './costing-fabric-item-details.component';
import { CostingFabricItemDetailsDetailComponent } from './costing-fabric-item-details-detail.component';
import { CostingFabricItemDetailsUpdateComponent } from './costing-fabric-item-details-update.component';
import { CostingFabricItemDetailsDeletePopupComponent } from './costing-fabric-item-details-delete-dialog.component';
import { ICostingFabricItemDetails } from 'app/shared/model/costing-fabric-item-details.model';

@Injectable({ providedIn: 'root' })
export class CostingFabricItemDetailsResolve implements Resolve<ICostingFabricItemDetails> {
  constructor(private service: CostingFabricItemDetailsService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ICostingFabricItemDetails> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<CostingFabricItemDetails>) => response.ok),
        map((costingFabricItemDetails: HttpResponse<CostingFabricItemDetails>) => costingFabricItemDetails.body)
      );
    }
    return of(new CostingFabricItemDetails());
  }
}

export const costingFabricItemDetailsRoute: Routes = [
  {
    path: '',
    component: CostingFabricItemDetailsComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      defaultSort: 'id,asc',
      pageTitle: 'Costing Fabric Item Details'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: CostingFabricItemDetailsDetailComponent,
    resolve: {
      costingFabricItemDetails: CostingFabricItemDetailsResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'Costing Fabric Item Details'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: CostingFabricItemDetailsUpdateComponent,
    resolve: {
      costingFabricItemDetails: CostingFabricItemDetailsResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'Costing Fabric Item Details'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: CostingFabricItemDetailsUpdateComponent,
    resolve: {
      costingFabricItemDetails: CostingFabricItemDetailsResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'Costing Fabric Item Details'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const costingFabricItemDetailsPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: CostingFabricItemDetailsDeletePopupComponent,
    resolve: {
      costingFabricItemDetails: CostingFabricItemDetailsResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'Costing Fabric Item Details'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
