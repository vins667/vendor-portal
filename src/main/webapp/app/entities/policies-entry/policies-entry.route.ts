import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Policies } from 'app/shared/model/policies.model';
import { PoliciesEntryService } from './policies-entry.service';
import { IPolicies } from 'app/shared/model/policies.model';
import { PoliciesViewComponent } from '../policies-entry/policies-view.component';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { PoliciesUpdateComponent } from '../policies-entry/policies-update.component';
import { PoliciesDeletePopupComponent } from '../policies-entry/policies-delete-dialog.component';

@Injectable({ providedIn: 'root' })
export class PoliciesEntryResolve implements Resolve<IPolicies> {
  constructor(private service: PoliciesEntryService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Policies> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<Policies>) => response.ok),
        map((policies: HttpResponse<Policies>) => policies.body)
      );
    }
    return of(new Policies());
  }
}

export const policiesEntryRoute: Routes = [
  {
    path: '',
    component: PoliciesViewComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      defaultSort: 'id,asc',
      pageTitle: 'Policies'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: PoliciesUpdateComponent,
    resolve: {
      policies: PoliciesEntryResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'Policies'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: PoliciesUpdateComponent,
    resolve: {
      policies: PoliciesEntryResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'Policies'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const policiesEntryPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: PoliciesDeletePopupComponent,
    resolve: {
      policies: PoliciesEntryResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'Policies'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
