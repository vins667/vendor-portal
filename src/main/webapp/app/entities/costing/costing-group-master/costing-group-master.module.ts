import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { VamaniportalSharedModule } from 'app/shared/shared.module';
import { CostingGroupMasterComponent } from './costing-group-master.component';
import { CostingGroupMasterDetailComponent } from './costing-group-master-detail.component';
import { CostingGroupMasterUpdateComponent } from './costing-group-master-update.component';
import {
  CostingGroupMasterDeletePopupComponent,
  CostingGroupMasterDeleteDialogComponent
} from './costing-group-master-delete-dialog.component';
import { costingGroupMasterRoute, costingGroupMasterPopupRoute } from './costing-group-master.route';

const ENTITY_STATES = [...costingGroupMasterRoute, ...costingGroupMasterPopupRoute];

@NgModule({
  imports: [VamaniportalSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    CostingGroupMasterComponent,
    CostingGroupMasterDetailComponent,
    CostingGroupMasterUpdateComponent,
    CostingGroupMasterDeleteDialogComponent,
    CostingGroupMasterDeletePopupComponent
  ],
  entryComponents: [
    CostingGroupMasterComponent,
    CostingGroupMasterUpdateComponent,
    CostingGroupMasterDeleteDialogComponent,
    CostingGroupMasterDeletePopupComponent
  ]
})
export class VamaniportalCostingGroupMasterModule {}
