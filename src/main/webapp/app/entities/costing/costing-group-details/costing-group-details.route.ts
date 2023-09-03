import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { CostingGroupDetails } from 'app/shared/model/costing-group-details.model';
import { CostingGroupDetailsService } from './costing-group-details.service';
import { CostingGroupDetailsComponent } from './costing-group-details.component';
import { CostingGroupDetailsDetailComponent } from './costing-group-details-detail.component';
import { CostingGroupDetailsUpdateComponent } from './costing-group-details-update.component';
import { CostingGroupDetailsDeletePopupComponent } from './costing-group-details-delete-dialog.component';
import { ICostingGroupDetails } from 'app/shared/model/costing-group-details.model';

@Injectable({ providedIn: 'root' })
export class CostingGroupDetailsResolve implements Resolve<ICostingGroupDetails> {
  constructor(private service: CostingGroupDetailsService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ICostingGroupDetails> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<CostingGroupDetails>) => response.ok),
        map((costingGroupDetails: HttpResponse<CostingGroupDetails>) => costingGroupDetails.body)
      );
    }
    return of(new CostingGroupDetails());
  }
}

export const costingGroupDetailsRoute: Routes = [
  {
    path: '',
    component: CostingGroupDetailsComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      defaultSort: 'id,asc',
      pageTitle: 'Costing Group Details'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: CostingGroupDetailsDetailComponent,
    resolve: {
      costingGroupDetails: CostingGroupDetailsResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'Costing Group Details'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: CostingGroupDetailsUpdateComponent,
    resolve: {
      costingGroupDetails: CostingGroupDetailsResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'Costing Group Details'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: CostingGroupDetailsUpdateComponent,
    resolve: {
      costingGroupDetails: CostingGroupDetailsResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'Costing Group Details'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const costingGroupDetailsPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: CostingGroupDetailsDeletePopupComponent,
    resolve: {
      costingGroupDetails: CostingGroupDetailsResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'Costing Group Details'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
