import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { WorkerForwardTypeMaster } from 'app/shared/model/worker-forward-type-master.model';
import { WorkerForwardTypeMasterService } from './worker-forward-type-master.service';
import { WorkerForwardTypeMasterComponent } from './worker-forward-type-master.component';
import { WorkerForwardTypeMasterDetailComponent } from './worker-forward-type-master-detail.component';
import { WorkerForwardTypeMasterUpdateComponent } from './worker-forward-type-master-update.component';
import { WorkerForwardTypeMasterDeletePopupComponent } from './worker-forward-type-master-delete-dialog.component';
import { IWorkerForwardTypeMaster } from 'app/shared/model/worker-forward-type-master.model';

@Injectable({ providedIn: 'root' })
export class WorkerForwardTypeMasterResolve implements Resolve<IWorkerForwardTypeMaster> {
  constructor(private service: WorkerForwardTypeMasterService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<WorkerForwardTypeMaster> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<WorkerForwardTypeMaster>) => response.ok),
        map((workerForwardTypeMaster: HttpResponse<WorkerForwardTypeMaster>) => workerForwardTypeMaster.body)
      );
    }
    return of(new WorkerForwardTypeMaster());
  }
}

export const workerForwardTypeMasterRoute: Routes = [
  {
    path: '',
    component: WorkerForwardTypeMasterComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      defaultSort: 'id,asc',
      pageTitle: 'WorkerForwardTypeMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: WorkerForwardTypeMasterDetailComponent,
    resolve: {
      workerForwardTypeMaster: WorkerForwardTypeMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'WorkerForwardTypeMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: WorkerForwardTypeMasterUpdateComponent,
    resolve: {
      workerForwardTypeMaster: WorkerForwardTypeMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'WorkerForwardTypeMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: WorkerForwardTypeMasterUpdateComponent,
    resolve: {
      workerForwardTypeMaster: WorkerForwardTypeMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'WorkerForwardTypeMasters'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const workerForwardTypeMasterPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: WorkerForwardTypeMasterDeletePopupComponent,
    resolve: {
      workerForwardTypeMaster: WorkerForwardTypeMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'WorkerForwardTypeMasters'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
