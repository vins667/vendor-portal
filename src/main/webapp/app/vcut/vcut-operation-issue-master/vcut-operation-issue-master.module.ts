import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { VamaniportalSharedModule } from 'app/shared/shared.module';
import { VcutOperationIssueMasterComponent } from './vcut-operation-issue-master.component';
import { VcutOperationIssueMasterDetailComponent } from './vcut-operation-issue-master-detail.component';
import { VcutOperationIssueMasterUpdateComponent } from './vcut-operation-issue-master-update.component';
import {
  VcutOperationIssueMasterDeletePopupComponent,
  VcutOperationIssueMasterDeleteDialogComponent
} from './vcut-operation-issue-master-delete-dialog.component';
import { vcutOperationIssueMasterRoute, vcutOperationIssueMasterPopupRoute } from './vcut-operation-issue-master.route';

const ENTITY_STATES = [...vcutOperationIssueMasterRoute, ...vcutOperationIssueMasterPopupRoute];

@NgModule({
  imports: [VamaniportalSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    VcutOperationIssueMasterComponent,
    VcutOperationIssueMasterDetailComponent,
    VcutOperationIssueMasterUpdateComponent,
    VcutOperationIssueMasterDeleteDialogComponent,
    VcutOperationIssueMasterDeletePopupComponent
  ],
  entryComponents: [
    VcutOperationIssueMasterComponent,
    VcutOperationIssueMasterUpdateComponent,
    VcutOperationIssueMasterDeleteDialogComponent,
    VcutOperationIssueMasterDeletePopupComponent
  ]
})
export class VamaniportalVcutOperationIssueMasterModule {}
