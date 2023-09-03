import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Policies } from 'app/shared/model/policies.model';
import { PoliciesService } from './policies.service';
import { PoliciesComponent } from './policies.component';
import { IPolicies } from 'app/shared/model/policies.model';

@Injectable({ providedIn: 'root' })
export class PoliciesResolve implements Resolve<IPolicies> {
  constructor(private service: PoliciesService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Policies> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<Policies>) => response.ok),
        map((policies: HttpResponse<Policies>) => policies.body)
      );
    }
    return of(new Policies());
  }
}

export const policiesRoute: Routes = [
  {
    path: '',
    component: PoliciesComponent,
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'Policies'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const policiesPopupRoute: Routes = [];
