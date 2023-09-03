import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { VehicleMaster } from 'app/shared/model/vehicle-master.model';
import { VehicleMasterService } from './vehicle-master.service';
import { VehicleMasterComponent } from './vehicle-master.component';
import { VehicleMasterDetailComponent } from './vehicle-master-detail.component';
import { VehicleMasterUpdateComponent } from './vehicle-master-update.component';
import { VehicleMasterDeletePopupComponent } from './vehicle-master-delete-dialog.component';
import { IVehicleMaster } from 'app/shared/model/vehicle-master.model';

@Injectable({ providedIn: 'root' })
export class VehicleMasterResolve implements Resolve<IVehicleMaster> {
  constructor(private service: VehicleMasterService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<VehicleMaster> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<VehicleMaster>) => response.ok),
        map((vehicleMaster: HttpResponse<VehicleMaster>) => vehicleMaster.body)
      );
    }
    return of(new VehicleMaster());
  }
}

export const vehicleMasterRoute: Routes = [
  {
    path: '',
    component: VehicleMasterComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      defaultSort: 'id,dsc',
      pageTitle: 'Vehicle Entry'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: VehicleMasterDetailComponent,
    resolve: {
      vehicleMaster: VehicleMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'Vehicle Entry View'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: VehicleMasterUpdateComponent,
    resolve: {
      vehicleMaster: VehicleMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'Vehicle Entry'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: VehicleMasterUpdateComponent,
    resolve: {
      vehicleMaster: VehicleMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'Vehicle Entry Edit'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const vehicleMasterPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: VehicleMasterDeletePopupComponent,
    resolve: {
      vehicleMaster: VehicleMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'Vehicle Entry Delete'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
