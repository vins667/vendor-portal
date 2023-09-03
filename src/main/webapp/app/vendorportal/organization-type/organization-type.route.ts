import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { OrganizationType } from 'app/shared/model/organization-type.model';
import { OrganizationTypeService } from './organization-type.service';
import { OrganizationTypeComponent } from './organization-type.component';
import { OrganizationTypeDetailComponent } from './organization-type-detail.component';
import { OrganizationTypeUpdateComponent } from './organization-type-update.component';
import { OrganizationTypeDeletePopupComponent } from './organization-type-delete-dialog.component';
import { IOrganizationType } from 'app/shared/model/organization-type.model';

@Injectable({ providedIn: 'root' })
export class OrganizationTypeResolve implements Resolve<IOrganizationType> {
  constructor(private service: OrganizationTypeService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<OrganizationType> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<OrganizationType>) => response.ok),
        map((organizationType: HttpResponse<OrganizationType>) => organizationType.body)
      );
    }
    return of(new OrganizationType());
  }
}

export const organizationTypeRoute: Routes = [
  {
    path: '',
    component: OrganizationTypeComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      defaultSort: 'id,asc',
      pageTitle: 'OrganizationTypes'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: OrganizationTypeDetailComponent,
    resolve: {
      organizationType: OrganizationTypeResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'OrganizationTypes'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: OrganizationTypeUpdateComponent,
    resolve: {
      organizationType: OrganizationTypeResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'OrganizationTypes'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: OrganizationTypeUpdateComponent,
    resolve: {
      organizationType: OrganizationTypeResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'OrganizationTypes'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const organizationTypePopupRoute: Routes = [
  {
    path: ':id/delete',
    component: OrganizationTypeDeletePopupComponent,
    resolve: {
      organizationType: OrganizationTypeResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'OrganizationTypes'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
