import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { CostingGroupMaster } from 'app/shared/model/costing-group-master.model';
import { CostingGroupMasterService } from './costing-group-master.service';
import { CostingGroupMasterComponent } from './costing-group-master.component';
import { CostingGroupMasterDetailComponent } from './costing-group-master-detail.component';
import { CostingGroupMasterUpdateComponent } from './costing-group-master-update.component';
import { CostingGroupMasterDeletePopupComponent } from './costing-group-master-delete-dialog.component';
import { ICostingGroupMaster } from 'app/shared/model/costing-group-master.model';

@Injectable({ providedIn: 'root' })
export class CostingGroupMasterResolve implements Resolve<ICostingGroupMaster> {
  constructor(private service: CostingGroupMasterService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ICostingGroupMaster> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<CostingGroupMaster>) => response.ok),
        map((costingGroupMaster: HttpResponse<CostingGroupMaster>) => costingGroupMaster.body)
      );
    }
    return of(new CostingGroupMaster());
  }
}

export const costingGroupMasterRoute: Routes = [
  {
    path: '',
    component: CostingGroupMasterComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      defaultSort: 'id,asc',
      pageTitle: 'Costing Group Master'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: CostingGroupMasterDetailComponent,
    resolve: {
      costingGroupMaster: CostingGroupMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'Costing Group Master'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: CostingGroupMasterUpdateComponent,
    resolve: {
      costingGroupMaster: CostingGroupMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'Costing Group Master'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: CostingGroupMasterUpdateComponent,
    resolve: {
      costingGroupMaster: CostingGroupMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'Costing Group Master'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const costingGroupMasterPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: CostingGroupMasterDeletePopupComponent,
    resolve: {
      costingGroupMaster: CostingGroupMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'Costing Group Master'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
