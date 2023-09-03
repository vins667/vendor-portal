import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JobWorkFollowup } from './job-work-followup.model';
import { JobWorkFollowupService } from './job-work-followup.service';
import { JobWorkFollowupComponent } from './job-work-followup.component';
import { JobWorkFollowupDetailComponent } from './job-work-followup-detail.component';
import { JobWorkFollowupUpdateComponent } from './job-work-followup-update.component';
import { JobWorkFollowupDeletePopupComponent } from './job-work-followup-delete-dialog.component';
import { IJobWorkFollowup } from './job-work-followup.model';

@Injectable({ providedIn: 'root' })
export class JobWorkFollowupResolve implements Resolve<IJobWorkFollowup> {
  constructor(private service: JobWorkFollowupService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IJobWorkFollowup> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<JobWorkFollowup>) => response.ok),
        map((jobWorkFollowup: HttpResponse<JobWorkFollowup>) => jobWorkFollowup.body)
      );
    }
    return of(new JobWorkFollowup());
  }
}

export const jobWorkFollowupRoute: Routes = [
  {
    path: '',
    component: JobWorkFollowupComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'JobWorkFollowups'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: JobWorkFollowupDetailComponent,
    resolve: {
      jobWorkFollowup: JobWorkFollowupResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'JobWorkFollowups'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: JobWorkFollowupUpdateComponent,
    resolve: {
      jobWorkFollowup: JobWorkFollowupResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'JobWorkFollowups'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: JobWorkFollowupUpdateComponent,
    resolve: {
      jobWorkFollowup: JobWorkFollowupResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'JobWorkFollowups'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const jobWorkFollowupPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: JobWorkFollowupDeletePopupComponent,
    resolve: {
      jobWorkFollowup: JobWorkFollowupResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'JobWorkFollowups'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
