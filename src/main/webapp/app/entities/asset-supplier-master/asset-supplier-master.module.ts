import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { VamaniportalSharedModule } from 'app/shared/shared.module';
import {
  AssetSupplierMasterComponent,
  AssetSupplierMasterDetailComponent,
  AssetSupplierMasterUpdateComponent,
  AssetSupplierMasterDeletePopupComponent,
  AssetSupplierMasterDeleteDialogComponent,
  assetSupplierMasterRoute,
  assetSupplierMasterPopupRoute
} from './';

const ENTITY_STATES = [...assetSupplierMasterRoute, ...assetSupplierMasterPopupRoute];

@NgModule({
  imports: [VamaniportalSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    AssetSupplierMasterComponent,
    AssetSupplierMasterDetailComponent,
    AssetSupplierMasterUpdateComponent,
    AssetSupplierMasterDeleteDialogComponent,
    AssetSupplierMasterDeletePopupComponent
  ],
  entryComponents: [
    AssetSupplierMasterComponent,
    AssetSupplierMasterUpdateComponent,
    AssetSupplierMasterDeleteDialogComponent,
    AssetSupplierMasterDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class VamaniportalAssetSupplierMasterModule {}
