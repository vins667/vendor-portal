import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { CutPlanEntry } from 'app/shared/model/cut-plan-entry.model';
import { CutPlanEntryService } from './cut-plan-entry.service';
import { CutPlanEntryComponent } from './cut-plan-entry.component';
import { CutPlanEntryDetailComponent } from './cut-plan-entry-detail.component';
import { CutPlanEntryUpdateComponent } from './cut-plan-entry-update.component';
import { CutPlanEntryDeletePopupComponent } from './cut-plan-entry-delete-dialog.component';
import { ICutPlanEntry } from 'app/shared/model/cut-plan-entry.model';

@Injectable({ providedIn: 'root' })
export class CutPlanEntryResolve implements Resolve<ICutPlanEntry> {
  constructor(private service: CutPlanEntryService) {}

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

export const cutPlanEntryRoute: Routes = [
  {
    path: '',
    component: CutPlanEntryComponent,
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
    path: ':id/view',
    component: CutPlanEntryDetailComponent,
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
    path: 'new',
    component: CutPlanEntryUpdateComponent,
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
    path: ':id/edit',
    component: CutPlanEntryUpdateComponent,
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

export const cutPlanEntryPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: CutPlanEntryDeletePopupComponent,
    resolve: {
      cutPlanEntry: CutPlanEntryResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'CutPlanEntries'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
