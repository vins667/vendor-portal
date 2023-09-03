import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { VamaniportalSharedModule } from 'app/shared/shared.module';
import { CostingEfficiencyMasteComponent } from './costing-efficiency-maste.component';
import { CostingEfficiencyMasteDetailComponent } from './costing-efficiency-maste-detail.component';
import { CostingEfficiencyMasteUpdateComponent } from './costing-efficiency-maste-update.component';
import {
  CostingEfficiencyMasteDeletePopupComponent,
  CostingEfficiencyMasteDeleteDialogComponent
} from './costing-efficiency-maste-delete-dialog.component';
import { costingEfficiencyMasteRoute, costingEfficiencyMastePopupRoute } from './costing-efficiency-maste.route';

const ENTITY_STATES = [...costingEfficiencyMasteRoute, ...costingEfficiencyMastePopupRoute];

@NgModule({
  imports: [VamaniportalSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    CostingEfficiencyMasteComponent,
    CostingEfficiencyMasteDetailComponent,
    CostingEfficiencyMasteUpdateComponent,
    CostingEfficiencyMasteDeleteDialogComponent,
    CostingEfficiencyMasteDeletePopupComponent
  ],
  entryComponents: [
    CostingEfficiencyMasteComponent,
    CostingEfficiencyMasteUpdateComponent,
    CostingEfficiencyMasteDeleteDialogComponent,
    CostingEfficiencyMasteDeletePopupComponent
  ]
})
export class VamaniportalCostingEfficiencyMasteModule {}
