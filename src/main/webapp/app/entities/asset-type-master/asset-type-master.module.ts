import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { VamaniportalSharedModule } from 'app/shared/shared.module';
import {
  AssetTypeMasterComponent,
  AssetTypeMasterDetailComponent,
  AssetTypeMasterUpdateComponent,
  AssetTypeMasterDeletePopupComponent,
  AssetTypeMasterDeleteDialogComponent,
  assetTypeMasterRoute,
  assetTypeMasterPopupRoute
} from './';

const ENTITY_STATES = [...assetTypeMasterRoute, ...assetTypeMasterPopupRoute];

@NgModule({
  imports: [VamaniportalSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    AssetTypeMasterComponent,
    AssetTypeMasterDetailComponent,
    AssetTypeMasterUpdateComponent,
    AssetTypeMasterDeleteDialogComponent,
    AssetTypeMasterDeletePopupComponent
  ],
  entryComponents: [
    AssetTypeMasterComponent,
    AssetTypeMasterUpdateComponent,
    AssetTypeMasterDeleteDialogComponent,
    AssetTypeMasterDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class VamaniportalAssetTypeMasterModule {}
