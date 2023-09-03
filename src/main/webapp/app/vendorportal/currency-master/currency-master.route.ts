import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { CurrencyMaster } from 'app/shared/model/currency-master.model';
import { CurrencyMasterService } from './currency-master.service';
import { CurrencyMasterComponent } from './currency-master.component';
import { CurrencyMasterDetailComponent } from './currency-master-detail.component';
import { CurrencyMasterUpdateComponent } from './currency-master-update.component';
import { CurrencyMasterDeletePopupComponent } from './currency-master-delete-dialog.component';
import { ICurrencyMaster } from 'app/shared/model/currency-master.model';

@Injectable({ providedIn: 'root' })
export class CurrencyMasterResolve implements Resolve<ICurrencyMaster> {
  constructor(private service: CurrencyMasterService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ICurrencyMaster> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<CurrencyMaster>) => response.ok),
        map((currencyMaster: HttpResponse<CurrencyMaster>) => currencyMaster.body)
      );
    }
    return of(new CurrencyMaster());
  }
}

export const currencyMasterRoute: Routes = [
  {
    path: '',
    component: CurrencyMasterComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_ADMIN', 'ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'CurrencyMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: CurrencyMasterDetailComponent,
    resolve: {
      currencyMaster: CurrencyMasterResolve
    },
    data: {
      authorities: ['ROLE_ADMIN', 'ROLE_USER'],
      pageTitle: 'CurrencyMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: CurrencyMasterUpdateComponent,
    resolve: {
      currencyMaster: CurrencyMasterResolve
    },
    data: {
      authorities: ['ROLE_ADMIN', 'ROLE_USER'],
      pageTitle: 'CurrencyMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: CurrencyMasterUpdateComponent,
    resolve: {
      currencyMaster: CurrencyMasterResolve
    },
    data: {
      authorities: ['ROLE_ADMIN', 'ROLE_USER'],
      pageTitle: 'CurrencyMasters'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const currencyMasterPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: CurrencyMasterDeletePopupComponent,
    resolve: {
      currencyMaster: CurrencyMasterResolve
    },
    data: {
      authorities: ['ROLE_ADMIN', 'ROLE_USER'],
      pageTitle: 'CurrencyMasters'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
