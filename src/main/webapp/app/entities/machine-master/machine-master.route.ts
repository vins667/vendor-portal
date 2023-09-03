import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MachineMaster } from 'app/shared/model/machine-master.model';
import { MachineMasterService } from './machine-master.service';
import { MachineMasterComponent } from './machine-master.component';
import { MachineMasterDetailComponent } from './machine-master-detail.component';
import { MachineMasterUpdateComponent } from './machine-master-update.component';
import { MachineMasterDeletePopupComponent } from './machine-master-delete-dialog.component';
import { IMachineMaster } from 'app/shared/model/machine-master.model';

@Injectable({ providedIn: 'root' })
export class MachineMasterResolve implements Resolve<IMachineMaster> {
  constructor(private service: MachineMasterService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMachineMaster> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MachineMaster>) => response.ok),
        map((machineMaster: HttpResponse<MachineMaster>) => machineMaster.body)
      );
    }
    return of(new MachineMaster());
  }
}

export const machineMasterRoute: Routes = [
  {
    path: '',
    component: MachineMasterComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      defaultSort: 'id,asc',
      pageTitle: 'MachineMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MachineMasterDetailComponent,
    resolve: {
      machineMaster: MachineMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'MachineMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MachineMasterUpdateComponent,
    resolve: {
      machineMaster: MachineMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'MachineMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MachineMasterUpdateComponent,
    resolve: {
      machineMaster: MachineMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'MachineMasters'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const machineMasterPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MachineMasterDeletePopupComponent,
    resolve: {
      machineMaster: MachineMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'MachineMasters'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
