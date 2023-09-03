import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JobProfile, IJobProfile } from 'app/shared/model/job-profile.model';
import { InductionDetailComponent } from './induction-detail.component';
import { InductionService } from './induction.service';

@Injectable({ providedIn: 'root' })
export class InductionResolve implements Resolve<IJobProfile[]> {
  constructor(private service: InductionService) {}

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
  constructor(private service: InductionService) {}

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

export const inductionRoute: Routes = [
  {
    path: ':id/view',
    component: InductionDetailComponent,
    resolve: {
      jobProfiles: InductionResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'Induction'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const inductionPopupRoute: Routes = [];
