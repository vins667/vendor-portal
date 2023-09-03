import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { VamaniportalSharedModule } from 'app/shared/shared.module';
import { CostingProcessMasterComponent } from './costing-process-master.component';
import { CostingProcessMasterDetailComponent } from './costing-process-master-detail.component';
import { CostingProcessMasterUpdateComponent } from './costing-process-master-update.component';
import {
  CostingProcessMasterDeletePopupComponent,
  CostingProcessMasterDeleteDialogComponent
} from './costing-process-master-delete-dialog.component';
import { costingProcessMasterRoute, costingProcessMasterPopupRoute } from './costing-process-master.route';

const ENTITY_STATES = [...costingProcessMasterRoute, ...costingProcessMasterPopupRoute];

@NgModule({
  imports: [VamaniportalSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    CostingProcessMasterComponent,
    CostingProcessMasterDetailComponent,
    CostingProcessMasterUpdateComponent,
    CostingProcessMasterDeleteDialogComponent,
    CostingProcessMasterDeletePopupComponent
  ],
  entryComponents: [
    CostingProcessMasterComponent,
    CostingProcessMasterUpdateComponent,
    CostingProcessMasterDeleteDialogComponent,
    CostingProcessMasterDeletePopupComponent
  ]
})
export class VamaniportalCostingProcessMasterModule {}
