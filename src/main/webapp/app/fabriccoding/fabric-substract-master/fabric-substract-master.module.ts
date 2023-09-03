import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { VamaniportalSharedModule } from 'app/shared/shared.module';
import {
  FabricSubstractMasterComponent,
  FabricSubstractMasterDetailComponent,
  FabricSubstractMasterUpdateComponent,
  FabricSubstractMasterDeletePopupComponent,
  FabricSubstractMasterDeleteDialogComponent,
  fabricSubstractMasterRoute,
  fabricSubstractMasterPopupRoute
} from './';

const ENTITY_STATES = [...fabricSubstractMasterRoute, ...fabricSubstractMasterPopupRoute];

@NgModule({
  imports: [VamaniportalSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    FabricSubstractMasterComponent,
    FabricSubstractMasterDetailComponent,
    FabricSubstractMasterUpdateComponent,
    FabricSubstractMasterDeleteDialogComponent,
    FabricSubstractMasterDeletePopupComponent
  ],
  entryComponents: [
    FabricSubstractMasterComponent,
    FabricSubstractMasterUpdateComponent,
    FabricSubstractMasterDeleteDialogComponent,
    FabricSubstractMasterDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class VamaniportalFabricSubstractMasterModule {}
