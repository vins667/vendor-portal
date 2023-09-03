import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import {
  VcutStyleImageComponent,
  VcutStyleImageDetailComponent,
  VcutStyleImageUpdateComponent,
  VcutStyleImageDeletePopupComponent,
  VcutStyleImageDeleteDialogComponent,
  vcutStyleImageRoute,
  vcutStyleImagePopupRoute
} from './';
import { VamaniportalSharedModule } from 'app/shared/shared.module';

const ENTITY_STATES = [...vcutStyleImageRoute, ...vcutStyleImagePopupRoute];

@NgModule({
  imports: [VamaniportalSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    VcutStyleImageComponent,
    VcutStyleImageDetailComponent,
    VcutStyleImageUpdateComponent,
    VcutStyleImageDeleteDialogComponent,
    VcutStyleImageDeletePopupComponent
  ],
  entryComponents: [
    VcutStyleImageComponent,
    VcutStyleImageUpdateComponent,
    VcutStyleImageDeleteDialogComponent,
    VcutStyleImageDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class VamaniportalVcutStyleImageModule {}
