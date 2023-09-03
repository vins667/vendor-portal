import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { CutPlanEntry } from 'app/shared/model/cut-plan-entry.model';
import { CutPlanMrkrEntryService } from './cut-plan-mrkr-entry.service';
import { CutPlanMrkrEntryComponent } from './cut-plan-mrkr-entry.component';
import { CutPlanMrkrEntryDetailComponent } from './cut-plan-mrkr-entry-detail.component';
import { CutPlanMrkrEntryUpdateComponent } from './cut-plan-mrkr-entry-update.component';
import { CutPlanMrkrEntryDeletePopupComponent } from './cut-plan-mrkr-entry-delete-dialog.component';
import { ICutPlanEntry } from 'app/shared/model/cut-plan-entry.model';

@Injectable({ providedIn: 'root' })
export class CutPlanEntryResolve implements Resolve<ICutPlanEntry> {
  constructor(private service: CutPlanMrkrEntryService) {}

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

export const cutPlanMrkrEntryRoute: Routes = [
  {
    path: '',
    component: CutPlanMrkrEntryComponent,
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
    component: CutPlanMrkrEntryDetailComponent,
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
    component: CutPlanMrkrEntryUpdateComponent,
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
    component: CutPlanMrkrEntryUpdateComponent,
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

export const cutPlanMrkrEntryPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: CutPlanMrkrEntryDeletePopupComponent,
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
