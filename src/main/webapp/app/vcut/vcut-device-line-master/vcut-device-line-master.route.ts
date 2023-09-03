import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { VcutDeviceLineMaster } from 'app/shared/model/vcut-device-line-master.model';
import { VcutDeviceLineMasterService } from './vcut-device-line-master.service';
import { VcutDeviceLineMasterComponent } from './vcut-device-line-master.component';
import { VcutDeviceLineMasterDetailComponent } from './vcut-device-line-master-detail.component';
import { VcutDeviceLineMasterUpdateComponent } from './vcut-device-line-master-update.component';
import { VcutDeviceLineMasterDeletePopupComponent } from './vcut-device-line-master-delete-dialog.component';
import { IVcutDeviceLineMaster } from 'app/shared/model/vcut-device-line-master.model';

@Injectable({ providedIn: 'root' })
export class VcutDeviceLineMasterResolve implements Resolve<IVcutDeviceLineMaster> {
  constructor(private service: VcutDeviceLineMasterService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IVcutDeviceLineMaster> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<VcutDeviceLineMaster>) => response.ok),
        map((vcutDeviceLineMaster: HttpResponse<VcutDeviceLineMaster>) => vcutDeviceLineMaster.body)
      );
    }
    return of(new VcutDeviceLineMaster());
  }
}

export const vcutDeviceLineMasterRoute: Routes = [
  {
    path: '',
    component: VcutDeviceLineMasterComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      defaultSort: 'id,asc',
      pageTitle: 'Device User Link'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: VcutDeviceLineMasterDetailComponent,
    resolve: {
      vcutDeviceLineMaster: VcutDeviceLineMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'Device User Link'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: VcutDeviceLineMasterUpdateComponent,
    resolve: {
      vcutDeviceLineMaster: VcutDeviceLineMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'Device User Link'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: VcutDeviceLineMasterUpdateComponent,
    resolve: {
      vcutDeviceLineMaster: VcutDeviceLineMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'Device User Link'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const vcutDeviceLineMasterPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: VcutDeviceLineMasterDeletePopupComponent,
    resolve: {
      vcutDeviceLineMaster: VcutDeviceLineMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'Device User Link'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
