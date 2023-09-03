import { Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { StrengthReportComponent } from './strength-report.component';

export const strengthReportRoute: Routes = [
  {
    path: '',
    component: StrengthReportComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      defaultSort: 'id,asc',
      pageTitle: 'Strength Report'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const strengthReportPopupRoute: Routes = [{}];
