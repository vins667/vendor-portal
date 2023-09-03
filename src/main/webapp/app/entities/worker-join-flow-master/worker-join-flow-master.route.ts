import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { WorkerJoinFlowMaster } from 'app/shared/model/worker-join-flow-master.model';
import { WorkerJoinFlowMasterService } from './worker-join-flow-master.service';
import { WorkerJoinFlowMasterComponent } from './worker-join-flow-master.component';
import { WorkerJoinFlowMasterDetailComponent } from './worker-join-flow-master-detail.component';
import { WorkerJoinFlowMasterUpdateComponent } from './worker-join-flow-master-update.component';
import { WorkerJoinFlowMasterDeletePopupComponent } from './worker-join-flow-master-delete-dialog.component';
import { IWorkerJoinFlowMaster } from 'app/shared/model/worker-join-flow-master.model';

@Injectable({ providedIn: 'root' })
export class WorkerJoinFlowMasterResolve implements Resolve<IWorkerJoinFlowMaster> {
  constructor(private service: WorkerJoinFlowMasterService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<WorkerJoinFlowMaster> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<WorkerJoinFlowMaster>) => response.ok),
        map((workerJoinFlowMaster: HttpResponse<WorkerJoinFlowMaster>) => workerJoinFlowMaster.body)
      );
    }
    return of(new WorkerJoinFlowMaster());
  }
}

export const workerJoinFlowMasterRoute: Routes = [
  {
    path: '',
    component: WorkerJoinFlowMasterComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      defaultSort: 'id,asc',
      pageTitle: 'WorkerJoinFlowMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: WorkerJoinFlowMasterDetailComponent,
    resolve: {
      workerJoinFlowMaster: WorkerJoinFlowMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'WorkerJoinFlowMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: WorkerJoinFlowMasterUpdateComponent,
    resolve: {
      workerJoinFlowMaster: WorkerJoinFlowMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'WorkerJoinFlowMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: WorkerJoinFlowMasterUpdateComponent,
    resolve: {
      workerJoinFlowMaster: WorkerJoinFlowMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'WorkerJoinFlowMasters'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const workerJoinFlowMasterPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: WorkerJoinFlowMasterDeletePopupComponent,
    resolve: {
      workerJoinFlowMaster: WorkerJoinFlowMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'WorkerJoinFlowMasters'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
