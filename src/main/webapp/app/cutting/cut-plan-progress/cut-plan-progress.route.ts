import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { CutPlanEntry } from 'app/shared/model/cut-plan-entry.model';
import { CutPlanProgressService } from './cut-plan-progress.service';
import { CutPlanProgressComponent } from './cut-plan-progress.component';
import { CutPlanProgressUpdateComponent } from './cut-plan-progress-update.component';
import { ICutPlanEntry } from 'app/shared/model/cut-plan-entry.model';

@Injectable({ providedIn: 'root' })
export class CutPlanEntryResolve implements Resolve<ICutPlanEntry> {
  constructor(private service: CutPlanProgressService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ICutPlanEntry> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<CutPlanEntry>) => response.ok),
        map((cutPlanEntry: HttpResponse<CutPlanEntry>) => cutPlanEntry.body)
      );
    }
    return of(new CutPlanEntry());
  }
}

export const cutPlanProgressRoute: Routes = [
  {
    path: '',
    component: CutPlanProgressComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      defaultSort: 'id,desc',
      pageTitle: 'CutPlanEntries'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: CutPlanProgressUpdateComponent,
    resolve: {
      cutPlanEntry: CutPlanEntryResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'CutPlanEntries'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const cutPlanProgressPopupRoute: Routes = [];
