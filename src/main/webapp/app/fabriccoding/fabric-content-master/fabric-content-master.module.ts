import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { VamaniportalSharedModule } from 'app/shared/shared.module';
import {
  FabricContentMasterComponent,
  FabricContentMasterDetailComponent,
  FabricContentMasterUpdateComponent,
  FabricContentMasterDeletePopupComponent,
  FabricContentMasterDeleteDialogComponent,
  fabricContentMasterRoute,
  fabricContentMasterPopupRoute
} from './';

const ENTITY_STATES = [...fabricContentMasterRoute, ...fabricContentMasterPopupRoute];

@NgModule({
  imports: [VamaniportalSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    FabricContentMasterComponent,
    FabricContentMasterDetailComponent,
    FabricContentMasterUpdateComponent,
    FabricContentMasterDeleteDialogComponent,
    FabricContentMasterDeletePopupComponent
  ],
  entryComponents: [
    FabricContentMasterComponent,
    FabricContentMasterUpdateComponent,
    FabricContentMasterDeleteDialogComponent,
    FabricContentMasterDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class VamaniportalFabricContentMasterModule {}
