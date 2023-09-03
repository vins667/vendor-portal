import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { WorkerJoining } from 'app/shared/model/worker-joining.model';
import { WorkerJoiningService } from './worker-joining.service';
import { WorkerJoiningComponent } from './worker-joining.component';
import { WorkerJoiningDetailComponent } from './worker-joining-detail.component';
import { WorkerJoiningUpdateComponent } from './worker-joining-update.component';
import { WorkerJoiningDeletePopupComponent } from './worker-joining-delete-dialog.component';
import { IWorkerJoining } from 'app/shared/model/worker-joining.model';

@Injectable({ providedIn: 'root' })
export class WorkerJoiningResolve implements Resolve<IWorkerJoining> {
  constructor(private service: WorkerJoiningService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IWorkerJoining> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<WorkerJoining>) => response.ok),
        map((workerJoining: HttpResponse<WorkerJoining>) => workerJoining.body)
      );
    }
    return of(new WorkerJoining());
  }
}

export const workerJoiningRoute: Routes = [
  {
    path: '',
    component: WorkerJoiningComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      defaultSort: 'id,asc',
      pageTitle: 'Worker Joinings'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: WorkerJoiningDetailComponent,
    resolve: {
      workerJoining: WorkerJoiningResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'Worker Joinings'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: WorkerJoiningUpdateComponent,
    resolve: {
      workerJoining: WorkerJoiningResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'Worker Joinings'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: WorkerJoiningUpdateComponent,
    resolve: {
      workerJoining: WorkerJoiningResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'Worker Joinings'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const workerJoiningPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: WorkerJoiningDeletePopupComponent,
    resolve: {
      workerJoining: WorkerJoiningResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'Worker Joinings'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
