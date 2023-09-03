import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { TravelApplicationMasterHrService } from './travel-application-master-hr.service';
import { TravelApplicationMasterHrComponent } from './travel-application-master-hr.component';
import { TravelApplicationMasterHrUpdateComponent } from './travel-application-master-hr-update.component';
import { ITravelApplicationMaster, TravelApplicationMaster } from 'app/shared/model/travel-application-master.model';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';

@Injectable({ providedIn: 'root' })
export class TravelApplicationMasterHrResolve implements Resolve<ITravelApplicationMaster> {
  constructor(private service: TravelApplicationMasterHrService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ITravelApplicationMaster> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<TravelApplicationMaster>) => response.ok),
        map((travelApplicationMasterHr: HttpResponse<TravelApplicationMaster>) => travelApplicationMasterHr.body)
      );
    }
    return of(new TravelApplicationMaster());
  }
}

export const travelApplicationMasterHrRoute: Routes = [
  {
    path: '',
    component: TravelApplicationMasterHrComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER', 'ADMIN_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'TravelApplication Master Hr'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: TravelApplicationMasterHrUpdateComponent,
    resolve: {
      travelApplicationMaster: TravelApplicationMasterHrResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ADMIN_USER'],
      pageTitle: 'TravelApplication Master Hr'
    },
    canActivate: [UserRouteAccessService]
  }
];
