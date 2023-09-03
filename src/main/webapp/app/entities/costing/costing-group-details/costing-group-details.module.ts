import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { VamaniportalSharedModule } from 'app/shared/shared.module';
import { CostingGroupDetailsComponent } from './costing-group-details.component';
import { CostingGroupDetailsDetailComponent } from './costing-group-details-detail.component';
import { CostingGroupDetailsUpdateComponent } from './costing-group-details-update.component';
import {
  CostingGroupDetailsDeletePopupComponent,
  CostingGroupDetailsDeleteDialogComponent
} from './costing-group-details-delete-dialog.component';
import { costingGroupDetailsRoute, costingGroupDetailsPopupRoute } from './costing-group-details.route';

const ENTITY_STATES = [...costingGroupDetailsRoute, ...costingGroupDetailsPopupRoute];

@NgModule({
  imports: [VamaniportalSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    CostingGroupDetailsComponent,
    CostingGroupDetailsDetailComponent,
    CostingGroupDetailsUpdateComponent,
    CostingGroupDetailsDeleteDialogComponent,
    CostingGroupDetailsDeletePopupComponent
  ],
  entryComponents: [
    CostingGroupDetailsComponent,
    CostingGroupDetailsUpdateComponent,
    CostingGroupDetailsDeleteDialogComponent,
    CostingGroupDetailsDeletePopupComponent
  ]
})
export class VamaniportalCostingGroupDetailsModule {}
