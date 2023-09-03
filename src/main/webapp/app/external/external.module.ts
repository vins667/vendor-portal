import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { VamaniportalSharedModule } from 'app/shared/shared.module';
import { EXTERNAL_ROUTE, ExternalComponent } from './';

@NgModule({
  imports: [VamaniportalSharedModule, RouterModule.forChild([EXTERNAL_ROUTE])],
  declarations: [ExternalComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class VamaniportalExternalModule {}
