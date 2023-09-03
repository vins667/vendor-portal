import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { TravelAccommodationDetails } from 'app/shared/model/travel-accommodation-details.model';
import { TravelAccommodationDetailsService } from './travel-accommodation-details.service';
import { TravelAccommodationDetailsComponent } from './travel-accommodation-details.component';
import { TravelAccommodationDetailsDetailComponent } from './travel-accommodation-details-detail.component';
import { TravelAccommodationDetailsUpdateComponent } from './travel-accommodation-details-update.component';
import { TravelAccommodationDetailsDeletePopupComponent } from './travel-accommodation-details-delete-dialog.component';
import { ITravelAccommodationDetails } from 'app/shared/model/travel-accommodation-details.model';

@Injectable({ providedIn: 'root' })
export class TravelAccommodationDetailsResolve implements Resolve<ITravelAccommodationDetails> {
  constructor(private service: TravelAccommodationDetailsService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ITravelAccommodationDetails> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<TravelAccommodationDetails>) => response.ok),
        map((travelAccommodationDetails: HttpResponse<TravelAccommodationDetails>) => travelAccommodationDetails.body)
      );
    }
    return of(new TravelAccommodationDetails());
  }
}

export const travelAccommodationDetailsRoute: Routes = [
  {
    path: '',
    component: TravelAccommodationDetailsComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      defaultSort: 'id,asc',
      pageTitle: 'TravelAccommodationDetails'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: TravelAccommodationDetailsDetailComponent,
    resolve: {
      travelAccommodationDetails: TravelAccommodationDetailsResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'TravelAccommodationDetails'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: TravelAccommodationDetailsUpdateComponent,
    resolve: {
      travelAccommodationDetails: TravelAccommodationDetailsResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'TravelAccommodationDetails'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: TravelAccommodationDetailsUpdateComponent,
    resolve: {
      travelAccommodationDetails: TravelAccommodationDetailsResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'TravelAccommodationDetails'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const travelAccommodationDetailsPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: TravelAccommodationDetailsDeletePopupComponent,
    resolve: {
      travelAccommodationDetails: TravelAccommodationDetailsResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'TravelAccommodationDetails'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
