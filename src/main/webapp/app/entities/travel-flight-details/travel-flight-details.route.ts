import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { TravelFlightDetails } from 'app/shared/model/travel-flight-details.model';
import { TravelFlightDetailsService } from './travel-flight-details.service';
import { TravelFlightDetailsComponent } from './travel-flight-details.component';
import { TravelFlightDetailsDetailComponent } from './travel-flight-details-detail.component';
import { TravelFlightDetailsUpdateComponent } from './travel-flight-details-update.component';
import { TravelFlightDetailsDeletePopupComponent } from './travel-flight-details-delete-dialog.component';
import { ITravelFlightDetails } from 'app/shared/model/travel-flight-details.model';

@Injectable({ providedIn: 'root' })
export class TravelFlightDetailsResolve implements Resolve<ITravelFlightDetails> {
  constructor(private service: TravelFlightDetailsService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ITravelFlightDetails> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<TravelFlightDetails>) => response.ok),
        map((travelFlightDetails: HttpResponse<TravelFlightDetails>) => travelFlightDetails.body)
      );
    }
    return of(new TravelFlightDetails());
  }
}

export const travelFlightDetailsRoute: Routes = [
  {
    path: '',
    component: TravelFlightDetailsComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      defaultSort: 'id,asc',
      pageTitle: 'TravelFlightDetails'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: TravelFlightDetailsDetailComponent,
    resolve: {
      travelFlightDetails: TravelFlightDetailsResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'TravelFlightDetails'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: TravelFlightDetailsUpdateComponent,
    resolve: {
      travelFlightDetails: TravelFlightDetailsResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'TravelFlightDetails'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: TravelFlightDetailsUpdateComponent,
    resolve: {
      travelFlightDetails: TravelFlightDetailsResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'TravelFlightDetails'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const travelFlightDetailsPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: TravelFlightDetailsDeletePopupComponent,
    resolve: {
      travelFlightDetails: TravelFlightDetailsResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'TravelFlightDetails'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
