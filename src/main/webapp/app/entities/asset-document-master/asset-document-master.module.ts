import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { VamaniportalSharedModule } from 'app/shared/shared.module';
import {
  AssetDocumentMasterComponent,
  AssetDocumentMasterDetailComponent,
  AssetDocumentMasterUpdateComponent,
  AssetDocumentMasterDeletePopupComponent,
  AssetDocumentMasterDeleteDialogComponent,
  assetDocumentMasterRoute,
  assetDocumentMasterPopupRoute
} from './';

const ENTITY_STATES = [...assetDocumentMasterRoute, ...assetDocumentMasterPopupRoute];

@NgModule({
  imports: [VamaniportalSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    AssetDocumentMasterComponent,
    AssetDocumentMasterDetailComponent,
    AssetDocumentMasterUpdateComponent,
    AssetDocumentMasterDeleteDialogComponent,
    AssetDocumentMasterDeletePopupComponent
  ],
  entryComponents: [
    AssetDocumentMasterComponent,
    AssetDocumentMasterUpdateComponent,
    AssetDocumentMasterDeleteDialogComponent,
    AssetDocumentMasterDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class VamaniportalAssetDocumentMasterModule {}
