import { Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { EmployeeHierarchyComponent } from './employee-hierarchy.component';

export const employeeHierarchyRoute: Routes = [
  {
    path: '',
    component: EmployeeHierarchyComponent,
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN']
    },
    canActivate: [UserRouteAccessService]
  }
];
