import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { VamaniportalSharedModule } from 'app/shared/shared.module';
import {
  FabricOthersMasterComponent,
  FabricOthersMasterDetailComponent,
  FabricOthersMasterUpdateComponent,
  FabricOthersMasterDeletePopupComponent,
  FabricOthersMasterDeleteDialogComponent,
  fabricOthersMasterRoute,
  fabricOthersMasterPopupRoute
} from './';

const ENTITY_STATES = [...fabricOthersMasterRoute, ...fabricOthersMasterPopupRoute];

@NgModule({
  imports: [VamaniportalSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    FabricOthersMasterComponent,
    FabricOthersMasterDetailComponent,
    FabricOthersMasterUpdateComponent,
    FabricOthersMasterDeleteDialogComponent,
    FabricOthersMasterDeletePopupComponent
  ],
  entryComponents: [
    FabricOthersMasterComponent,
    FabricOthersMasterUpdateComponent,
    FabricOthersMasterDeleteDialogComponent,
    FabricOthersMasterDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class VamaniportalFabricOthersMasterModule {}
