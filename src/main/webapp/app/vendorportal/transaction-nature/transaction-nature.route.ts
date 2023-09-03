import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { TransactionNature } from 'app/shared/model/transaction-nature.model';
import { TransactionNatureService } from './transaction-nature.service';
import { TransactionNatureComponent } from './transaction-nature.component';
import { TransactionNatureDetailComponent } from './transaction-nature-detail.component';
import { TransactionNatureUpdateComponent } from './transaction-nature-update.component';
import { TransactionNatureDeletePopupComponent } from './transaction-nature-delete-dialog.component';
import { ITransactionNature } from 'app/shared/model/transaction-nature.model';

@Injectable({ providedIn: 'root' })
export class TransactionNatureResolve implements Resolve<ITransactionNature> {
  constructor(private service: TransactionNatureService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<TransactionNature> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<TransactionNature>) => response.ok),
        map((transactionNature: HttpResponse<TransactionNature>) => transactionNature.body)
      );
    }
    return of(new TransactionNature());
  }
}

export const transactionNatureRoute: Routes = [
  {
    path: '',
    component: TransactionNatureComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      defaultSort: 'id,asc',
      pageTitle: 'TransactionNatures'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: TransactionNatureDetailComponent,
    resolve: {
      transactionNature: TransactionNatureResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'TransactionNatures'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: TransactionNatureUpdateComponent,
    resolve: {
      transactionNature: TransactionNatureResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'TransactionNatures'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: TransactionNatureUpdateComponent,
    resolve: {
      transactionNature: TransactionNatureResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'TransactionNatures'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const transactionNaturePopupRoute: Routes = [
  {
    path: ':id/delete',
    component: TransactionNatureDeletePopupComponent,
    resolve: {
      transactionNature: TransactionNatureResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'TransactionNatures'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
