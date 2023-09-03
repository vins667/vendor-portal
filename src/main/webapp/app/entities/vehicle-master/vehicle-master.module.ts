import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { OwlDateTimeModule, OwlNativeDateTimeModule } from 'ng-pick-datetime';
import { SnotifyModule, SnotifyService, ToastDefaults } from 'ng-snotify';
import { VamaniportalSharedModule } from 'app/shared/shared.module';
import {
  VehicleMasterComponent,
  VehicleMasterDetailComponent,
  VehicleMasterUpdateComponent,
  VehicleMasterDeletePopupComponent,
  VehicleMasterDeleteDialogComponent,
  vehicleMasterRoute,
  vehicleMasterPopupRoute
} from './';

const ENTITY_STATES = [...vehicleMasterRoute, ...vehicleMasterPopupRoute];

@NgModule({
  imports: [VamaniportalSharedModule, RouterModule.forChild(ENTITY_STATES), OwlDateTimeModule, OwlNativeDateTimeModule, SnotifyModule],
  declarations: [
    VehicleMasterComponent,
    VehicleMasterDetailComponent,
    VehicleMasterUpdateComponent,
    VehicleMasterDeleteDialogComponent,
    VehicleMasterDeletePopupComponent
  ],
  entryComponents: [
    VehicleMasterComponent,
    VehicleMasterUpdateComponent,
    VehicleMasterDeleteDialogComponent,
    VehicleMasterDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
  providers: [{ provide: 'SnotifyToastConfig', useValue: ToastDefaults }, SnotifyService]
})
export class VamaniportalVehicleMasterModule {}
