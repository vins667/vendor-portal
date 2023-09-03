import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { VamaniportalSharedModule } from 'app/shared/shared.module';
import { GstReconciliationUploadComponent } from './gst-reconciliation-upload.component';
import { gstReconciliationUploadRoute } from './gst-reconciliation-upload.route';
import { DragDropDirective } from './drag-drop.directive';
import { SnotifyModule, SnotifyService, ToastDefaults } from 'ng-snotify';
import { OwlDateTimeModule, OwlNativeDateTimeModule } from 'ng-pick-datetime';
import { TextMaskModule } from 'angular2-text-mask';
const ENTITY_STATES = [...gstReconciliationUploadRoute];

@NgModule({
  imports: [
    VamaniportalSharedModule,
    RouterModule.forChild(ENTITY_STATES),
    SnotifyModule,
    OwlDateTimeModule,
    OwlNativeDateTimeModule,
    TextMaskModule
  ],
  declarations: [DragDropDirective, GstReconciliationUploadComponent],
  entryComponents: [GstReconciliationUploadComponent],
  providers: [{ provide: 'SnotifyToastConfig', useValue: ToastDefaults }, SnotifyService]
})
export class VamaniportalGstReconciliationUploadModule {}
