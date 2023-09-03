import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ICutPlanIssueStitch, CutPlanIssueStitch } from 'app/shared/model/cut-plan-issue-stitch.model';
import { CutPlanIssueStitchService } from './cut-plan-issue-stitch.service';
import { CutPlanIssueStitchComponent } from './cut-plan-issue-stitch.component';
import { CutPlanIssueStitchDetailComponent } from './cut-plan-issue-stitch-detail.component';
import { CutPlanIssueStitchUpdateComponent } from './cut-plan-issue-stitch-update.component';

@Injectable({ providedIn: 'root' })
export class CutPlanIssueStitchResolve implements Resolve<ICutPlanIssueStitch> {
  constructor(private service: CutPlanIssueStitchService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ICutPlanIssueStitch> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((cutPlanIssueStitch: HttpResponse<CutPlanIssueStitch>) => {
          if (cutPlanIssueStitch.body) {
            return of(cutPlanIssueStitch.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new CutPlanIssueStitch());
  }
}

export const cutPlanIssueStitchRoute: Routes = [
  {
    path: '',
    component: CutPlanIssueStitchComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      defaultSort: 'id,desc',
      pageTitle: 'CutPlanIssueStitches'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: CutPlanIssueStitchDetailComponent,
    resolve: {
      cutPlanIssueStitch: CutPlanIssueStitchResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'CutPlanIssueStitches'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: CutPlanIssueStitchUpdateComponent,
    resolve: {
      cutPlanIssueStitch: CutPlanIssueStitchResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'CutPlanIssueStitches'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: CutPlanIssueStitchUpdateComponent,
    resolve: {
      cutPlanIssueStitch: CutPlanIssueStitchResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'CutPlanIssueStitches'
    },
    canActivate: [UserRouteAccessService]
  }
];
