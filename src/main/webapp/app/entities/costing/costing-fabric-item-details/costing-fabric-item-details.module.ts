import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { VamaniportalSharedModule } from 'app/shared/shared.module';
import { CostingFabricItemDetailsComponent } from './costing-fabric-item-details.component';
import { CostingFabricItemDetailsDetailComponent } from './costing-fabric-item-details-detail.component';
import { CostingFabricItemDetailsUpdateComponent } from './costing-fabric-item-details-update.component';
import {
  CostingFabricItemDetailsDeletePopupComponent,
  CostingFabricItemDetailsDeleteDialogComponent
} from './costing-fabric-item-details-delete-dialog.component';
import { costingFabricItemDetailsRoute, costingFabricItemDetailsPopupRoute } from './costing-fabric-item-details.route';

const ENTITY_STATES = [...costingFabricItemDetailsRoute, ...costingFabricItemDetailsPopupRoute];

@NgModule({
  imports: [VamaniportalSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    CostingFabricItemDetailsComponent,
    CostingFabricItemDetailsDetailComponent,
    CostingFabricItemDetailsUpdateComponent,
    CostingFabricItemDetailsDeleteDialogComponent,
    CostingFabricItemDetailsDeletePopupComponent
  ],
  entryComponents: [
    CostingFabricItemDetailsComponent,
    CostingFabricItemDetailsUpdateComponent,
    CostingFabricItemDetailsDeleteDialogComponent,
    CostingFabricItemDetailsDeletePopupComponent
  ]
})
export class VamaniportalCostingFabricItemDetailsModule {}
