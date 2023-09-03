import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { DepartmentMaster } from 'app/shared/model/department-master.model';
import { DepartmentMasterService } from './department-master.service';
import { DepartmentMasterComponent } from './department-master.component';
import { DepartmentMasterDetailComponent } from './department-master-detail.component';
import { DepartmentMasterUpdateComponent } from './department-master-update.component';
import { DepartmentMasterDeletePopupComponent } from './department-master-delete-dialog.component';
import { IDepartmentMaster } from 'app/shared/model/department-master.model';

@Injectable({ providedIn: 'root' })
export class DepartmentMasterResolve implements Resolve<IDepartmentMaster> {
  constructor(private service: DepartmentMasterService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<DepartmentMaster> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<DepartmentMaster>) => response.ok),
        map((departmentMaster: HttpResponse<DepartmentMaster>) => departmentMaster.body)
      );
    }
    return of(new DepartmentMaster());
  }
}

export const departmentMasterRoute: Routes = [
  {
    path: '',
    component: DepartmentMasterComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      defaultSort: 'id,asc',
      pageTitle: 'DepartmentMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: DepartmentMasterDetailComponent,
    resolve: {
      departmentMaster: DepartmentMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'DepartmentMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: DepartmentMasterUpdateComponent,
    resolve: {
      departmentMaster: DepartmentMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'DepartmentMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: DepartmentMasterUpdateComponent,
    resolve: {
      departmentMaster: DepartmentMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'DepartmentMasters'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const departmentMasterPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: DepartmentMasterDeletePopupComponent,
    resolve: {
      departmentMaster: DepartmentMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'DepartmentMasters'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
