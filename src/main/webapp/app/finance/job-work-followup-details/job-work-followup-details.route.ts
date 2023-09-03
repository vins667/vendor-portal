import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JobWorkFollowupDetails } from './job-work-followup-details.model';
import { JobWorkFollowupDetailsService } from './job-work-followup-details.service';
import { JobWorkFollowupDetailsComponent } from './job-work-followup-details.component';
import { JobWorkFollowupDetailsUpdateComponent } from './job-work-followup-details-update.component';
import { IJobWorkFollowupDetails } from './job-work-followup-details.model';

@Injectable({ providedIn: 'root' })
export class JobWorkFollowupDetailsResolve implements Resolve<IJobWorkFollowupDetails> {
  constructor(private service: JobWorkFollowupDetailsService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IJobWorkFollowupDetails> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<JobWorkFollowupDetails>) => response.ok),
        map((jobWorkFollowupDetails: HttpResponse<JobWorkFollowupDetails>) => jobWorkFollowupDetails.body)
      );
    }
    return of(new JobWorkFollowupDetails());
  }
}

export const jobWorkFollowupDetailsRoute: Routes = [
  {
    path: '',
    component: JobWorkFollowupDetailsComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'jobWorkDate,asc',
      pageTitle: 'Buyer Master'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: JobWorkFollowupDetailsUpdateComponent,
    resolve: {
      jobWorkFollowupDetails: JobWorkFollowupDetailsResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'Buyer Master'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: JobWorkFollowupDetailsUpdateComponent,
    resolve: {
      jobWorkFollowupDetails: JobWorkFollowupDetailsResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'Buyer Master'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const jobWorkFollowupDetailsPopupRoute: Routes = [];
