import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { VamaniportalSharedModule } from 'app/shared/shared.module';
import {
  IgnoreSoftwareMasterComponent,
  IgnoreSoftwareMasterDetailComponent,
  IgnoreSoftwareMasterUpdateComponent,
  IgnoreSoftwareMasterDeletePopupComponent,
  IgnoreSoftwareMasterDeleteDialogComponent,
  ignoreSoftwareMasterRoute,
  ignoreSoftwareMasterPopupRoute
} from './';

const ENTITY_STATES = [...ignoreSoftwareMasterRoute, ...ignoreSoftwareMasterPopupRoute];

@NgModule({
  imports: [VamaniportalSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    IgnoreSoftwareMasterComponent,
    IgnoreSoftwareMasterDetailComponent,
    IgnoreSoftwareMasterUpdateComponent,
    IgnoreSoftwareMasterDeleteDialogComponent,
    IgnoreSoftwareMasterDeletePopupComponent
  ],
  entryComponents: [
    IgnoreSoftwareMasterComponent,
    IgnoreSoftwareMasterUpdateComponent,
    IgnoreSoftwareMasterDeleteDialogComponent,
    IgnoreSoftwareMasterDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class VamaniportalIgnoreSoftwareMasterModule {}
