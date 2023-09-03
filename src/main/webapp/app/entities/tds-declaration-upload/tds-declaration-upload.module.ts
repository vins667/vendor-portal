import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import {
  TdsDeclarationUploadUpdateComponent,
  TdsDeclarationUploadPopupComponent,
  tdsDeclarationUploadRoute,
  tdsDeclarationUploadPopupRoute
} from './';
import { VamaniportalSharedModule } from 'app/shared/shared.module';

const ENTITY_STATES = [...tdsDeclarationUploadRoute, ...tdsDeclarationUploadPopupRoute];

@NgModule({
  imports: [VamaniportalSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [TdsDeclarationUploadUpdateComponent, TdsDeclarationUploadPopupComponent],
  entryComponents: [TdsDeclarationUploadUpdateComponent, TdsDeclarationUploadPopupComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class VamaniportalTdsDeclarationUploadModule {}
