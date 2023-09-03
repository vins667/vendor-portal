import { Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { MmrReportComponent } from './mmr-report.component';

export const mmrReportRoute: Routes = [
  {
    path: '',
    component: MmrReportComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      defaultSort: 'id,asc',
      pageTitle: 'MmrReports'
    },
    canActivate: [UserRouteAccessService]
  }
];
