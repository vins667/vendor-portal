import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { VamaniportalSharedModule } from 'app/shared/shared.module';
import { OwlDateTimeModule, OwlNativeDateTimeModule } from 'ng-pick-datetime';
import { ConveyanceMasterHrComponent, ConveyanceMasterHrUpdateComponent, conveyanceMasterHrRoute } from './';
const ENTITY_STATES = [...conveyanceMasterHrRoute];
@NgModule({
  imports: [VamaniportalSharedModule, RouterModule.forChild(ENTITY_STATES), OwlDateTimeModule, OwlNativeDateTimeModule],
  declarations: [ConveyanceMasterHrComponent, ConveyanceMasterHrUpdateComponent],
  entryComponents: [ConveyanceMasterHrComponent, ConveyanceMasterHrUpdateComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class VamaniportalConveyanceMasterHrModule {}
