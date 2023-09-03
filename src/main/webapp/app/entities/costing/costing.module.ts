import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'costing-process-master',
        loadChildren: () =>
          import('./costing-process-master/costing-process-master.module').then(m => m.VamaniportalCostingProcessMasterModule)
      },
      {
        path: 'costing-value-master',
        loadChildren: () => import('./costing-value-master/costing-value-master.module').then(m => m.VamaniportalCostingValueMasterModule)
      },
      {
        path: 'costing-efficiency-maste',
        loadChildren: () =>
          import('./costing-efficiency-maste/costing-efficiency-maste.module').then(m => m.VamaniportalCostingEfficiencyMasteModule)
      },
      {
        path: 'buyer-costing',
        loadChildren: () => import('./buyer-costing/buyer-costing.module').then(m => m.VamaniportalBuyerCostingModule)
      },
      {
        path: 'costing-fabric-item-details',
        loadChildren: () =>
          import('./costing-fabric-item-details/costing-fabric-item-details.module').then(m => m.VamaniportalCostingFabricItemDetailsModule)
      },
      {
        path: 'costing-group-master',
        loadChildren: () => import('./costing-group-master/costing-group-master.module').then(m => m.VamaniportalCostingGroupMasterModule)
      },
      {
        path: 'costing-group-details',
        loadChildren: () =>
          import('./costing-group-details/costing-group-details.module').then(m => m.VamaniportalCostingGroupDetailsModule)
      }
    ])
  ],
  declarations: [],
  entryComponents: [],
  providers: [],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class VamaniportalCostingModule {}
