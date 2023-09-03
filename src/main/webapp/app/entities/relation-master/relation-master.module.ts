import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { VamaniportalSharedModule } from 'app/shared/shared.module';
import {
  RelationMasterComponent,
  RelationMasterDetailComponent,
  RelationMasterUpdateComponent,
  RelationMasterDeletePopupComponent,
  RelationMasterDeleteDialogComponent,
  relationMasterRoute,
  relationMasterPopupRoute
} from './';

const ENTITY_STATES = [...relationMasterRoute, ...relationMasterPopupRoute];

@NgModule({
  imports: [VamaniportalSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    RelationMasterComponent,
    RelationMasterDetailComponent,
    RelationMasterUpdateComponent,
    RelationMasterDeleteDialogComponent,
    RelationMasterDeletePopupComponent
  ],
  entryComponents: [
    RelationMasterComponent,
    RelationMasterUpdateComponent,
    RelationMasterDeleteDialogComponent,
    RelationMasterDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class VamaniportalRelationMasterModule {}
