import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { VamaniportalSharedModule } from 'app/shared/shared.module';
import {
  TrimsTemplateMasterComponent,
  TrimsTemplateMasterDetailComponent,
  TrimsTemplateMasterUpdateComponent,
  TrimsTemplateMasterDeletePopupComponent,
  TrimsTemplateMasterDeleteDialogComponent,
  trimsTemplateMasterRoute,
  trimsTemplateMasterPopupRoute
} from './';

const ENTITY_STATES = [...trimsTemplateMasterRoute, ...trimsTemplateMasterPopupRoute];

@NgModule({
  imports: [VamaniportalSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    TrimsTemplateMasterComponent,
    TrimsTemplateMasterDetailComponent,
    TrimsTemplateMasterUpdateComponent,
    TrimsTemplateMasterDeleteDialogComponent,
    TrimsTemplateMasterDeletePopupComponent
  ],
  entryComponents: [
    TrimsTemplateMasterComponent,
    TrimsTemplateMasterUpdateComponent,
    TrimsTemplateMasterDeleteDialogComponent,
    TrimsTemplateMasterDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class VamaniportalTrimsTemplateMasterModule {}
