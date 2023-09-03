import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { VamaniportalSharedModule } from 'app/shared/shared.module';
import { VcutDeviceLineMasterComponent } from './vcut-device-line-master.component';
import { VcutDeviceLineMasterDetailComponent } from './vcut-device-line-master-detail.component';
import { VcutDeviceLineMasterUpdateComponent } from './vcut-device-line-master-update.component';
import {
  VcutDeviceLineMasterDeletePopupComponent,
  VcutDeviceLineMasterDeleteDialogComponent
} from './vcut-device-line-master-delete-dialog.component';
import { vcutDeviceLineMasterRoute, vcutDeviceLineMasterPopupRoute } from './vcut-device-line-master.route';
import { VcutDeviceLineMasterSearchComponent } from 'app/vcut/vcut-device-line-master/vcut-device-line-master-search.component';
import { SnotifyModule, SnotifyService, ToastDefaults } from 'ng-snotify';

const ENTITY_STATES = [...vcutDeviceLineMasterRoute, ...vcutDeviceLineMasterPopupRoute];

@NgModule({
  imports: [VamaniportalSharedModule, RouterModule.forChild(ENTITY_STATES), SnotifyModule],
  declarations: [
    VcutDeviceLineMasterComponent,
    VcutDeviceLineMasterDetailComponent,
    VcutDeviceLineMasterUpdateComponent,
    VcutDeviceLineMasterDeleteDialogComponent,
    VcutDeviceLineMasterSearchComponent,
    VcutDeviceLineMasterDeletePopupComponent
  ],
  entryComponents: [
    VcutDeviceLineMasterComponent,
    VcutDeviceLineMasterUpdateComponent,
    VcutDeviceLineMasterDeleteDialogComponent,
    VcutDeviceLineMasterSearchComponent,
    VcutDeviceLineMasterDeletePopupComponent
  ],
  providers: [{ provide: 'SnotifyToastConfig', useValue: ToastDefaults }, SnotifyService]
})
export class VamaniportalVcutDeviceLineMasterModule {}
