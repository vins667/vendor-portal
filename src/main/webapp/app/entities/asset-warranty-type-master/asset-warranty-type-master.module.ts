import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { VamaniportalSharedModule } from 'app/shared/shared.module';
import {
  AssetWarrantyTypeMasterComponent,
  AssetWarrantyTypeMasterDetailComponent,
  AssetWarrantyTypeMasterUpdateComponent,
  AssetWarrantyTypeMasterDeletePopupComponent,
  AssetWarrantyTypeMasterDeleteDialogComponent,
  assetWarrantyTypeMasterRoute,
  assetWarrantyTypeMasterPopupRoute
} from './';

const ENTITY_STATES = [...assetWarrantyTypeMasterRoute, ...assetWarrantyTypeMasterPopupRoute];

@NgModule({
  imports: [VamaniportalSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    AssetWarrantyTypeMasterComponent,
    AssetWarrantyTypeMasterDetailComponent,
    AssetWarrantyTypeMasterUpdateComponent,
    AssetWarrantyTypeMasterDeleteDialogComponent,
    AssetWarrantyTypeMasterDeletePopupComponent
  ],
  entryComponents: [
    AssetWarrantyTypeMasterComponent,
    AssetWarrantyTypeMasterUpdateComponent,
    AssetWarrantyTypeMasterDeleteDialogComponent,
    AssetWarrantyTypeMasterDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class VamaniportalAssetWarrantyTypeMasterModule {}
