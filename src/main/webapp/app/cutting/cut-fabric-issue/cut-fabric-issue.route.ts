import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { CutFabricIssueComponent } from './cut-fabric-issue.component';
import { CutFabricIssueUpdateComponent } from './cut-fabric-issue-update.component';
import { CutPlanEntryService } from './cut-plan-entry.service';
import { CutPlanEntry, ICutPlanEntry } from 'app/shared/model/cut-plan-entry.model';

@Injectable({ providedIn: 'root' })
export class CutFabricIssueResolve implements Resolve<ICutPlanEntry> {
  constructor(private service: CutPlanEntryService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ICutPlanEntry> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<ICutPlanEntry>) => response.ok),
        map((cutFabricIssue: HttpResponse<ICutPlanEntry>) => cutFabricIssue.body)
      );
    }
    return of(new CutPlanEntry());
  }
}

export const CutFabricIssueRoute: Routes = [
  {
    path: '',
    component: CutFabricIssueComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      defaultSort: 'id,desc',
      pageTitle: 'Pulse | Fabric Issue'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: CutFabricIssueUpdateComponent,
    resolve: {
      cutPlanEntry: CutFabricIssueResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'Pulse | Fabric Issue'
    },
    canActivate: [UserRouteAccessService]
  }
];
