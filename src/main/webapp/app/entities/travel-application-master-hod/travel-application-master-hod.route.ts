import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { TravelApplicationMasterHodService } from './travel-application-master-hod.service';
import { TravelApplicationMasterHodComponent } from './travel-application-master-hod.component';
import { TravelApplicationMasterUpdateHodComponent } from './travel-application-master-update-hod.component';
import { ITravelApplicationMaster, TravelApplicationMaster } from 'app/shared/model/travel-application-master.model';

@Injectable({ providedIn: 'root' })
export class TravelApplicationMasterHodResolve implements Resolve<ITravelApplicationMaster> {
  constructor(private service: TravelApplicationMasterHodService) {}

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

export const travelApplicationMasterHodRoute: Routes = [
  {
    path: '',
    component: TravelApplicationMasterHodComponent,
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
    path: ':id/edit',
    component: TravelApplicationMasterUpdateHodComponent,
    resolve: {
      travelApplicationMasterHod: TravelApplicationMasterHodResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'TravelApplicationMasters'
    },
    canActivate: [UserRouteAccessService]
  }
];
