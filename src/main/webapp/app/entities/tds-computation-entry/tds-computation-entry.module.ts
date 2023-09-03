import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { VamaniportalSharedModule } from 'app/shared/shared.module';
import {
  TdsComputationEntryComponent,
  TdsComputationEntryUpdateComponent,
  tdsComputationEntryRoute,
  tdsComputationEntryPopupRoute
} from './';
import { UiSwitchModule } from 'ngx-ui-switch';

const ENTITY_STATES = [...tdsComputationEntryRoute, ...tdsComputationEntryPopupRoute];

@NgModule({
  imports: [VamaniportalSharedModule, RouterModule.forChild(ENTITY_STATES), UiSwitchModule],
  declarations: [TdsComputationEntryComponent, TdsComputationEntryUpdateComponent],
  entryComponents: [TdsComputationEntryComponent, TdsComputationEntryUpdateComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class VamaniportalTdsComputationEntryModule {}
