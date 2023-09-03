import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { VamaniportalSharedModule } from 'app/shared/shared.module';
import {
  YarnTypeMasterComponent,
  YarnTypeMasterDetailComponent,
  YarnTypeMasterUpdateComponent,
  YarnTypeMasterDeletePopupComponent,
  YarnTypeMasterDeleteDialogComponent,
  yarnTypeMasterRoute,
  yarnTypeMasterPopupRoute
} from './';

const ENTITY_STATES = [...yarnTypeMasterRoute, ...yarnTypeMasterPopupRoute];

@NgModule({
  imports: [VamaniportalSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    YarnTypeMasterComponent,
    YarnTypeMasterDetailComponent,
    YarnTypeMasterUpdateComponent,
    YarnTypeMasterDeleteDialogComponent,
    YarnTypeMasterDeletePopupComponent
  ],
  entryComponents: [
    YarnTypeMasterComponent,
    YarnTypeMasterUpdateComponent,
    YarnTypeMasterDeleteDialogComponent,
    YarnTypeMasterDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class VamaniportalYarnTypeMasterModule {}
