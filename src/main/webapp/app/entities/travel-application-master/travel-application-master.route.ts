import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { TravelApplicationMaster } from 'app/shared/model/travel-application-master.model';
import { TravelApplicationMasterService } from './travel-application-master.service';
import { TravelApplicationMasterComponent } from './travel-application-master.component';
import { TravelApplicationMasterDetailComponent } from './travel-application-master-detail.component';
import { TravelApplicationMasterUpdateComponent } from './travel-application-master-update.component';
import { TravelApplicationMasterDeletePopupComponent } from './travel-application-master-delete-dialog.component';
import { ITravelApplicationMaster } from 'app/shared/model/travel-application-master.model';

@Injectable({ providedIn: 'root' })
export class TravelApplicationMasterResolve implements Resolve<ITravelApplicationMaster> {
  constructor(private service: TravelApplicationMasterService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ITravelApplicationMaster> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<TravelApplicationMaster>) => response.ok),
        map((travelApplicationMaster: HttpResponse<TravelApplicationMaster>) => travelApplicationMaster.body)
      );
    }
    return of(new TravelApplicationMaster());
  }
}

export const travelApplicationMasterRoute: Routes = [
  {
    path: '',
    component: TravelApplicationMasterComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      defaultSort: 'id,asc',
      pageTitle: 'TravelApplicationMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: TravelApplicationMasterDetailComponent,
    resolve: {
      travelApplicationMaster: TravelApplicationMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'TravelApplicationMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: TravelApplicationMasterUpdateComponent,
    resolve: {
      travelApplicationMaster: TravelApplicationMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'TravelApplicationMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: TravelApplicationMasterUpdateComponent,
    resolve: {
      travelApplicationMaster: TravelApplicationMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'TravelApplicationMasters'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const travelApplicationMasterPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: TravelApplicationMasterDeletePopupComponent,
    resolve: {
      travelApplicationMaster: TravelApplicationMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'TravelApplicationMasters'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
