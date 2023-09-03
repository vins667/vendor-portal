import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { VamaniportalSharedModule } from 'app/shared/shared.module';
import {
  StateComponent,
  StateDetailComponent,
  StateUpdateComponent,
  StateDeletePopupComponent,
  StateDeleteDialogComponent,
  stateRoute,
  statePopupRoute
} from './index';

const ENTITY_STATES = [...stateRoute, ...statePopupRoute];

@NgModule({
  imports: [VamaniportalSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [StateComponent, StateDetailComponent, StateUpdateComponent, StateDeleteDialogComponent, StateDeletePopupComponent],
  entryComponents: [StateComponent, StateUpdateComponent, StateDeleteDialogComponent, StateDeletePopupComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class VendorportalStateModule {}
