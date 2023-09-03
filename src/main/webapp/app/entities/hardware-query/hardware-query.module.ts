import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { VamaniportalSharedModule } from 'app/shared/shared.module';
import { HardwareQueryComponent, hardwareQueryRoute, hardwareQueryPopupRoute } from './';

const ENTITY_STATES = [...hardwareQueryRoute, ...hardwareQueryPopupRoute];

@NgModule({
  imports: [VamaniportalSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [HardwareQueryComponent],
  entryComponents: [HardwareQueryComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class VamaniportalHardwareQueryModule {}
