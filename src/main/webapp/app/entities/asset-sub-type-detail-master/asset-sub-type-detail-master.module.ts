import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { VamaniportalSharedModule } from 'app/shared/shared.module';
import {
  AssetSubTypeDetailMasterComponent,
  AssetSubTypeDetailMasterDetailComponent,
  AssetSubTypeDetailMasterUpdateComponent,
  AssetSubTypeDetailMasterDeletePopupComponent,
  AssetSubTypeDetailMasterDeleteDialogComponent,
  assetSubTypeDetailMasterRoute,
  assetSubTypeDetailMasterPopupRoute
} from './';

const ENTITY_STATES = [...assetSubTypeDetailMasterRoute, ...assetSubTypeDetailMasterPopupRoute];

@NgModule({
  imports: [VamaniportalSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    AssetSubTypeDetailMasterComponent,
    AssetSubTypeDetailMasterDetailComponent,
    AssetSubTypeDetailMasterUpdateComponent,
    AssetSubTypeDetailMasterDeleteDialogComponent,
    AssetSubTypeDetailMasterDeletePopupComponent
  ],
  entryComponents: [
    AssetSubTypeDetailMasterComponent,
    AssetSubTypeDetailMasterUpdateComponent,
    AssetSubTypeDetailMasterDeleteDialogComponent,
    AssetSubTypeDetailMasterDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class VamaniportalAssetSubTypeDetailMasterModule {}
