import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { SupervisorEmployeeDetails } from 'app/shared/model/supervisor-employee-details.model';
import { SupervisorEmployeeDetailsService } from './supervisor-employee-details.service';
import { SupervisorEmployeeDetailsUpdateComponent } from './supervisor-employee-details-update.component';
import { ISupervisorEmployeeDetails } from 'app/shared/model/supervisor-employee-details.model';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';

@Injectable({ providedIn: 'root' })
export class SupervisorEmployeeDetailsResolve implements Resolve<ISupervisorEmployeeDetails> {
  constructor(private service: SupervisorEmployeeDetailsService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ISupervisorEmployeeDetails> {
    const id = route.params['id'] ? route.params['id'] : null;
    return of(new SupervisorEmployeeDetails());
  }
}

export const supervisorEmployeeDetailsRoute: Routes = [
  {
    path: '',
    component: SupervisorEmployeeDetailsUpdateComponent,
    resolve: {
      supervisorEmployeeDetails: SupervisorEmployeeDetailsResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'SupervisorEmployeeDetails'
    },
    canActivate: [UserRouteAccessService]
  }
];
