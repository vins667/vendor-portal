import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, Resolve, RouterStateSnapshot, Routes } from '@angular/router';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { HttpResponse } from '@angular/common/http';
import { ITransactionUpload, TransactionUpload } from 'app/entities/transaction-upload/transaction-upload.model';
import { TransactionUploadService } from 'app/entities/transaction-upload/transaction-upload.service';
import { TransactionUploadComponent } from 'app/entities/transaction-upload/transaction-upload.component';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { TransactionUploadDetailsComponent } from 'app/entities/transaction-upload/transaction-upload-details.component';

@Injectable({ providedIn: 'root' })
export class TransactionUploadResolve implements Resolve<ITransactionUpload> {
  constructor(private service: TransactionUploadService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ITransactionUpload> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<TransactionUpload>) => response.ok),
        map((transactionUpload: HttpResponse<ITransactionUpload>) => transactionUpload.body)
      );
    }
    return of(new TransactionUpload());
  }
}

export const transactionUploadRoute: Routes = [
  {
    path: '',
    component: TransactionUploadComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      defaultSort: 'id,desc',
      pageTitle: 'Transaction Details'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'upload',
    component: TransactionUploadDetailsComponent,
    resolve: {
      transactionUpload: TransactionUploadResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'Details Upload'
    },
    canActivate: [UserRouteAccessService]
  }
  // {
  //   path: ':id/edit',
  //   component: TransactionUploadUpdateComponent,
  //   resolve: {
  //     transactionUpload: TransactionUploadResolve
  //   },
  //   data: {
  //     authorities: ['ROLE_USER', 'ROLE_ADMIN'],
  //     pageTitle: 'Details Upload'
  //   },
  //   canActivate: [UserRouteAccessService]
  // }
];
