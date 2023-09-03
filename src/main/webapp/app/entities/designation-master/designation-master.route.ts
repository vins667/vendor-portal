import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { DesignationMaster } from 'app/shared/model/designation-master.model';
import { DesignationMasterService } from './designation-master.service';
import { DesignationMasterComponent } from './designation-master.component';
import { DesignationMasterDetailComponent } from './designation-master-detail.component';
import { DesignationMasterUpdateComponent } from './designation-master-update.component';
import { DesignationMasterDeletePopupComponent } from './designation-master-delete-dialog.component';
import { IDesignationMaster } from 'app/shared/model/designation-master.model';

@Injectable({ providedIn: 'root' })
export class DesignationMasterResolve implements Resolve<IDesignationMaster> {
  constructor(private service: DesignationMasterService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IDesignationMaster> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<DesignationMaster>) => response.ok),
        map((designationMaster: HttpResponse<DesignationMaster>) => designationMaster.body)
      );
    }
    return of(new DesignationMaster());
  }
}

export const designationMasterRoute: Routes = [
  {
    path: '',
    component: DesignationMasterComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      defaultSort: 'id,asc',
      pageTitle: 'DesignationMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: DesignationMasterDetailComponent,
    resolve: {
      designationMaster: DesignationMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'DesignationMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: DesignationMasterUpdateComponent,
    resolve: {
      designationMaster: DesignationMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'DesignationMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: DesignationMasterUpdateComponent,
    resolve: {
      designationMaster: DesignationMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'DesignationMasters'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const designationMasterPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: DesignationMasterDeletePopupComponent,
    resolve: {
      designationMaster: DesignationMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'DesignationMasters'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
