import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { VamaniportalSharedModule } from 'app/shared/shared.module';
import { OwlDateTimeModule, OwlNativeDateTimeModule } from 'ng-pick-datetime';
import { ConveyanceMasterProcessComponent } from './conveyance-master-process.component';
import { conveyanceMasterProcessRoute } from './conveyance-master-process.route';
const ENTITY_STATES = [...conveyanceMasterProcessRoute];
@NgModule({
  imports: [VamaniportalSharedModule, RouterModule.forChild(ENTITY_STATES), OwlDateTimeModule, OwlNativeDateTimeModule],
  declarations: [ConveyanceMasterProcessComponent],
  entryComponents: [ConveyanceMasterProcessComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class VamaniportalConveyanceMasterProcessModule {}
