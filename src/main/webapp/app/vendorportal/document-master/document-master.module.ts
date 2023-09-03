import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { VamaniportalSharedModule } from 'app/shared/shared.module';
import {
  DocumentMasterComponent,
  DocumentMasterDetailComponent,
  DocumentMasterUpdateComponent,
  DocumentMasterDeletePopupComponent,
  DocumentMasterDeleteDialogComponent,
  documentMasterRoute,
  documentMasterPopupRoute
} from './index';

const ENTITY_STATES = [...documentMasterRoute, ...documentMasterPopupRoute];

@NgModule({
  imports: [VamaniportalSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    DocumentMasterComponent,
    DocumentMasterDetailComponent,
    DocumentMasterUpdateComponent,
    DocumentMasterDeleteDialogComponent,
    DocumentMasterDeletePopupComponent
  ],
  entryComponents: [
    DocumentMasterComponent,
    DocumentMasterUpdateComponent,
    DocumentMasterDeleteDialogComponent,
    DocumentMasterDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class VendorportalDocumentMasterModule {}
