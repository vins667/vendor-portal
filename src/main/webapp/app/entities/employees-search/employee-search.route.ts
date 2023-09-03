import { Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { EmployeeSearchComponent } from './employee-search.component';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';

export const employeeSearchRoute: Routes = [
  {
    path: '',
    component: EmployeeSearchComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      defaultSort: 'id,asc',
      pageTitle: 'Employees Search'
    },
    canActivate: [UserRouteAccessService]
  }
];
export const employeeSearchPopupRoute: Routes = [{}];
