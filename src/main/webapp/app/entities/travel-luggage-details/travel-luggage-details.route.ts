import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { TravelLuggageDetails } from 'app/shared/model/travel-luggage-details.model';
import { TravelLuggageDetailsService } from './travel-luggage-details.service';
import { TravelLuggageDetailsComponent } from './travel-luggage-details.component';
import { TravelLuggageDetailsDetailComponent } from './travel-luggage-details-detail.component';
import { TravelLuggageDetailsUpdateComponent } from './travel-luggage-details-update.component';
import { TravelLuggageDetailsDeletePopupComponent } from './travel-luggage-details-delete-dialog.component';
import { ITravelLuggageDetails } from 'app/shared/model/travel-luggage-details.model';

@Injectable({ providedIn: 'root' })
export class TravelLuggageDetailsResolve implements Resolve<ITravelLuggageDetails> {
  constructor(private service: TravelLuggageDetailsService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ITravelLuggageDetails> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<TravelLuggageDetails>) => response.ok),
        map((travelLuggageDetails: HttpResponse<TravelLuggageDetails>) => travelLuggageDetails.body)
      );
    }
    return of(new TravelLuggageDetails());
  }
}

export const travelLuggageDetailsRoute: Routes = [
  {
    path: '',
    component: TravelLuggageDetailsComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      defaultSort: 'id,asc',
      pageTitle: 'TravelLuggageDetails'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: TravelLuggageDetailsDetailComponent,
    resolve: {
      travelLuggageDetails: TravelLuggageDetailsResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'TravelLuggageDetails'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: TravelLuggageDetailsUpdateComponent,
    resolve: {
      travelLuggageDetails: TravelLuggageDetailsResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'TravelLuggageDetails'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: TravelLuggageDetailsUpdateComponent,
    resolve: {
      travelLuggageDetails: TravelLuggageDetailsResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'TravelLuggageDetails'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const travelLuggageDetailsPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: TravelLuggageDetailsDeletePopupComponent,
    resolve: {
      travelLuggageDetails: TravelLuggageDetailsResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'TravelLuggageDetails'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
