import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { LeaveEntryHr } from 'app/shared/model/leave-entry-hr.model';
import { LeaveEntryHrService } from './leave-entry-hr.service';
import { LeaveEntryHrComponent } from './leave-entry-hr.component';
import { LeaveEntryHrDetailComponent } from './leave-entry-hr-detail.component';
import { LeaveEntryHrUpdateComponent } from './leave-entry-hr-update.component';
import { LeaveEntryHrDeletePopupComponent } from './leave-entry-hr-delete-dialog.component';
import { ILeaveEntryHr } from 'app/shared/model/leave-entry-hr.model';

@Injectable({ providedIn: 'root' })
export class LeaveEntryHrResolve implements Resolve<ILeaveEntryHr> {
  constructor(private service: LeaveEntryHrService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<LeaveEntryHr> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<LeaveEntryHr>) => response.ok),
        map((leaveEntryHr: HttpResponse<LeaveEntryHr>) => leaveEntryHr.body)
      );
    }
    return of(new LeaveEntryHr());
  }
}

export const leaveEntryHrRoute: Routes = [
  {
    path: '',
    component: LeaveEntryHrComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      defaultSort: 'id,asc',
      pageTitle: 'Leave Entry HR'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: LeaveEntryHrDetailComponent,
    resolve: {
      leaveEntryHr: LeaveEntryHrResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'Leave Entry HR'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: LeaveEntryHrUpdateComponent,
    resolve: {
      leaveEntryHr: LeaveEntryHrResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'Leave Entry HR'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'id/edit',
    component: LeaveEntryHrUpdateComponent,
    resolve: {
      leaveEntryHr: LeaveEntryHrResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'Leave Entry HR'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const leaveEntryHrPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: LeaveEntryHrDeletePopupComponent,
    resolve: {
      leaveEntryHr: LeaveEntryHrResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'Leave Entry HR'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
