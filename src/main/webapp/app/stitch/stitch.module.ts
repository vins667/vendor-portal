import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'manpower-entry',
        loadChildren: () => import('./manpower-budgeting/manpower-budgeting.module').then(m => m.VamaniPortalManpowerBudgetingModule)
      },
      {
        path: 'issue-to-line',
        loadChildren: () => import('./stitch-line-issue/stitch-line-issue.module').then(m => m.VamaniportalStitchLineIssueModule)
      },
      {
        path: 'stitch-issue-pack',
        loadChildren: () => import('./stitch-issue-pack/stitch-issue-pack.module').then(m => m.VamaniportalStitchIssuePackModule)
      },
      {
        path: 'stitch-reciept-pack',
        loadChildren: () => import('./stitch-reciept-pack/stitch-reciept-pack.module').then(m => m.VamaniportalStitchRecieptPackModule)
      },
      {
        path: 'issue-to-line-report',
        loadChildren: () =>
          import('./stitch-line-issue-report/stitch-line-issue-report.module').then(m => m.VamaniportalStitchLineIssueReportModule)
      },
      {
        path: 'stitching-manpower-cost', //stitch-cost-head-master
        loadChildren: () =>
          import('./stitch-cost-Head/stitch-cost-Head-master.module').then(m => m.VamaniportalStitchCostHeadMasterModule)
      }
    ])
  ],
  entryComponents: [],
  providers: [],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class VamaniportalStitchingModule {}
