import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { VamaniportalSharedModule } from 'app/shared/shared.module';
import {
  FabricSplFinishMasterComponent,
  FabricSplFinishMasterDetailComponent,
  FabricSplFinishMasterUpdateComponent,
  FabricSplFinishMasterDeletePopupComponent,
  FabricSplFinishMasterDeleteDialogComponent,
  fabricSplFinishMasterRoute,
  fabricSplFinishMasterPopupRoute
} from './';

const ENTITY_STATES = [...fabricSplFinishMasterRoute, ...fabricSplFinishMasterPopupRoute];

@NgModule({
  imports: [VamaniportalSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    FabricSplFinishMasterComponent,
    FabricSplFinishMasterDetailComponent,
    FabricSplFinishMasterUpdateComponent,
    FabricSplFinishMasterDeleteDialogComponent,
    FabricSplFinishMasterDeletePopupComponent
  ],
  entryComponents: [
    FabricSplFinishMasterComponent,
    FabricSplFinishMasterUpdateComponent,
    FabricSplFinishMasterDeleteDialogComponent,
    FabricSplFinishMasterDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class VamaniportalFabricSplFinishMasterModule {}
