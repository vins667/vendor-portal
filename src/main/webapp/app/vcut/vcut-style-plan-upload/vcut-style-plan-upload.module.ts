import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { VamaniportalSharedModule } from 'app/shared/shared.module';
import { VcutStylePlanUploadComponent } from './vcut-style-plan-upload.component';
import { VcutStylePlanUploadDetailComponent } from './vcut-style-plan-upload-detail.component';
import { VcutStylePlanUploadUpdateComponent } from './vcut-style-plan-upload-update.component';
import { VcutStylePlanSessionBreakupComponent } from './vcut-style-plan-session-breakup.component';
import {
  VcutStylePlanUploadDeletePopupComponent,
  VcutStylePlanUploadDeleteDialogComponent
} from './vcut-style-plan-upload-delete-dialog.component';
import { vcutStylePlanUploadRoute, vcutStylePlanUploadPopupRoute } from './vcut-style-plan-upload.route';
import { DragDropDirective } from './drag-drop.directive';
import { SnotifyModule, SnotifyService, ToastDefaults } from 'ng-snotify';
import { OwlDateTimeModule, OwlNativeDateTimeModule } from 'ng-pick-datetime';
import { TextMaskModule } from 'angular2-text-mask';
import { VcutLineProductionorderSelectionComponent } from './vcut-line-productionorder-selection.component';
const ENTITY_STATES = [...vcutStylePlanUploadRoute, ...vcutStylePlanUploadPopupRoute];

@NgModule({
  imports: [
    VamaniportalSharedModule,
    RouterModule.forChild(ENTITY_STATES),
    SnotifyModule,
    OwlDateTimeModule,
    OwlNativeDateTimeModule,
    TextMaskModule
  ],
  declarations: [
    DragDropDirective,
    VcutStylePlanUploadComponent,
    VcutStylePlanUploadDetailComponent,
    VcutStylePlanUploadUpdateComponent,
    VcutStylePlanUploadDeleteDialogComponent,
    VcutStylePlanUploadDeletePopupComponent,
    VcutStylePlanSessionBreakupComponent,
    VcutLineProductionorderSelectionComponent
  ],
  entryComponents: [
    VcutStylePlanUploadComponent,
    VcutStylePlanUploadUpdateComponent,
    VcutStylePlanUploadDeleteDialogComponent,
    VcutStylePlanUploadDeletePopupComponent,
    VcutStylePlanSessionBreakupComponent,
    VcutLineProductionorderSelectionComponent
  ],
  providers: [{ provide: 'SnotifyToastConfig', useValue: ToastDefaults }, SnotifyService]
})
export class VamaniportalVcutStylePlanUploadModule {}
