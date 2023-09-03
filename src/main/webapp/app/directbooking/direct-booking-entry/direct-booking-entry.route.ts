import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IDirectBookingEntry, DirectBookingEntry } from 'app/shared/model/direct-booking-entry.model';
import { DirectBookingEntryService } from './direct-booking-entry.service';
import { DirectBookingEntryComponent } from './direct-booking-entry.component';
import { DirectBookingEntryDetailComponent } from './direct-booking-entry-detail.component';
import { DirectBookingEntryUpdateComponent } from './direct-booking-entry-update.component';

@Injectable({ providedIn: 'root' })
export class DirectBookingEntryResolve implements Resolve<IDirectBookingEntry> {
  constructor(private service: DirectBookingEntryService, private router: Router) {}

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

@Injectable({ providedIn: 'root' })
export class DirectBookingCopyEntryResolve implements Resolve<IDirectBookingEntry> {
  constructor(private service: DirectBookingEntryService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IDirectBookingEntry> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.copy(id).pipe(
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

export const directBookingEntryRoute: Routes = [
  {
    path: '',
    component: DirectBookingEntryComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      defaultSort: 'id,asc',
      pageTitle: 'Direct Booking System'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: DirectBookingEntryDetailComponent,
    resolve: {
      directBookingEntry: DirectBookingEntryResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'Direct Booking System'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: DirectBookingEntryUpdateComponent,
    resolve: {
      directBookingEntry: DirectBookingEntryResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'Direct Booking System'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: DirectBookingEntryUpdateComponent,
    resolve: {
      directBookingEntry: DirectBookingEntryResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'Direct Booking System'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/copy',
    component: DirectBookingEntryUpdateComponent,
    resolve: {
      directBookingEntry: DirectBookingCopyEntryResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'Direct Booking System'
    },
    canActivate: [UserRouteAccessService]
  }
];
