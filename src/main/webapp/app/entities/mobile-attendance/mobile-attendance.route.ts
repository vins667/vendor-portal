import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MobileAttendance } from 'app/shared/model/mobile-attendance.model';
import { MobileAttendanceService } from './mobile-attendance.service';
import { MobileAttendanceComponent } from './mobile-attendance.component';
import { MobileAttendanceDetailComponent } from './mobile-attendance-detail.component';
import { MobileAttendanceUpdateComponent } from './mobile-attendance-update.component';
import { MobileAttendanceDeletePopupComponent } from './mobile-attendance-delete-dialog.component';
import { IMobileAttendance } from 'app/shared/model/mobile-attendance.model';

@Injectable({ providedIn: 'root' })
export class MobileAttendanceResolve implements Resolve<IMobileAttendance> {
  constructor(private service: MobileAttendanceService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<MobileAttendance> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MobileAttendance>) => response.ok),
        map((mobileAttendance: HttpResponse<MobileAttendance>) => mobileAttendance.body)
      );
    }
    return of(new MobileAttendance());
  }
}

export const mobileAttendanceRoute: Routes = [
  {
    path: '',
    component: MobileAttendanceComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      defaultSort: 'id,desc',
      pageTitle: 'MobileAttendances'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MobileAttendanceDetailComponent,
    resolve: {
      mobileAttendance: MobileAttendanceResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'MobileAttendances'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MobileAttendanceUpdateComponent,
    resolve: {
      mobileAttendance: MobileAttendanceResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'MobileAttendances'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MobileAttendanceUpdateComponent,
    resolve: {
      mobileAttendance: MobileAttendanceResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'MobileAttendances'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mobileAttendancePopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MobileAttendanceDeletePopupComponent,
    resolve: {
      mobileAttendance: MobileAttendanceResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'MobileAttendances'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
