import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IStitchIssuePack, StitchIssuePack } from 'app/shared/model/stitch-issue-pack.model';
import { StitchIssuePackService } from './stitch-issue-pack.service';
import { StitchIssuePackComponent } from './stitch-issue-pack.component';
import { StitchIssuePackDetailComponent } from './stitch-issue-pack-detail.component';
import { StitchIssuePackUpdateComponent } from './stitch-issue-pack-update.component';

@Injectable({ providedIn: 'root' })
export class StitchIssuePackResolve implements Resolve<IStitchIssuePack> {
  constructor(private service: StitchIssuePackService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IStitchIssuePack> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((stitchIssuePack: HttpResponse<StitchIssuePack>) => {
          if (stitchIssuePack.body) {
            return of(stitchIssuePack.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new StitchIssuePack());
  }
}

export const stitchIssuePackRoute: Routes = [
  {
    path: '',
    component: StitchIssuePackComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      defaultSort: 'id,desc',
      pageTitle: 'StitchIssuePackes'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: StitchIssuePackDetailComponent,
    resolve: {
      stitchIssuePack: StitchIssuePackResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'StitchIssuePackes'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: StitchIssuePackUpdateComponent,
    resolve: {
      stitchIssuePack: StitchIssuePackResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'StitchIssuePackes'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: StitchIssuePackUpdateComponent,
    resolve: {
      stitchIssuePack: StitchIssuePackResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'StitchIssuePackes'
    },
    canActivate: [UserRouteAccessService]
  }
];
