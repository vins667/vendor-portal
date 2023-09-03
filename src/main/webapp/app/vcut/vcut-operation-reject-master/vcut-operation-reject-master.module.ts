import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { VamaniportalSharedModule } from 'app/shared/shared.module';
import { VcutOperationRejectMasterComponent } from './vcut-operation-reject-master.component';
import { VcutOperationRejectMasterDetailComponent } from './vcut-operation-reject-master-detail.component';
import { VcutOperationRejectMasterUpdateComponent } from './vcut-operation-reject-master-update.component';
import {
  VcutOperationRejectMasterDeletePopupComponent,
  VcutOperationRejectMasterDeleteDialogComponent
} from './vcut-operation-reject-master-delete-dialog.component';
import { vcutOperationRejectMasterRoute, vcutOperationRejectMasterPopupRoute } from './vcut-operation-reject-master.route';

const ENTITY_STATES = [...vcutOperationRejectMasterRoute, ...vcutOperationRejectMasterPopupRoute];

@NgModule({
  imports: [VamaniportalSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    VcutOperationRejectMasterComponent,
    VcutOperationRejectMasterDetailComponent,
    VcutOperationRejectMasterUpdateComponent,
    VcutOperationRejectMasterDeleteDialogComponent,
    VcutOperationRejectMasterDeletePopupComponent
  ],
  entryComponents: [
    VcutOperationRejectMasterComponent,
    VcutOperationRejectMasterUpdateComponent,
    VcutOperationRejectMasterDeleteDialogComponent,
    VcutOperationRejectMasterDeletePopupComponent
  ]
})
export class VamaniportalVcutOperationRejectMasterModule {}
