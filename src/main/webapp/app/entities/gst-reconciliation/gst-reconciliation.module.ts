import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { SnotifyModule, SnotifyService, ToastDefaults } from 'ng-snotify';
import { VamaniportalSharedModule } from 'app/shared/shared.module';
import { GstReconciliationComponent } from './gst-reconciliation.component';
import { GstReconciliationDetailComponent } from './gst-reconciliation-detail.component';
import { GstReconciliationUpdateComponent } from './gst-reconciliation-update.component';
import {
  GstReconciliationDeletePopupComponent,
  GstReconciliationDeleteDialogComponent
} from './gst-reconciliation-delete-dialog.component';
import { gstReconciliationRoute, gstReconciliationPopupRoute } from './gst-reconciliation.route';
import { GstReconciliationMiscComponent } from './gst-reconciliation-misc.component';

const ENTITY_STATES = [...gstReconciliationRoute, ...gstReconciliationPopupRoute];

@NgModule({
  imports: [VamaniportalSharedModule, RouterModule.forChild(ENTITY_STATES), SnotifyModule],
  declarations: [
    GstReconciliationComponent,
    GstReconciliationDetailComponent,
    GstReconciliationUpdateComponent,
    GstReconciliationMiscComponent,
    GstReconciliationDeleteDialogComponent,
    GstReconciliationDeletePopupComponent
  ],
  entryComponents: [
    GstReconciliationComponent,
    GstReconciliationUpdateComponent,
    GstReconciliationMiscComponent,
    GstReconciliationDeleteDialogComponent,
    GstReconciliationDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
  providers: [{ provide: 'SnotifyToastConfig', useValue: ToastDefaults }, SnotifyService]
})
export class VamaniportalGstReconciliationModule {}
