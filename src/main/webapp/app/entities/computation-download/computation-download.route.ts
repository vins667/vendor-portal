import { Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';

import { ComputationDownloadComponent } from './computation-download.component';

export const ComputationDownloadRoute: Routes = [
  {
    path: '',
    component: ComputationDownloadComponent,
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'ComputationDownloads'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const ComputationDownloadPopupRoute: Routes = [];
