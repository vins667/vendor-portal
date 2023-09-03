import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ICutPlanIssueStitch, CutPlanIssueStitch } from 'app/shared/model/cut-plan-issue-stitch.model';
import { PackingLineIssueService } from './packing-line-issue.service';
import { PackingLineIssueComponent } from './packing-line-issue.component';
import { PackingLineIssueDetailComponent } from './packing-line-issue-detail.component';
import { PackingLineIssueUpdateComponent } from './packing-line-issue-update.component';

@Injectable({ providedIn: 'root' })
export class PackingLineIssueRouteResolve implements Resolve<ICutPlanIssueStitch> {
  constructor(private service: PackingLineIssueService, private router: Router) {}

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
    component: PackingLineIssueComponent,
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
    component: PackingLineIssueDetailComponent,
    resolve: {
      cutPlanIssueStitch: PackingLineIssueRouteResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'CutPlanIssueStitches'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: PackingLineIssueUpdateComponent,
    resolve: {
      cutPlanIssueStitch: PackingLineIssueRouteResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'CutPlanIssueStitches'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: PackingLineIssueUpdateComponent,
    resolve: {
      cutPlanIssueStitch: PackingLineIssueRouteResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'CutPlanIssueStitches'
    },
    canActivate: [UserRouteAccessService]
  }
];
