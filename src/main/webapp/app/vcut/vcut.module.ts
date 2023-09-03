import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'vcut-device-line-master',
        loadChildren: () =>
          import('./vcut-device-line-master/vcut-device-line-master.module').then(m => m.VamaniportalVcutDeviceLineMasterModule)
      },
      {
        path: 'vcut-style-plan-upload',
        loadChildren: () =>
          import('./vcut-style-plan-upload/vcut-style-plan-upload.module').then(m => m.VamaniportalVcutStylePlanUploadModule)
      },
      {
        path: 'vcut-session-master',
        loadChildren: () => import('./vcut-session-master/vcut-session-master.module').then(m => m.VamaniportalVcutSessionMasterModule)
      },
      {
        path: 'vcut-plan-change-master',
        loadChildren: () =>
          import('./vcut-plan-change-master/vcut-plan-change-master.module').then(m => m.VamaniportalVcutPlanChangeMasterModule)
      },
      {
        path: 'vcut-operation-issue-master',
        loadChildren: () =>
          import('./vcut-operation-issue-master/vcut-operation-issue-master.module').then(m => m.VamaniportalVcutOperationIssueMasterModule)
      },
      {
        path: 'vcut-operation-master',
        loadChildren: () =>
          import('./vcut-operation-master/vcut-operation-master.module').then(m => m.VamaniportalVcutOperationMasterModule)
      },
      {
        path: 'vcut-line-summary',
        loadChildren: () => import('./vcut-line-summary/vcut-line-summary.module').then(m => m.VamaniportalVcutLineSummaryModule)
      },
      {
        path: 'vcut-style-image',
        loadChildren: () => import('./vcut-style-image/vcut-style-image.module').then(m => m.VamaniportalVcutStyleImageModule)
      }
    ])
  ],
  declarations: [],
  entryComponents: [],
  providers: [],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class VcutVendorportalModule {}
