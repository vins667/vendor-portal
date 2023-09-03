import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { VamaniportalSharedModule } from 'app/shared/shared.module';
import {
  FabricSubstractDetailsComponent,
  FabricSubstractDetailsDetailComponent,
  FabricSubstractDetailsUpdateComponent,
  FabricSubstractDetailsDeletePopupComponent,
  FabricSubstractDetailsDeleteDialogComponent,
  fabricSubstractDetailsRoute,
  fabricSubstractDetailsPopupRoute
} from './';

const ENTITY_STATES = [...fabricSubstractDetailsRoute, ...fabricSubstractDetailsPopupRoute];

@NgModule({
  imports: [VamaniportalSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    FabricSubstractDetailsComponent,
    FabricSubstractDetailsDetailComponent,
    FabricSubstractDetailsUpdateComponent,
    FabricSubstractDetailsDeleteDialogComponent,
    FabricSubstractDetailsDeletePopupComponent
  ],
  entryComponents: [
    FabricSubstractDetailsComponent,
    FabricSubstractDetailsUpdateComponent,
    FabricSubstractDetailsDeleteDialogComponent,
    FabricSubstractDetailsDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class VamaniportalFabricSubstractDetailsModule {}
