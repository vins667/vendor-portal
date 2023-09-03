import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { WorkerRecruitment } from 'app/shared/model/worker-recruitment.model';
import { WorkerRecruitmentService } from './worker-recruitment.service';
import { WorkerRecruitmentComponent } from './worker-recruitment.component';
import { WorkerRecruitmentDetailComponent } from './worker-recruitment-detail.component';
import { WorkerRecruitmentUpdateComponent } from './worker-recruitment-update.component';
import { WorkerRecruitmentDeletePopupComponent } from './worker-recruitment-delete-dialog.component';
import { IWorkerRecruitment } from 'app/shared/model/worker-recruitment.model';

@Injectable({ providedIn: 'root' })
export class WorkerRecruitmentResolve implements Resolve<IWorkerRecruitment> {
  constructor(private service: WorkerRecruitmentService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IWorkerRecruitment> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<WorkerRecruitment>) => response.ok),
        map((workerRecruitment: HttpResponse<WorkerRecruitment>) => workerRecruitment.body)
      );
    }
    return of(new WorkerRecruitment());
  }
}

export const workerRecruitmentRoute: Routes = [
  {
    path: '',
    component: WorkerRecruitmentComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      defaultSort: 'id,asc',
      pageTitle: 'Recruitments'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: WorkerRecruitmentDetailComponent,
    resolve: {
      workerRecruitment: WorkerRecruitmentResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'Recruitments'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: WorkerRecruitmentUpdateComponent,
    resolve: {
      workerRecruitment: WorkerRecruitmentResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'Recruitments'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: WorkerRecruitmentUpdateComponent,
    resolve: {
      workerRecruitment: WorkerRecruitmentResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'Recruitments'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const workerRecruitmentPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: WorkerRecruitmentDeletePopupComponent,
    resolve: {
      workerRecruitment: WorkerRecruitmentResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'Recruitments'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
