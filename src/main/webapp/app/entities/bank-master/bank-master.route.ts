import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { BankMaster } from 'app/shared/model/bank-master.model';
import { BankMasterService } from './bank-master.service';
import { BankMasterComponent } from './bank-master.component';
import { BankMasterDetailComponent } from './bank-master-detail.component';
import { BankMasterUpdateComponent } from './bank-master-update.component';
import { BankMasterDeletePopupComponent } from './bank-master-delete-dialog.component';
import { IBankMaster } from 'app/shared/model/bank-master.model';

@Injectable({ providedIn: 'root' })
export class BankMasterResolve implements Resolve<IBankMaster> {
  constructor(private service: BankMasterService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IBankMaster> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<BankMaster>) => response.ok),
        map((bankMaster: HttpResponse<BankMaster>) => bankMaster.body)
      );
    }
    return of(new BankMaster());
  }
}

export const bankMasterRoute: Routes = [
  {
    path: '',
    component: BankMasterComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      defaultSort: 'id,asc',
      pageTitle: 'BankMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: BankMasterDetailComponent,
    resolve: {
      bankMaster: BankMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'BankMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: BankMasterUpdateComponent,
    resolve: {
      bankMaster: BankMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'BankMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: BankMasterUpdateComponent,
    resolve: {
      bankMaster: BankMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'BankMasters'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const bankMasterPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: BankMasterDeletePopupComponent,
    resolve: {
      bankMaster: BankMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'BankMasters'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
