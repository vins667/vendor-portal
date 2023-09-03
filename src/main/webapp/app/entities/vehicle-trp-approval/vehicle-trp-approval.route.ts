import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { VehicleMaster } from 'app/shared/model/vehicle-master.model';
import { VehicleTrpApprovalService } from './vehicle-trp-approval.service';
import { VehicleTrpApprovalComponent } from './vehicle-trp-approval.component';
import { VehicleTrpUpdateComponent } from './vehicle-trp-update.component';
import { IVehicleMaster } from 'app/shared/model/vehicle-master.model';

@Injectable({ providedIn: 'root' })
export class VehicleMasterResolve implements Resolve<IVehicleMaster> {
  constructor(private service: VehicleTrpApprovalService) {}

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

export const vehicleTrpApprovalRoute: Routes = [
  {
    path: '',
    component: VehicleTrpApprovalComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      defaultSort: 'id,dsc',
      pageTitle: 'Vehicle Approval Transport'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: VehicleTrpUpdateComponent,
    resolve: {
      vehicleMaster: VehicleMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'Vehicle Approval Transport'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const vehicleTrpApprovalPopupRoute: Routes = [];
