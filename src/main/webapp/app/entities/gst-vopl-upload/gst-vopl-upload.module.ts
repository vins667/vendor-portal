import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { VamaniportalSharedModule } from 'app/shared/shared.module';
import { GstVoplUploadComponent } from './gst-vopl-upload.component';
import { GstVoplUploadDetailComponent } from './gst-vopl-upload-detail.component';
import { GstVoplUploadUpdateComponent } from './gst-vopl-upload-update.component';
import { GstVoplUploadDeletePopupComponent, GstVoplUploadDeleteDialogComponent } from './gst-vopl-upload-delete-dialog.component';
import { gstVoplUploadRoute, gstVoplUploadPopupRoute } from './gst-vopl-upload.route';
import { GstVoplUploadPopupComponent } from './gst-vopl-upload-popup.component';
import { SnotifyModule, SnotifyService, ToastDefaults } from 'ng-snotify';
import { DragDropGstviewDirective } from './drag-drop-gstview.directive';

const ENTITY_STATES = [...gstVoplUploadRoute, ...gstVoplUploadPopupRoute];

@NgModule({
  imports: [VamaniportalSharedModule, RouterModule.forChild(ENTITY_STATES), SnotifyModule],
  declarations: [
    DragDropGstviewDirective,
    GstVoplUploadComponent,
    GstVoplUploadDetailComponent,
    GstVoplUploadUpdateComponent,
    GstVoplUploadDeleteDialogComponent,
    GstVoplUploadPopupComponent,
    GstVoplUploadDeletePopupComponent
  ],
  entryComponents: [
    GstVoplUploadComponent,
    GstVoplUploadUpdateComponent,
    GstVoplUploadDeleteDialogComponent,
    GstVoplUploadPopupComponent,
    GstVoplUploadDeletePopupComponent
  ],
  providers: [{ provide: 'SnotifyToastConfig', useValue: ToastDefaults }, SnotifyService]
})
export class VamaniportalGstVoplUploadModule {}
