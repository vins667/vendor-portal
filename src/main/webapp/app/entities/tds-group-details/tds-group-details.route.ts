import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { TdsGroupDetails } from 'app/shared/model/tds-group-details.model';
import { TdsGroupDetailsService } from './tds-group-details.service';
import { TdsGroupDetailsComponent } from './tds-group-details.component';
import { TdsGroupDetailsDetailComponent } from './tds-group-details-detail.component';
import { TdsGroupDetailsUpdateComponent } from './tds-group-details-update.component';
import { TdsGroupDetailsDeletePopupComponent } from './tds-group-details-delete-dialog.component';
import { ITdsGroupDetails } from 'app/shared/model/tds-group-details.model';

@Injectable({ providedIn: 'root' })
export class TdsGroupDetailsResolve implements Resolve<ITdsGroupDetails> {
  constructor(private service: TdsGroupDetailsService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<TdsGroupDetails> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<TdsGroupDetails>) => response.ok),
        map((tdsGroupDetails: HttpResponse<TdsGroupDetails>) => tdsGroupDetails.body)
      );
    }
    return of(new TdsGroupDetails());
  }
}

export const tdsGroupDetailsRoute: Routes = [
  {
    path: '',
    component: TdsGroupDetailsComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      defaultSort: 'id,asc',
      pageTitle: 'Tds Group Details'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: TdsGroupDetailsDetailComponent,
    resolve: {
      tdsGroupDetails: TdsGroupDetailsResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'Tds Group Details'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: TdsGroupDetailsUpdateComponent,
    resolve: {
      tdsGroupDetails: TdsGroupDetailsResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'Tds Group Details'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: TdsGroupDetailsUpdateComponent,
    resolve: {
      tdsGroupDetails: TdsGroupDetailsResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'Tds Group Details'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const tdsGroupDetailsPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: TdsGroupDetailsDeletePopupComponent,
    resolve: {
      tdsGroupDetails: TdsGroupDetailsResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'Tds Group Details'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
