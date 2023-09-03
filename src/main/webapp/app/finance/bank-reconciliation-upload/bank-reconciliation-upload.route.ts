import { Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { BankReconciliationUploadComponent } from './bank-reconciliation-upload.component';

export const bankReconciliationUploadRoute: Routes = [
  {
    path: '',
    component: BankReconciliationUploadComponent,
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'Bank Reconciliation'
    },
    canActivate: [UserRouteAccessService]
  }
];
