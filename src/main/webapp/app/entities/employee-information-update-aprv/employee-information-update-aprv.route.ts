import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { EmployeeInformationUpdateAprv } from 'app/shared/model/employee-information-update-aprv.model';
import { EmployeeInformationUpdateAprvService } from './employee-information-update-aprv.service';
import { EmployeeInformationUpdateAprvComponent } from './employee-information-update-aprv.component';
import { EmployeeInformationUpdateAprvDetailComponent } from './employee-information-update-aprv-detail.component';
import { EmployeeInformationUpdateAprvUpdateComponent } from './employee-information-update-aprv-update.component';
import { IEmployeeInformationUpdateAprv } from 'app/shared/model/employee-information-update-aprv.model';

@Injectable({ providedIn: 'root' })
export class EmployeeInformationUpdateAprvResolve implements Resolve<IEmployeeInformationUpdateAprv> {
  constructor(private service: EmployeeInformationUpdateAprvService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<EmployeeInformationUpdateAprv> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<EmployeeInformationUpdateAprv>) => response.ok),
        map((employeeInformationUpdateAprv: HttpResponse<EmployeeInformationUpdateAprv>) => employeeInformationUpdateAprv.body)
      );
    }
    return of(new EmployeeInformationUpdateAprv());
  }
}

export const employeeInformationUpdateAprvRoute: Routes = [
  {
    path: '',
    component: EmployeeInformationUpdateAprvComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      defaultSort: 'id,asc',
      pageTitle: 'Employee Information Update Approval'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: EmployeeInformationUpdateAprvDetailComponent,
    resolve: {
      employeeInformationUpdateAprv: EmployeeInformationUpdateAprvResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'Employee Information Update Approval'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: EmployeeInformationUpdateAprvUpdateComponent,
    resolve: {
      employeeInformationUpdateAprv: EmployeeInformationUpdateAprvResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'Employee Information Update Approval'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: EmployeeInformationUpdateAprvUpdateComponent,
    resolve: {
      employeeInformationUpdateAprv: EmployeeInformationUpdateAprvResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'Employee Information Update Approval'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const employeeInformationUpdateAprvPopupRoute: Routes = [{}];
