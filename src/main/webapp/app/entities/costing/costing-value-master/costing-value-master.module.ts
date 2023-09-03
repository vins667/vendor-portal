import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { VamaniportalSharedModule } from 'app/shared/shared.module';
import { CostingValueMasterComponent } from './costing-value-master.component';
import { CostingValueMasterDetailComponent } from './costing-value-master-detail.component';
import { CostingValueMasterUpdateComponent } from './costing-value-master-update.component';
import {
  CostingValueMasterDeletePopupComponent,
  CostingValueMasterDeleteDialogComponent
} from './costing-value-master-delete-dialog.component';
import { costingValueMasterRoute, costingValueMasterPopupRoute } from './costing-value-master.route';

const ENTITY_STATES = [...costingValueMasterRoute, ...costingValueMasterPopupRoute];

@NgModule({
  imports: [VamaniportalSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    CostingValueMasterComponent,
    CostingValueMasterDetailComponent,
    CostingValueMasterUpdateComponent,
    CostingValueMasterDeleteDialogComponent,
    CostingValueMasterDeletePopupComponent
  ],
  entryComponents: [
    CostingValueMasterComponent,
    CostingValueMasterUpdateComponent,
    CostingValueMasterDeleteDialogComponent,
    CostingValueMasterDeletePopupComponent
  ]
})
export class VamaniportalCostingValueMasterModule {}
