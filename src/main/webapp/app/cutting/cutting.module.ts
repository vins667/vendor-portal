import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'marker-master-entry',
        loadChildren: () => import('./marker-master-entry/marker-master-entry.module').then(m => m.VamaniportalMarkerMasterEntryModule)
      },
      {
        path: 'marker-entry-approval',
        loadChildren: () =>
          import('./marker-entry-approval/marker-entry-approval.module').then(m => m.VamaniportalMarkerEntryApprovalModule)
      },
      {
        path: 'cut-plan-entry',
        loadChildren: () => import('./cut-plan-entry/cut-plan-entry.module').then(m => m.VamaniportalCutPlanEntryModule)
      },
      {
        path: 'cut-plan-mrkr-entry',
        loadChildren: () => import('./cut-plan-mrkr-entry/cut-plan-mrkr-entry.module').then(m => m.VamaniportalCutPlanMrkrEntryModule)
      },
      {
        path: 'cut-fabric-issue',
        loadChildren: () => import('./cut-fabric-issue/cut-fabric-issue.module').then(m => m.VamaniportalCutFabricIssueModule)
      },
      {
        path: 'cut-plan-progress',
        loadChildren: () => import('./cut-plan-progress/cut-plan-progress.module').then(m => m.VamaniportalCutPlanProgressModule)
      },
      {
        path: 'cut-plan-bundle',
        loadChildren: () => import('./cut-plan-bundle/cut-plan-bundle.module').then(m => m.VamaniportalCutPlanBundleModule)
      },
      {
        path: 'cut-plan-issue-stitch',
        loadChildren: () => import('./cut-plan-issue-stitch/cut-plan-issue-stitch.module').then(m => m.VamaniportalCutPlanIssueStitchModule)
      },
      {
        path: 'cut-plan-reciept-stitch',
        loadChildren: () =>
          import('./cut-plan-reciept-stitch/cut-plan-reciept-stitch.module').then(m => m.VamaniportalCutPlanRecieptStitchModule)
      },
      {
        path: 'user-plant',
        loadChildren: () => import('./user-plant/user-plant.module').then(m => m.VamaniportalUserPlantModule)
      },
      {
        path: 'cut-bundle-lock',
        loadChildren: () => import('./cut-bundle-lock/cut-bundle-lock.module').then(m => m.VamaniportalCutBundleLockModule)
      }
    ])
  ],
  entryComponents: [],
  providers: [],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class VamaniportalCuttingModule {}
