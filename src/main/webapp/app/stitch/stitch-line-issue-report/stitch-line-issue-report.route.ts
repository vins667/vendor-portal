import { Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { StitchLineIssueReportComponent } from './stitch-line-issue-report.component';
export const stitchLineIssueReportRoute: Routes = [
  {
    path: '',
    component: StitchLineIssueReportComponent,
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'Stitch Line Issue Report'
    },
    canActivate: [UserRouteAccessService]
  }
];
