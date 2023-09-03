import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { CostingValueMaster } from 'app/shared/model/costing-value-master.model';
import { CostingValueMasterService } from './costing-value-master.service';
import { CostingValueMasterComponent } from './costing-value-master.component';
import { CostingValueMasterDetailComponent } from './costing-value-master-detail.component';
import { CostingValueMasterUpdateComponent } from './costing-value-master-update.component';
import { CostingValueMasterDeletePopupComponent } from './costing-value-master-delete-dialog.component';
import { ICostingValueMaster } from 'app/shared/model/costing-value-master.model';

@Injectable({ providedIn: 'root' })
export class CostingValueMasterResolve implements Resolve<ICostingValueMaster> {
  constructor(private service: CostingValueMasterService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ICostingValueMaster> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<CostingValueMaster>) => response.ok),
        map((costingValueMaster: HttpResponse<CostingValueMaster>) => costingValueMaster.body)
      );
    }
    return of(new CostingValueMaster());
  }
}

export const costingValueMasterRoute: Routes = [
  {
    path: '',
    component: CostingValueMasterComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      defaultSort: 'id,asc',
      pageTitle: 'Costing Value Master'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: CostingValueMasterDetailComponent,
    resolve: {
      costingValueMaster: CostingValueMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'Costing Value Master'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: CostingValueMasterUpdateComponent,
    resolve: {
      costingValueMaster: CostingValueMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'Costing Value Master'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: CostingValueMasterUpdateComponent,
    resolve: {
      costingValueMaster: CostingValueMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'Costing Value Master'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const costingValueMasterPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: CostingValueMasterDeletePopupComponent,
    resolve: {
      costingValueMaster: CostingValueMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'Costing Value Master'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
