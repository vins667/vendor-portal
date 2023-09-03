import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { VamaniportalSharedModule } from 'app/shared/shared.module';
import { VcutOperationMasterComponent } from './vcut-operation-master.component';
import { VcutOperationMasterDetailComponent } from './vcut-operation-master-detail.component';
import { VcutOperationMasterUpdateComponent } from './vcut-operation-master-update.component';
import {
  VcutOperationMasterDeletePopupComponent,
  VcutOperationMasterDeleteDialogComponent
} from './vcut-operation-master-delete-dialog.component';
import { vcutOperationMasterRoute, vcutOperationMasterPopupRoute } from './vcut-operation-master.route';
import { SnotifyModule, SnotifyService, ToastDefaults } from 'ng-snotify';
import { DragDropOperDirective } from './drag-drop-oper.directive';

const ENTITY_STATES = [...vcutOperationMasterRoute, ...vcutOperationMasterPopupRoute];

@NgModule({
  imports: [VamaniportalSharedModule, RouterModule.forChild(ENTITY_STATES), SnotifyModule],
  declarations: [
    DragDropOperDirective,
    VcutOperationMasterComponent,
    VcutOperationMasterDetailComponent,
    VcutOperationMasterUpdateComponent,
    VcutOperationMasterDeleteDialogComponent,
    VcutOperationMasterDeletePopupComponent
  ],
  entryComponents: [
    VcutOperationMasterComponent,
    VcutOperationMasterUpdateComponent,
    VcutOperationMasterDeleteDialogComponent,
    VcutOperationMasterDeletePopupComponent
  ],
  providers: [{ provide: 'SnotifyToastConfig', useValue: ToastDefaults }, SnotifyService]
})
export class VamaniportalVcutOperationMasterModule {}
