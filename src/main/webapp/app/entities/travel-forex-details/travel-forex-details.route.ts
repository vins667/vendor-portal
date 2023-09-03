import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { TravelForexDetails } from 'app/shared/model/travel-forex-details.model';
import { TravelForexDetailsService } from './travel-forex-details.service';
import { TravelForexDetailsComponent } from './travel-forex-details.component';
import { TravelForexDetailsDetailComponent } from './travel-forex-details-detail.component';
import { TravelForexDetailsUpdateComponent } from './travel-forex-details-update.component';
import { TravelForexDetailsDeletePopupComponent } from './travel-forex-details-delete-dialog.component';
import { ITravelForexDetails } from 'app/shared/model/travel-forex-details.model';

@Injectable({ providedIn: 'root' })
export class TravelForexDetailsResolve implements Resolve<ITravelForexDetails> {
  constructor(private service: TravelForexDetailsService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ITravelForexDetails> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<TravelForexDetails>) => response.ok),
        map((travelForexDetails: HttpResponse<TravelForexDetails>) => travelForexDetails.body)
      );
    }
    return of(new TravelForexDetails());
  }
}

export const travelForexDetailsRoute: Routes = [
  {
    path: '',
    component: TravelForexDetailsComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      defaultSort: 'id,asc',
      pageTitle: 'TravelForexDetails'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: TravelForexDetailsDetailComponent,
    resolve: {
      travelForexDetails: TravelForexDetailsResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'TravelForexDetails'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: TravelForexDetailsUpdateComponent,
    resolve: {
      travelForexDetails: TravelForexDetailsResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'TravelForexDetails'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: TravelForexDetailsUpdateComponent,
    resolve: {
      travelForexDetails: TravelForexDetailsResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'TravelForexDetails'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const travelForexDetailsPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: TravelForexDetailsDeletePopupComponent,
    resolve: {
      travelForexDetails: TravelForexDetailsResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'TravelForexDetails'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
