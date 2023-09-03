import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'fabric-creation-master',
        loadChildren: () => import('./fabric-creation-master/fabric-creation-master.module').then(m => m.VamaniportalFabricCreationMasterModule)
      },
      {
        path: 'fabric-substract-master',
        loadChildren: () => import('./fabric-substract-master/fabric-substract-master.module').then(m => m.VamaniportalFabricSubstractMasterModule)
      },
      {
        path: 'fabric-spl-finish-master',
        loadChildren: () => import('./fabric-spl-finish-master/fabric-spl-finish-master.module').then(m => m.VamaniportalFabricSplFinishMasterModule)
      },
      {
        path: 'fabric-substract-details',
        loadChildren: () => import('./fabric-substract-details/fabric-substract-details.module').then(m => m.VamaniportalFabricSubstractDetailsModule)
      },
      {
        path: 'fabric-uom-master',
        loadChildren: () => import('./fabric-uom-master/fabric-uom-master.module').then(m => m.VamaniportalFabricUomMasterModule)
      },
      {
        path: 'fabric-content-master',
        loadChildren: () => import('./fabric-content-master/fabric-content-master.module').then(m => m.VamaniportalFabricContentMasterModule)
      },
      {
        path: 'fabric-others-master',
        loadChildren: () => import('./fabric-others-master/fabric-others-master.module').then(m => m.VamaniportalFabricOthersMasterModule)
      },
      {
        path: 'yarn-type-master',
        loadChildren: () => import('./yarn-type-master/yarn-type-master.module').then(m => m.VamaniportalYarnTypeMasterModule)
      },
      {
        path: 'yarn-count-master',
        loadChildren: () => import('./yarn-count-master/yarn-count-master.module').then(m => m.VamaniportalYarnCountMasterModule)
      },
      {
        path: 'knit-type-master',
        loadChildren: () => import('./knit-type-master/knit-type-master.module').then(m => m.VamaniportalKnitTypeMasterModule)
      },
      {
        path: 'knit-process-master',
        loadChildren: () => import('./knit-process-master/knit-process-master.module').then(m => m.VamaniportalKnitProcessMasterModule)
      },
      {
        path: 'fabric-content-master',
        loadChildren: () => import('./fabric-content-master/fabric-content-master.module').then(m => m.VamaniportalFabricContentMasterModule)
      },
      {
        path: 'fabric-others-master',
        loadChildren: () => import('./fabric-others-master/fabric-others-master.module').then(m => m.VamaniportalFabricOthersMasterModule)
      },
      {
        path: 'trims-template-master',
        loadChildren: () => import('./trims-template-master/trims-template-master.module').then(m => m.VamaniportalTrimsTemplateMasterModule)
      },
      {
        path: 'knit-creation-master',
        loadChildren: () => import('./knit-creation-master/knit-creation-master.module').then(m => m.VamaniportalKnitCreationMasterModule)
      },
      {
        path: 'trims-creation-master',
        loadChildren: () => import('./trims-creation-master/trims-creation-master.module').then(m => m.VamaniportalTrimsCreationMasterModule)
      }
    ])
  ],
  declarations: [],
  entryComponents: [],
  providers: [],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class VamaniportalFabriccodingModule {}
