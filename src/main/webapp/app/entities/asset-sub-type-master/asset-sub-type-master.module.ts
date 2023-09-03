import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { VamaniportalSharedModule } from 'app/shared/shared.module';
import {
  AssetSubTypeMasterComponent,
  AssetSubTypeMasterDetailComponent,
  AssetSubTypeMasterUpdateComponent,
  AssetSubTypeMasterDeletePopupComponent,
  AssetSubTypeMasterDeleteDialogComponent,
  assetSubTypeMasterRoute,
  assetSubTypeMasterPopupRoute
} from './';

const ENTITY_STATES = [...assetSubTypeMasterRoute, ...assetSubTypeMasterPopupRoute];

@NgModule({
  imports: [VamaniportalSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    AssetSubTypeMasterComponent,
    AssetSubTypeMasterDetailComponent,
    AssetSubTypeMasterUpdateComponent,
    AssetSubTypeMasterDeleteDialogComponent,
    AssetSubTypeMasterDeletePopupComponent
  ],
  entryComponents: [
    AssetSubTypeMasterComponent,
    AssetSubTypeMasterUpdateComponent,
    AssetSubTypeMasterDeleteDialogComponent,
    AssetSubTypeMasterDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class VamaniportalAssetSubTypeMasterModule {}
