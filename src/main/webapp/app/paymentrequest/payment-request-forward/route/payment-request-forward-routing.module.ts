import { Injectable, NgModule } from '@angular/core';
import { ActivatedRouteSnapshot, Resolve, Router, RouterModule, Routes } from '@angular/router';
import { PaymentRequestForwardComponent } from '../list/payment-request-forward.component';
import { PaymentRequestForwardUpdateComponent } from '../update/payment-request-forward-update.component';
import { IPaymentRequestForward, PaymentRequestForward } from 'app/paymentrequest/payment-request-forward/payment-request-forward.model';
import { PaymentRequestForwardService } from '../service/payment-request-forward.service';
import { EMPTY, Observable, of } from 'rxjs';
import { mergeMap } from 'rxjs/operators';
import { HttpResponse } from '@angular/common/http';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { JhiResolvePagingParams } from 'ng-jhipster';

@Injectable({ providedIn: 'root' })
export class PaymentRequestForwardRoutingResolveService implements Resolve<IPaymentRequestForward> {
  constructor(protected service: PaymentRequestForwardService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IPaymentRequestForward> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((paymentRequestForward: HttpResponse<PaymentRequestForward>) => {
          if (paymentRequestForward.body) {
            return of(paymentRequestForward.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new PaymentRequestForward());
  }
}

const paymentRequestForwardRoute: Routes = [
  {
    path: '',
    component: PaymentRequestForwardComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      defaultSort: 'id,asc',
      pageTitle: 'Payment Request Forward'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: PaymentRequestForwardUpdateComponent,
    resolve: {
      paymentRequestForward: PaymentRequestForwardRoutingResolveService
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'Payment Request Forward'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: PaymentRequestForwardUpdateComponent,
    resolve: {
      paymentRequestForward: PaymentRequestForwardRoutingResolveService
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'Payment Request Forward'
    },
    canActivate: [UserRouteAccessService]
  }
];

@NgModule({
  imports: [RouterModule.forChild(paymentRequestForwardRoute)],
  exports: [RouterModule]
})
export class PaymentRequestForwardRoutingModule {}
