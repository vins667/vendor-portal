import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { OwlDateTimeModule, OwlNativeDateTimeModule } from 'ng-pick-datetime';
import { SnotifyModule, SnotifyService, ToastDefaults } from 'ng-snotify';
import {
  ConveyanceMasterComponent,
  ConveyanceMasterDetailComponent,
  ConveyanceMasterUpdateComponent,
  ConveyanceMasterDeletePopupComponent,
  ConveyanceMasterDeleteDialogComponent,
  conveyanceMasterRoute,
  conveyanceMasterPopupRoute
} from './';
import { VamaniportalSharedModule } from 'app/shared/shared.module';

const ENTITY_STATES = [...conveyanceMasterRoute, ...conveyanceMasterPopupRoute];

@NgModule({
  imports: [VamaniportalSharedModule, RouterModule.forChild(ENTITY_STATES), SnotifyModule, OwlDateTimeModule, OwlNativeDateTimeModule],
  declarations: [
    ConveyanceMasterComponent,
    ConveyanceMasterDetailComponent,
    ConveyanceMasterUpdateComponent,
    ConveyanceMasterDeleteDialogComponent,
    ConveyanceMasterDeletePopupComponent
  ],
  entryComponents: [
    ConveyanceMasterComponent,
    ConveyanceMasterUpdateComponent,
    ConveyanceMasterDeleteDialogComponent,
    ConveyanceMasterDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
  providers: [{ provide: 'SnotifyToastConfig', useValue: ToastDefaults }, SnotifyService]
})
export class VamaniportalConveyanceMasterModule {}
