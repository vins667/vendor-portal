import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { CostingProcessMaster } from 'app/shared/model/costing-process-master.model';
import { CostingProcessMasterService } from './costing-process-master.service';
import { CostingProcessMasterComponent } from './costing-process-master.component';
import { CostingProcessMasterDetailComponent } from './costing-process-master-detail.component';
import { CostingProcessMasterUpdateComponent } from './costing-process-master-update.component';
import { CostingProcessMasterDeletePopupComponent } from './costing-process-master-delete-dialog.component';
import { ICostingProcessMaster } from 'app/shared/model/costing-process-master.model';

@Injectable({ providedIn: 'root' })
export class CostingProcessMasterResolve implements Resolve<ICostingProcessMaster> {
  constructor(private service: CostingProcessMasterService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ICostingProcessMaster> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<CostingProcessMaster>) => response.ok),
        map((costingProcessMaster: HttpResponse<CostingProcessMaster>) => costingProcessMaster.body)
      );
    }
    return of(new CostingProcessMaster());
  }
}

export const costingProcessMasterRoute: Routes = [
  {
    path: '',
    component: CostingProcessMasterComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      defaultSort: 'id,asc',
      pageTitle: 'Costing Process Master'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: CostingProcessMasterDetailComponent,
    resolve: {
      costingProcessMaster: CostingProcessMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'Costing Process Master'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: CostingProcessMasterUpdateComponent,
    resolve: {
      costingProcessMaster: CostingProcessMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'Costing Process Master'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: CostingProcessMasterUpdateComponent,
    resolve: {
      costingProcessMaster: CostingProcessMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'Costing Process Master'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const costingProcessMasterPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: CostingProcessMasterDeletePopupComponent,
    resolve: {
      costingProcessMaster: CostingProcessMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'Costing Process Master'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
