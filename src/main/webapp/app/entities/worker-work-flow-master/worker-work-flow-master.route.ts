import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { WorkerWorkFlowMaster } from 'app/shared/model/worker-work-flow-master.model';
import { WorkerWorkFlowMasterService } from './worker-work-flow-master.service';
import { WorkerWorkFlowMasterComponent } from './worker-work-flow-master.component';
import { WorkerWorkFlowMasterDetailComponent } from './worker-work-flow-master-detail.component';
import { WorkerWorkFlowMasterUpdateComponent } from './worker-work-flow-master-update.component';
import { WorkerWorkFlowMasterDeletePopupComponent } from './worker-work-flow-master-delete-dialog.component';
import { IWorkerWorkFlowMaster } from 'app/shared/model/worker-work-flow-master.model';

@Injectable({ providedIn: 'root' })
export class WorkerWorkFlowMasterResolve implements Resolve<IWorkerWorkFlowMaster> {
  constructor(private service: WorkerWorkFlowMasterService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<WorkerWorkFlowMaster> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<WorkerWorkFlowMaster>) => response.ok),
        map((workerWorkFlowMaster: HttpResponse<WorkerWorkFlowMaster>) => workerWorkFlowMaster.body)
      );
    }
    return of(new WorkerWorkFlowMaster());
  }
}

export const workerWorkFlowMasterRoute: Routes = [
  {
    path: '',
    component: WorkerWorkFlowMasterComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      defaultSort: 'id,asc',
      pageTitle: 'WorkerWorkFlowMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: WorkerWorkFlowMasterDetailComponent,
    resolve: {
      workerWorkFlowMaster: WorkerWorkFlowMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'WorkerWorkFlowMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: WorkerWorkFlowMasterUpdateComponent,
    resolve: {
      workerWorkFlowMaster: WorkerWorkFlowMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'WorkerWorkFlowMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: WorkerWorkFlowMasterUpdateComponent,
    resolve: {
      workerWorkFlowMaster: WorkerWorkFlowMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'WorkerWorkFlowMasters'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const workerWorkFlowMasterPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: WorkerWorkFlowMasterDeletePopupComponent,
    resolve: {
      workerWorkFlowMaster: WorkerWorkFlowMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'WorkerWorkFlowMasters'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
