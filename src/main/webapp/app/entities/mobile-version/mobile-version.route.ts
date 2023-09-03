import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MobileVersion } from 'app/shared/model/mobile-version.model';
import { MobileVersionService } from './mobile-version.service';
import { MobileVersionComponent } from './mobile-version.component';
import { MobileVersionDetailComponent } from './mobile-version-detail.component';
import { MobileVersionUpdateComponent } from './mobile-version-update.component';
import { MobileVersionDeletePopupComponent } from './mobile-version-delete-dialog.component';
import { IMobileVersion } from 'app/shared/model/mobile-version.model';

@Injectable({ providedIn: 'root' })
export class MobileVersionResolve implements Resolve<IMobileVersion> {
  constructor(private service: MobileVersionService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<MobileVersion> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MobileVersion>) => response.ok),
        map((mobileVersion: HttpResponse<MobileVersion>) => mobileVersion.body)
      );
    }
    return of(new MobileVersion());
  }
}

export const mobileVersionRoute: Routes = [
  {
    path: '',
    component: MobileVersionComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      defaultSort: 'id,asc',
      pageTitle: 'Mobile Versions'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MobileVersionDetailComponent,
    resolve: {
      mobileVersion: MobileVersionResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'Mobile Versions'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MobileVersionUpdateComponent,
    resolve: {
      mobileVersion: MobileVersionResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'Mobile Versions'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MobileVersionUpdateComponent,
    resolve: {
      mobileVersion: MobileVersionResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'Mobile Versions'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mobileVersionPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MobileVersionDeletePopupComponent,
    resolve: {
      mobileVersion: MobileVersionResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'Mobile Versions'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
