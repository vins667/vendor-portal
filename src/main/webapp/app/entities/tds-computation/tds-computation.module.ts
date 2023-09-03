import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { VamaniportalSharedModule } from 'app/shared/shared.module';
import { TdsComputationUpdateComponent, tdsComputationRoute, tdsComputationPopupRoute } from './';

const ENTITY_STATES = [...tdsComputationRoute, ...tdsComputationPopupRoute];

@NgModule({
  imports: [VamaniportalSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [TdsComputationUpdateComponent],
  entryComponents: [TdsComputationUpdateComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class VamaniportalTdsComputationModule {}
