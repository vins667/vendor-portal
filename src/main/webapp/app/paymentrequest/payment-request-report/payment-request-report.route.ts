import { Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { PaymentRequestReportComponent } from 'app/paymentrequest/payment-request-report/payment-request-report.component';

export const paymentRequestReportRoute: Routes = [
  {
    path: '',
    component: PaymentRequestReportComponent,
    data: {
      authorities: ['ROLE_ADMIN', 'ROLE_USER'],
      pageTitle: 'Payment Request report'
    },
    canActivate: [UserRouteAccessService]
  }
];
