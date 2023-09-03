import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { VamaniportalSharedModule } from 'app/shared/shared.module';
import { SoftwareQueryComponent, softwareQueryRoute, softwareQueryPopupRoute } from './';

const ENTITY_STATES = [...softwareQueryRoute, ...softwareQueryPopupRoute];

@NgModule({
  imports: [VamaniportalSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [SoftwareQueryComponent],
  entryComponents: [SoftwareQueryComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class VamaniportalSoftwareQueryModule {}
