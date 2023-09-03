import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { VamaniportalSharedModule } from 'app/shared/shared.module';
import {
  OperationMasterComponent,
  OperationMasterDetailComponent,
  OperationMasterUpdateComponent,
  OperationMasterDeletePopupComponent,
  OperationMasterDeleteDialogComponent,
  operationMasterRoute,
  operationMasterPopupRoute
} from './';

const ENTITY_STATES = [...operationMasterRoute, ...operationMasterPopupRoute];

@NgModule({
  imports: [VamaniportalSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    OperationMasterComponent,
    OperationMasterDetailComponent,
    OperationMasterUpdateComponent,
    OperationMasterDeleteDialogComponent,
    OperationMasterDeletePopupComponent
  ],
  entryComponents: [
    OperationMasterComponent,
    OperationMasterUpdateComponent,
    OperationMasterDeleteDialogComponent,
    OperationMasterDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class VamaniportalOperationMasterModule {}
