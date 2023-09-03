import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { PreviousEmploymentDetails } from 'app/shared/model/previous-employment-details.model';
import { PreviousEmploymentDetailsService } from './previous-employment-details.service';
import { PreviousEmploymentDetailsComponent } from './previous-employment-details.component';
import { PreviousEmploymentDetailsDetailComponent } from './previous-employment-details-detail.component';
import { PreviousEmploymentDetailsUpdateComponent } from './previous-employment-details-update.component';
import { PreviousEmploymentDetailsDeletePopupComponent } from './previous-employment-details-delete-dialog.component';
import { IPreviousEmploymentDetails } from 'app/shared/model/previous-employment-details.model';

@Injectable({ providedIn: 'root' })
export class PreviousEmploymentDetailsResolve implements Resolve<IPreviousEmploymentDetails> {
  constructor(private service: PreviousEmploymentDetailsService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IPreviousEmploymentDetails> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<PreviousEmploymentDetails>) => response.ok),
        map((previousEmploymentDetails: HttpResponse<PreviousEmploymentDetails>) => previousEmploymentDetails.body)
      );
    }
    return of(new PreviousEmploymentDetails());
  }
}

export const previousEmploymentDetailsRoute: Routes = [
  {
    path: '',
    component: PreviousEmploymentDetailsComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      defaultSort: 'id,asc',
      pageTitle: 'PreviousEmploymentDetails'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: PreviousEmploymentDetailsDetailComponent,
    resolve: {
      previousEmploymentDetails: PreviousEmploymentDetailsResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'PreviousEmploymentDetails'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: PreviousEmploymentDetailsUpdateComponent,
    resolve: {
      previousEmploymentDetails: PreviousEmploymentDetailsResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'PreviousEmploymentDetails'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: PreviousEmploymentDetailsUpdateComponent,
    resolve: {
      previousEmploymentDetails: PreviousEmploymentDetailsResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'PreviousEmploymentDetails'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const previousEmploymentDetailsPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: PreviousEmploymentDetailsDeletePopupComponent,
    resolve: {
      previousEmploymentDetails: PreviousEmploymentDetailsResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'PreviousEmploymentDetails'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
