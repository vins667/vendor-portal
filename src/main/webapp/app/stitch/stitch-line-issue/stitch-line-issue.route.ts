import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ICutPlanIssueStitch, CutPlanIssueStitch } from 'app/shared/model/cut-plan-issue-stitch.model';
import { StitchLineIssueService } from './stitch-line-issue.service';
import { StitchLineIssueComponent } from './stitch-line-issue.component';
import { StitchLineIssueDetailComponent } from './stitch-line-issue-detail.component';
import { StitchLineIssueUpdateComponent } from './stitch-line-issue-update.component';

@Injectable({ providedIn: 'root' })
export class StitchLineIssueRouteResolve implements Resolve<ICutPlanIssueStitch> {
  constructor(private service: StitchLineIssueService, private router: Router) {}

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

export const stitchLineIssueRoute: Routes = [
  {
    path: '',
    component: StitchLineIssueComponent,
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
    component: StitchLineIssueDetailComponent,
    resolve: {
      cutPlanIssueStitch: StitchLineIssueRouteResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'CutPlanIssueStitches'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: StitchLineIssueUpdateComponent,
    resolve: {
      cutPlanIssueStitch: StitchLineIssueRouteResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'CutPlanIssueStitches'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: StitchLineIssueUpdateComponent,
    resolve: {
      cutPlanIssueStitch: StitchLineIssueRouteResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'CutPlanIssueStitches'
    },
    canActivate: [UserRouteAccessService]
  }
];
