import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { OccupationMaster } from 'app/shared/model/occupation-master.model';
import { OccupationMasterService } from './occupation-master.service';
import { OccupationMasterComponent } from './occupation-master.component';
import { OccupationMasterDetailComponent } from './occupation-master-detail.component';
import { OccupationMasterUpdateComponent } from './occupation-master-update.component';
import { OccupationMasterDeletePopupComponent } from './occupation-master-delete-dialog.component';
import { IOccupationMaster } from 'app/shared/model/occupation-master.model';

@Injectable({ providedIn: 'root' })
export class OccupationMasterResolve implements Resolve<IOccupationMaster> {
  constructor(private service: OccupationMasterService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<OccupationMaster> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<OccupationMaster>) => response.ok),
        map((occupationMaster: HttpResponse<OccupationMaster>) => occupationMaster.body)
      );
    }
    return of(new OccupationMaster());
  }
}

export const occupationMasterRoute: Routes = [
  {
    path: '',
    component: OccupationMasterComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      defaultSort: 'id,asc',
      pageTitle: 'OccupationMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: OccupationMasterDetailComponent,
    resolve: {
      occupationMaster: OccupationMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'OccupationMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: OccupationMasterUpdateComponent,
    resolve: {
      occupationMaster: OccupationMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'OccupationMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: OccupationMasterUpdateComponent,
    resolve: {
      occupationMaster: OccupationMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'OccupationMasters'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const occupationMasterPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: OccupationMasterDeletePopupComponent,
    resolve: {
      occupationMaster: OccupationMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'OccupationMasters'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
