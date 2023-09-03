import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { VamaniportalSharedModule } from 'app/shared/shared.module';
import { TdsSlabMasterUpdateComponent, tdsSlabMasterRoute, TdsSlabMasterComponent } from './';

const ENTITY_STATES = [...tdsSlabMasterRoute];

@NgModule({
  imports: [VamaniportalSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [TdsSlabMasterComponent, TdsSlabMasterUpdateComponent],
  entryComponents: [TdsSlabMasterComponent, TdsSlabMasterUpdateComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class VamaniportalTdsSlabMasterModule {}
