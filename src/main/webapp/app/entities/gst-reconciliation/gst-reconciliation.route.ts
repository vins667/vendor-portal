import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { GstReconciliation } from 'app/shared/model/gst-reconciliation.model';
import { GstReconciliationService } from './gst-reconciliation.service';
import { GstReconciliationComponent } from './gst-reconciliation.component';
import { GstReconciliationDetailComponent } from './gst-reconciliation-detail.component';
import { GstReconciliationUpdateComponent } from './gst-reconciliation-update.component';
import { GstReconciliationDeletePopupComponent } from './gst-reconciliation-delete-dialog.component';
import { IGstReconciliation } from 'app/shared/model/gst-reconciliation.model';
import { GstReconciliationMiscComponent } from './gst-reconciliation-misc.component';

@Injectable({ providedIn: 'root' })
export class GstReconciliationResolve implements Resolve<IGstReconciliation> {
  constructor(private service: GstReconciliationService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IGstReconciliation> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<GstReconciliation>) => response.ok),
        map((gstReconciliation: HttpResponse<GstReconciliation>) => gstReconciliation.body)
      );
    }
    return of(new GstReconciliation());
  }
}

export const gstReconciliationRoute: Routes = [
  {
    path: '',
    component: GstReconciliationComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      defaultSort: 'id,asc',
      pageTitle: 'GstReconciliations'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: GstReconciliationDetailComponent,
    resolve: {
      gstReconciliation: GstReconciliationResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'GstReconciliations'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: GstReconciliationUpdateComponent,
    resolve: {
      gstReconciliation: GstReconciliationResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'GstReconciliations'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'misc',
    component: GstReconciliationMiscComponent,
    resolve: {
      gstReconciliation: GstReconciliationResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'GstReconciliations'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: GstReconciliationUpdateComponent,
    resolve: {
      gstReconciliation: GstReconciliationResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'GstReconciliations'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const gstReconciliationPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: GstReconciliationDeletePopupComponent,
    resolve: {
      gstReconciliation: GstReconciliationResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'GstReconciliations'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
