import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { CutPlanEntry, ICutPlanEntry } from 'app/shared/model/cut-plan-entry.model';
import { CutPlanBundleService } from './cut-plan-bundle.service';
import { CutPlanBundleComponent } from './cut-plan-bundle.component';
import { CutPlanBundleUpdateComponent } from './cut-plan-bundle-update.component';

@Injectable({ providedIn: 'root' })
export class CutPlanEntryResolve implements Resolve<ICutPlanEntry> {
  constructor(private service: CutPlanBundleService) {}

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

export const cutPlanBundleRoute: Routes = [
  {
    path: '',
    component: CutPlanBundleComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      defaultSort: 'production_code,desc',
      pageTitle: 'CutPlanEntries'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: CutPlanBundleUpdateComponent,
    resolve: {
      cutPlanEntry: CutPlanEntryResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'CutPlanEntries'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'edit',
    component: CutPlanBundleUpdateComponent,
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

export const cutPlanBundlePopupRoute: Routes = [];
