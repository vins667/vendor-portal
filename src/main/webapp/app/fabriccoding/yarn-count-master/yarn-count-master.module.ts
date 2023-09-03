import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { VamaniportalSharedModule } from 'app/shared/shared.module';
import {
  YarnCountMasterComponent,
  YarnCountMasterDetailComponent,
  YarnCountMasterUpdateComponent,
  YarnCountMasterDeletePopupComponent,
  YarnCountMasterDeleteDialogComponent,
  yarnCountMasterRoute,
  yarnCountMasterPopupRoute
} from './';

const ENTITY_STATES = [...yarnCountMasterRoute, ...yarnCountMasterPopupRoute];

@NgModule({
  imports: [VamaniportalSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    YarnCountMasterComponent,
    YarnCountMasterDetailComponent,
    YarnCountMasterUpdateComponent,
    YarnCountMasterDeleteDialogComponent,
    YarnCountMasterDeletePopupComponent
  ],
  entryComponents: [
    YarnCountMasterComponent,
    YarnCountMasterUpdateComponent,
    YarnCountMasterDeleteDialogComponent,
    YarnCountMasterDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class VamaniportalYarnCountMasterModule {}
