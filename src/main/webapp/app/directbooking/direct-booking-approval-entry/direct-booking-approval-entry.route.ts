import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IDirectBookingEntry, DirectBookingEntry } from 'app/shared/model/direct-booking-entry.model';
import { DirectBookingApprovalEntryService } from './direct-booking-approval-entry.service';
import { DirectBookingApprovalEntryComponent } from './direct-booking-approval-entry.component';
import { DirectBookingApprovalEntryUpdateComponent } from './direct-booking-approval-entry-update.component';

@Injectable({ providedIn: 'root' })
export class DirectBookingEntryResolve implements Resolve<IDirectBookingEntry> {
  constructor(private service: DirectBookingApprovalEntryService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IDirectBookingEntry> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((directBookingEntry: HttpResponse<DirectBookingEntry>) => {
          if (directBookingEntry.body) {
            return of(directBookingEntry.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new DirectBookingEntry());
  }
}

export const directBookingApprovalEntryRoute: Routes = [
  {
    path: '',
    component: DirectBookingApprovalEntryComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      defaultSort: 'id,asc',
      pageTitle: 'DirectBookingEntries'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: DirectBookingApprovalEntryUpdateComponent,
    resolve: {
      directBookingEntry: DirectBookingEntryResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'DirectBookingEntries'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: DirectBookingApprovalEntryUpdateComponent,
    resolve: {
      directBookingEntry: DirectBookingEntryResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'DirectBookingEntries'
    },
    canActivate: [UserRouteAccessService]
  }
];
