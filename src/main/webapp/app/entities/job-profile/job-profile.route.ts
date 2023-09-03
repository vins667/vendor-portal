import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JobProfile } from 'app/shared/model/job-profile.model';
import { JobProfileService } from './job-profile.service';
import { JobProfileDetailComponent } from './job-profile-detail.component';
import { IJobProfile } from 'app/shared/model/job-profile.model';
import { JobProfileComponent } from './job-profile.component';
import { JhiResolvePagingParams } from 'ng-jhipster';

@Injectable({ providedIn: 'root' })
export class JobProfileResolve implements Resolve<IJobProfile[]> {
  constructor(private service: JobProfileService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<JobProfile[]> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<JobProfile[]>) => response.ok),
        map((jobProfile: HttpResponse<JobProfile[]>) => jobProfile.body)
      );
    }
    const jobProfiles = [];
    return of(jobProfiles);
  }
}

@Injectable({ providedIn: 'root' })
export class JobProfileCustomResolve implements Resolve<IJobProfile[]> {
  constructor(private service: JobProfileService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<JobProfile[]> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.findCustom(id).pipe(
        filter((response: HttpResponse<JobProfile[]>) => response.ok),
        map((jobProfile: HttpResponse<JobProfile[]>) => jobProfile.body)
      );
    }
    const jobProfiles = [];
    return of(jobProfiles);
  }
}

export const jobProfileRoute: Routes = [
  {
    path: 'upload',
    component: JobProfileComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      defaultSort: 'id,asc',
      pageTitle: 'JobProfiles'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: JobProfileDetailComponent,
    resolve: {
      jobProfiles: JobProfileCustomResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'JobProfiles'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const jobProfilePopupRoute: Routes = [];
