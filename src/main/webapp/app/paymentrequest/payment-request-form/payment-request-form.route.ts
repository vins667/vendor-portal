import { ActivatedRouteSnapshot, Resolve, RouterStateSnapshot, Routes } from '@angular/router';
import { IPaymentRequestForm, PaymentRequestForm } from 'app/paymentrequest/payment-request-form/payment-request-form.model';
import { Injectable } from '@angular/core';
import { PaymentRequestFormService } from 'app/paymentrequest/payment-request-form/payment-request-form.service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { HttpResponse } from '@angular/common/http';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { PaymentRequestFormComponent } from 'app/paymentrequest/payment-request-form/payment-request-form.component';
import { PaymentRequestFormUpdateComponent } from 'app/paymentrequest/payment-request-form/payment-request-form-update.component';
import { PaymentRequestFormDeletePopComponent } from 'app/paymentrequest/payment-request-form/payment-request-form-delete-dialog.component';

@Injectable({ providedIn: 'root' })
export class PaymentRequestFormResolve implements Resolve<IPaymentRequestForm> {
  constructor(private service: PaymentRequestFormService) {}
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
export const paymentRequestFormRoute: Routes = [
  {
    path: '',
    component: PaymentRequestFormComponent,
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
    path: 'new',
    component: PaymentRequestFormUpdateComponent,
    resolve: {
      paymentRequestForm: PaymentRequestFormResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'Payment Request Form'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: PaymentRequestFormUpdateComponent,
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
export const paymentRequestFormPopRoute: Routes = [
  {
    path: ':id/delete',
    component: PaymentRequestFormDeletePopComponent,
    resolve: {
      paymentRequestForm: PaymentRequestFormResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'Payment Request Form'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
