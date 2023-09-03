import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { VamaniportalSharedModule } from 'app/shared/shared.module';
import {
  AssetSoftTypeMasterComponent,
  AssetSoftTypeMasterDetailComponent,
  AssetSoftTypeMasterUpdateComponent,
  AssetSoftTypeMasterDeletePopupComponent,
  AssetSoftTypeMasterDeleteDialogComponent,
  assetSoftTypeMasterRoute,
  assetSoftTypeMasterPopupRoute
} from './';

const ENTITY_STATES = [...assetSoftTypeMasterRoute, ...assetSoftTypeMasterPopupRoute];

@NgModule({
  imports: [VamaniportalSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    AssetSoftTypeMasterComponent,
    AssetSoftTypeMasterDetailComponent,
    AssetSoftTypeMasterUpdateComponent,
    AssetSoftTypeMasterDeleteDialogComponent,
    AssetSoftTypeMasterDeletePopupComponent
  ],
  entryComponents: [
    AssetSoftTypeMasterComponent,
    AssetSoftTypeMasterUpdateComponent,
    AssetSoftTypeMasterDeleteDialogComponent,
    AssetSoftTypeMasterDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class VamaniportalAssetSoftTypeMasterModule {}
