import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { VamaniportalSharedModule } from 'app/shared/shared.module';
import {
  FabricUomMasterComponent,
  FabricUomMasterDetailComponent,
  FabricUomMasterUpdateComponent,
  FabricUomMasterDeletePopupComponent,
  FabricUomMasterDeleteDialogComponent,
  fabricUomMasterRoute,
  fabricUomMasterPopupRoute
} from './';

const ENTITY_STATES = [...fabricUomMasterRoute, ...fabricUomMasterPopupRoute];

@NgModule({
  imports: [VamaniportalSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    FabricUomMasterComponent,
    FabricUomMasterDetailComponent,
    FabricUomMasterUpdateComponent,
    FabricUomMasterDeleteDialogComponent,
    FabricUomMasterDeletePopupComponent
  ],
  entryComponents: [
    FabricUomMasterComponent,
    FabricUomMasterUpdateComponent,
    FabricUomMasterDeleteDialogComponent,
    FabricUomMasterDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class VamaniportalFabricUomMasterModule {}
