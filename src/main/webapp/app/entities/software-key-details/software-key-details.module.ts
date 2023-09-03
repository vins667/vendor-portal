import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { VamaniportalSharedModule } from 'app/shared/shared.module';
import { SoftwareKeyDetailsComponent, softwareKeyDetailsRoute, softwareKeyDetailsPopupRoute } from './';

const ENTITY_STATES = [...softwareKeyDetailsRoute, ...softwareKeyDetailsPopupRoute];

@NgModule({
  imports: [VamaniportalSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [SoftwareKeyDetailsComponent],
  entryComponents: [SoftwareKeyDetailsComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class VamaniportalSoftwareKeyDetailsModule {}
