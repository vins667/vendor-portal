import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { TaxTermMaster } from 'app/shared/model/tax-term-master.model';
import { TaxTermMasterService } from './tax-term-master.service';
import { TaxTermMasterComponent } from './tax-term-master.component';
import { TaxTermMasterDetailComponent } from './tax-term-master-detail.component';
import { TaxTermMasterUpdateComponent } from './tax-term-master-update.component';
import { TaxTermMasterDeletePopupComponent } from './tax-term-master-delete-dialog.component';
import { ITaxTermMaster } from 'app/shared/model/tax-term-master.model';

@Injectable({ providedIn: 'root' })
export class TaxTermMasterResolve implements Resolve<ITaxTermMaster> {
  constructor(private service: TaxTermMasterService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ITaxTermMaster> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<TaxTermMaster>) => response.ok),
        map((taxTermMaster: HttpResponse<TaxTermMaster>) => taxTermMaster.body)
      );
    }
    return of(new TaxTermMaster());
  }
}

export const taxTermMasterRoute: Routes = [
  {
    path: '',
    component: TaxTermMasterComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_ADMIN', 'ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'TaxTermMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: TaxTermMasterDetailComponent,
    resolve: {
      taxTermMaster: TaxTermMasterResolve
    },
    data: {
      authorities: ['ROLE_ADMIN', 'ROLE_USER'],
      pageTitle: 'TaxTermMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: TaxTermMasterUpdateComponent,
    resolve: {
      taxTermMaster: TaxTermMasterResolve
    },
    data: {
      authorities: ['ROLE_ADMIN', 'ROLE_USER'],
      pageTitle: 'TaxTermMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: TaxTermMasterUpdateComponent,
    resolve: {
      taxTermMaster: TaxTermMasterResolve
    },
    data: {
      authorities: ['ROLE_ADMIN', 'ROLE_USER'],
      pageTitle: 'TaxTermMasters'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const taxTermMasterPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: TaxTermMasterDeletePopupComponent,
    resolve: {
      taxTermMaster: TaxTermMasterResolve
    },
    data: {
      authorities: ['ROLE_ADMIN', 'ROLE_USER'],
      pageTitle: 'TaxTermMasters'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
