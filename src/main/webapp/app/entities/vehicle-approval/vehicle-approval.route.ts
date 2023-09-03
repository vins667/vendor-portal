import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { VehicleMaster } from 'app/shared/model/vehicle-master.model';
import { VehicleApprovalService } from './vehicle-approval.service';
import { VehicleApprovalComponent } from './vehicle-approval.component';
import { IVehicleMaster } from 'app/shared/model/vehicle-master.model';

@Injectable({ providedIn: 'root' })
export class VehicleMasterResolve implements Resolve<IVehicleMaster> {
  constructor(private service: VehicleApprovalService) {}

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

export const vehicleApprovalRoute: Routes = [
  {
    path: '',
    component: VehicleApprovalComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      defaultSort: 'id,dsc',
      pageTitle: 'Vehicle Approval HOD'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const vehicleApprovalPopupRoute: Routes = [];
