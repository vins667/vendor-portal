import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { VamaniportalSharedModule } from 'app/shared/shared.module';
import {
  CompOffMasterComponent,
  CompOffMasterDetailComponent,
  CompOffMasterUpdateComponent,
  CompOffMasterDeletePopupComponent,
  CompOffMasterDeleteDialogComponent,
  compOffMasterRoute,
  compOffMasterPopupRoute
} from './';
import { OwlDateTimeModule, OwlNativeDateTimeModule } from 'ng-pick-datetime';
import { SnotifyModule, SnotifyService, ToastDefaults } from 'ng-snotify';
const ENTITY_STATES = [...compOffMasterRoute, ...compOffMasterPopupRoute];

@NgModule({
  imports: [VamaniportalSharedModule, RouterModule.forChild(ENTITY_STATES), OwlDateTimeModule, OwlNativeDateTimeModule, SnotifyModule],
  declarations: [
    CompOffMasterComponent,
    CompOffMasterDetailComponent,
    CompOffMasterUpdateComponent,
    CompOffMasterDeleteDialogComponent,
    CompOffMasterDeletePopupComponent
  ],
  entryComponents: [
    CompOffMasterComponent,
    CompOffMasterUpdateComponent,
    CompOffMasterDeleteDialogComponent,
    CompOffMasterDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
  providers: [{ provide: 'SnotifyToastConfig', useValue: ToastDefaults }, SnotifyService]
})
export class VamaniportalCompOffMasterModule {}
