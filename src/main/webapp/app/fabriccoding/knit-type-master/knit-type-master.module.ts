import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { VamaniportalSharedModule } from 'app/shared/shared.module';
import {
  KnitTypeMasterComponent,
  KnitTypeMasterDetailComponent,
  KnitTypeMasterUpdateComponent,
  KnitTypeMasterDeletePopupComponent,
  KnitTypeMasterDeleteDialogComponent,
  knitTypeMasterRoute,
  knitTypeMasterPopupRoute
} from './';

const ENTITY_STATES = [...knitTypeMasterRoute, ...knitTypeMasterPopupRoute];

@NgModule({
  imports: [VamaniportalSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    KnitTypeMasterComponent,
    KnitTypeMasterDetailComponent,
    KnitTypeMasterUpdateComponent,
    KnitTypeMasterDeleteDialogComponent,
    KnitTypeMasterDeletePopupComponent
  ],
  entryComponents: [
    KnitTypeMasterComponent,
    KnitTypeMasterUpdateComponent,
    KnitTypeMasterDeleteDialogComponent,
    KnitTypeMasterDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class VamaniportalKnitTypeMasterModule {}
