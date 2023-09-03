import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { TermsConditionMaster } from 'app/shared/model/terms-condition-master.model';
import { TermsConditionMasterService } from './terms-condition-master.service';
import { TermsConditionMasterComponent } from './terms-condition-master.component';
import { TermsConditionMasterDetailComponent } from './terms-condition-master-detail.component';
import { TermsConditionMasterUpdateComponent } from './terms-condition-master-update.component';
import { TermsConditionMasterDeletePopupComponent } from './terms-condition-master-delete-dialog.component';
import { ITermsConditionMaster } from 'app/shared/model/terms-condition-master.model';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';

@Injectable({ providedIn: 'root' })
export class TermsConditionMasterResolve implements Resolve<ITermsConditionMaster> {
  constructor(private service: TermsConditionMasterService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ITermsConditionMaster> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<TermsConditionMaster>) => response.ok),
        map((termsConditionMaster: HttpResponse<TermsConditionMaster>) => termsConditionMaster.body)
      );
    }
    return of(new TermsConditionMaster());
  }
}

export const termsConditionMasterRoute: Routes = [
  {
    path: '',
    component: TermsConditionMasterComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      defaultSort: 'id,asc',
      pageTitle: 'TermsConditionMaster'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: TermsConditionMasterDetailComponent,
    resolve: {
      termsConditionMaster: TermsConditionMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'TermsConditionMaster'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: TermsConditionMasterUpdateComponent,
    resolve: {
      termsConditionMaster: TermsConditionMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'TermsConditionMaster'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: TermsConditionMasterUpdateComponent,
    resolve: {
      termsConditionMaster: TermsConditionMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'TermsConditionMaster'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const termsConditionMasterPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: TermsConditionMasterDeletePopupComponent,
    resolve: {
      termsConditionMaster: TermsConditionMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'TermsConditionMaster'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
