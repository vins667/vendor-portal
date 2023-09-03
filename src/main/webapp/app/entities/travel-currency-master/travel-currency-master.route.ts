import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { TravelCurrencyMaster } from 'app/shared/model/travel-currency-master.model';
import { TravelCurrencyMasterService } from './travel-currency-master.service';
import { TravelCurrencyMasterComponent } from './travel-currency-master.component';
import { TravelCurrencyMasterDetailComponent } from './travel-currency-master-detail.component';
import { TravelCurrencyMasterUpdateComponent } from './travel-currency-master-update.component';
import { TravelCurrencyMasterDeletePopupComponent } from './travel-currency-master-delete-dialog.component';
import { ITravelCurrencyMaster } from 'app/shared/model/travel-currency-master.model';

@Injectable({ providedIn: 'root' })
export class TravelCurrencyMasterResolve implements Resolve<ITravelCurrencyMaster> {
  constructor(private service: TravelCurrencyMasterService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ITravelCurrencyMaster> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<TravelCurrencyMaster>) => response.ok),
        map((travelCurrencyMaster: HttpResponse<TravelCurrencyMaster>) => travelCurrencyMaster.body)
      );
    }
    return of(new TravelCurrencyMaster());
  }
}

export const travelCurrencyMasterRoute: Routes = [
  {
    path: '',
    component: TravelCurrencyMasterComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      defaultSort: 'id,asc',
      pageTitle: 'TravelCurrencyMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: TravelCurrencyMasterDetailComponent,
    resolve: {
      travelCurrencyMaster: TravelCurrencyMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'TravelCurrencyMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: TravelCurrencyMasterUpdateComponent,
    resolve: {
      travelCurrencyMaster: TravelCurrencyMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'TravelCurrencyMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: TravelCurrencyMasterUpdateComponent,
    resolve: {
      travelCurrencyMaster: TravelCurrencyMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'TravelCurrencyMasters'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const travelCurrencyMasterPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: TravelCurrencyMasterDeletePopupComponent,
    resolve: {
      travelCurrencyMaster: TravelCurrencyMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'TravelCurrencyMasters'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
