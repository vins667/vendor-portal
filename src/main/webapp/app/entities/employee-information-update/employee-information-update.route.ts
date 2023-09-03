import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { EmployeeInformationUpdate } from 'app/shared/model/employee-information-update.model';
import { EmployeeInformationUpdateService } from './employee-information-update.service';
import { EmployeeInformationUpdateComponent } from './employee-information-update.component';
import { EmployeeInformationUpdateDetailComponent } from './employee-information-update-detail.component';
import { EmployeeInformationUpdateUpdateComponent } from './employee-information-update-update.component';
import { EmployeeInformationUpdateDeletePopupComponent } from './employee-information-update-delete-dialog.component';
import { IEmployeeInformationUpdate } from 'app/shared/model/employee-information-update.model';

@Injectable({ providedIn: 'root' })
export class EmployeeInformationUpdateResolve implements Resolve<IEmployeeInformationUpdate> {
  constructor(private service: EmployeeInformationUpdateService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<EmployeeInformationUpdate> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<EmployeeInformationUpdate>) => response.ok),
        map((employeeInformationUpdate: HttpResponse<EmployeeInformationUpdate>) => employeeInformationUpdate.body)
      );
    }
    return of(new EmployeeInformationUpdate());
  }
}

export const employeeInformationUpdateRoute: Routes = [
  {
    path: '',
    component: EmployeeInformationUpdateComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      defaultSort: 'id,asc',
      pageTitle: 'EmployeeInformationUpdates'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: EmployeeInformationUpdateDetailComponent,
    resolve: {
      employeeInformationUpdate: EmployeeInformationUpdateResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'EmployeeInformationUpdates'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: EmployeeInformationUpdateUpdateComponent,
    resolve: {
      employeeInformationUpdate: EmployeeInformationUpdateResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'EmployeeInformationUpdates'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: EmployeeInformationUpdateUpdateComponent,
    resolve: {
      employeeInformationUpdate: EmployeeInformationUpdateResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'EmployeeInformationUpdates'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const employeeInformationUpdatePopupRoute: Routes = [
  {
    path: ':id/delete',
    component: EmployeeInformationUpdateDeletePopupComponent,
    resolve: {
      employeeInformationUpdate: EmployeeInformationUpdateResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'EmployeeInformationUpdates'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
