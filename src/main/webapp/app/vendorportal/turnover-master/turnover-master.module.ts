import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { VamaniportalSharedModule } from 'app/shared/shared.module';
import {
  TurnoverMasterComponent,
  TurnoverMasterDetailComponent,
  TurnoverMasterUpdateComponent,
  TurnoverMasterDeletePopupComponent,
  TurnoverMasterDeleteDialogComponent,
  turnoverMasterRoute,
  turnoverMasterPopupRoute
} from './index';

const ENTITY_STATES = [...turnoverMasterRoute, ...turnoverMasterPopupRoute];

@NgModule({
  imports: [VamaniportalSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    TurnoverMasterComponent,
    TurnoverMasterDetailComponent,
    TurnoverMasterUpdateComponent,
    TurnoverMasterDeleteDialogComponent,
    TurnoverMasterDeletePopupComponent
  ],
  entryComponents: [
    TurnoverMasterComponent,
    TurnoverMasterUpdateComponent,
    TurnoverMasterDeleteDialogComponent,
    TurnoverMasterDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class VendorportalTurnoverMasterModule {}
