import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { FinPaymentAdviceService } from './fin-payment-advice.service';
import { FinPaymentAdviceComponent } from './fin-payment-advice.component';
import { IViewDifindocumentpaymentadvice, ViewDifindocumentpaymentadvice } from './view-difindocumentpaymentadvice.model';
import { FinPaymentAdviceUploadComponent } from 'app/finance/fin-payment-advice/fin-payment-advice-upload.component';

@Injectable({ providedIn: 'root' })
export class FinPaymentAdviceResolve implements Resolve<IViewDifindocumentpaymentadvice> {
  constructor(private service: FinPaymentAdviceService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IViewDifindocumentpaymentadvice> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((finPaymentAdvice: HttpResponse<IViewDifindocumentpaymentadvice>) => {
          if (finPaymentAdvice.body) {
            return of(finPaymentAdvice.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new ViewDifindocumentpaymentadvice());
  }
}

export const finPaymentAdviceRoute: Routes = [
  {
    path: '',
    component: FinPaymentAdviceComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      defaultSort: 'id,asc',
      pageTitle: 'Payment Advice'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'upload',
    component: FinPaymentAdviceUploadComponent,
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN']
    },
    canActivate: [UserRouteAccessService]
  }
];
