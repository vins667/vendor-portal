import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { VamaniportalSharedModule } from 'app/shared/shared.module';
import {
  LanguageMasterComponent,
  LanguageMasterDetailComponent,
  LanguageMasterUpdateComponent,
  LanguageMasterDeletePopupComponent,
  LanguageMasterDeleteDialogComponent,
  languageMasterRoute,
  languageMasterPopupRoute
} from './';

const ENTITY_STATES = [...languageMasterRoute, ...languageMasterPopupRoute];

@NgModule({
  imports: [VamaniportalSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    LanguageMasterComponent,
    LanguageMasterDetailComponent,
    LanguageMasterUpdateComponent,
    LanguageMasterDeleteDialogComponent,
    LanguageMasterDeletePopupComponent
  ],
  entryComponents: [
    LanguageMasterComponent,
    LanguageMasterUpdateComponent,
    LanguageMasterDeleteDialogComponent,
    LanguageMasterDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class VamaniportalLanguageMasterModule {}
