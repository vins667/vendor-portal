import { ActivatedRouteSnapshot, Resolve, RouterStateSnapshot, Routes } from '@angular/router';
import { IPaymentRequestForm, PaymentRequestForm } from 'app/paymentrequest/payment-request-form/payment-request-form.model';
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { HttpResponse } from '@angular/common/http';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { PaymentRequestApprovalService } from 'app/paymentrequest/payment-request-approval/payment-request-approval.service';
import { PaymentRequestApprovalComponent } from 'app/paymentrequest/payment-request-approval/payment-request-approval.component';
import { PaymentRequestApprovalUpdateComponent } from 'app/paymentrequest/payment-request-approval/payment-request-approval-update.component';

@Injectable({ providedIn: 'root' })
export class PaymentRequestFormResolve implements Resolve<IPaymentRequestForm> {
  constructor(private service: PaymentRequestApprovalService) {}
  resolve(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): Observable<IPaymentRequestForm> | Promise<IPaymentRequestForm> | IPaymentRequestForm {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<PaymentRequestForm>) => response.ok),
        map((paymentRequestForm: HttpResponse<PaymentRequestForm>) => paymentRequestForm.body)
      );
    }
    return of(new PaymentRequestForm());
  }
}
export const paymentRequestApprovalRoute: Routes = [
  {
    path: '',
    component: PaymentRequestApprovalComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      defaultSort: 'id,asc',
      pageTitle: 'Payment Request Form'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: PaymentRequestApprovalUpdateComponent,
    resolve: {
      paymentRequestForm: PaymentRequestFormResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'Payment Request Form'
    },
    canActivate: [UserRouteAccessService]
  }
];
export const paymentRequestApprovalPopRoute: Routes = [];
