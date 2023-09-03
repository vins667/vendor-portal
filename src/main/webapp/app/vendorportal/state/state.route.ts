import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { State } from 'app/shared/model/state.model';
import { StateService } from './state.service';
import { StateComponent } from './state.component';
import { StateDetailComponent } from './state-detail.component';
import { StateUpdateComponent } from './state-update.component';
import { StateDeletePopupComponent } from './state-delete-dialog.component';
import { IState } from 'app/shared/model/state.model';

@Injectable({ providedIn: 'root' })
export class StateResolve implements Resolve<IState> {
  constructor(private service: StateService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<State> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<State>) => response.ok),
        map((states: HttpResponse<State>) => states.body)
      );
    }
    return of(new State());
  }
}

export const stateRoute: Routes = [
  {
    path: '',
    component: StateComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      defaultSort: 'id,asc',
      pageTitle: 'States'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: StateDetailComponent,
    resolve: {
      state: StateResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'States'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: StateUpdateComponent,
    resolve: {
      state: StateResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'States'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: StateUpdateComponent,
    resolve: {
      state: StateResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'States'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const statePopupRoute: Routes = [
  {
    path: ':id/delete',
    component: StateDeletePopupComponent,
    resolve: {
      state: StateResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'States'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
