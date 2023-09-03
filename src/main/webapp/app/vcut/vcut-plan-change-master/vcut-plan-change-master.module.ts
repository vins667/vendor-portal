import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { VamaniportalSharedModule } from 'app/shared/shared.module';
import { VcutPlanChangeMasterComponent } from './vcut-plan-change-master.component';
import { VcutPlanChangeMasterDetailComponent } from './vcut-plan-change-master-detail.component';
import { VcutPlanChangeMasterUpdateComponent } from './vcut-plan-change-master-update.component';
import {
  VcutPlanChangeMasterDeletePopupComponent,
  VcutPlanChangeMasterDeleteDialogComponent
} from './vcut-plan-change-master-delete-dialog.component';
import { vcutPlanChangeMasterRoute, vcutPlanChangeMasterPopupRoute } from './vcut-plan-change-master.route';

const ENTITY_STATES = [...vcutPlanChangeMasterRoute, ...vcutPlanChangeMasterPopupRoute];

@NgModule({
  imports: [VamaniportalSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    VcutPlanChangeMasterComponent,
    VcutPlanChangeMasterDetailComponent,
    VcutPlanChangeMasterUpdateComponent,
    VcutPlanChangeMasterDeleteDialogComponent,
    VcutPlanChangeMasterDeletePopupComponent
  ],
  entryComponents: [
    VcutPlanChangeMasterComponent,
    VcutPlanChangeMasterUpdateComponent,
    VcutPlanChangeMasterDeleteDialogComponent,
    VcutPlanChangeMasterDeletePopupComponent
  ]
})
export class VamaniportalVcutPlanChangeMasterModule {}
