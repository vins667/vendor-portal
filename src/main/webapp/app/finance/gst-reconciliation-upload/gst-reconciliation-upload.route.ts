import { Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { GstReconciliationUploadComponent } from './gst-reconciliation-upload.component';

export const gstReconciliationUploadRoute: Routes = [
  {
    path: '',
    component: GstReconciliationUploadComponent,
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'GST Reconciliation'
    },
    canActivate: [UserRouteAccessService]
  }
];
