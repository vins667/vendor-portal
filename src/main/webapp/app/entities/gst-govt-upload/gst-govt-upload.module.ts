import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { VamaniportalSharedModule } from 'app/shared/shared.module';
import { GstGovtUploadComponent } from './gst-govt-upload.component';
import { GstGovtUploadDetailComponent } from './gst-govt-upload-detail.component';
import { GstGovtUploadUpdateComponent } from './gst-govt-upload-update.component';
import { GstGovtUploadDeletePopupComponent, GstGovtUploadDeleteDialogComponent } from './gst-govt-upload-delete-dialog.component';
import { gstGovtUploadRoute, gstGovtUploadPopupRoute } from './gst-govt-upload.route';
import { GstGovtUploadPopupComponent } from './gst-govt-upload-popup.component';
import { ToastDefaults, SnotifyService, SnotifyModule } from 'ng-snotify';
import { DragDropGovtviewDirective } from './drag-drop-govtview.directive';

const ENTITY_STATES = [...gstGovtUploadRoute, ...gstGovtUploadPopupRoute];

@NgModule({
  imports: [VamaniportalSharedModule, RouterModule.forChild(ENTITY_STATES), SnotifyModule],
  declarations: [
    DragDropGovtviewDirective,
    GstGovtUploadComponent,
    GstGovtUploadDetailComponent,
    GstGovtUploadUpdateComponent,
    GstGovtUploadDeleteDialogComponent,
    GstGovtUploadPopupComponent,
    GstGovtUploadDeletePopupComponent
  ],
  entryComponents: [
    GstGovtUploadComponent,
    GstGovtUploadUpdateComponent,
    GstGovtUploadDeleteDialogComponent,
    GstGovtUploadPopupComponent,
    GstGovtUploadDeletePopupComponent
  ],
  providers: [{ provide: 'SnotifyToastConfig', useValue: ToastDefaults }, SnotifyService]
})
export class VamaniportalGstGovtUploadModule {}
