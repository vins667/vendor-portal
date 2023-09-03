import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { VamaniportalSharedModule } from 'app/shared/shared.module';
import {
  AssetLocationMasterComponent,
  AssetLocationMasterDetailComponent,
  AssetLocationMasterUpdateComponent,
  AssetLocationMasterDeletePopupComponent,
  AssetLocationMasterDeleteDialogComponent,
  assetLocationMasterRoute,
  assetLocationMasterPopupRoute
} from './';

const ENTITY_STATES = [...assetLocationMasterRoute, ...assetLocationMasterPopupRoute];

@NgModule({
  imports: [VamaniportalSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    AssetLocationMasterComponent,
    AssetLocationMasterDetailComponent,
    AssetLocationMasterUpdateComponent,
    AssetLocationMasterDeleteDialogComponent,
    AssetLocationMasterDeletePopupComponent
  ],
  entryComponents: [
    AssetLocationMasterComponent,
    AssetLocationMasterUpdateComponent,
    AssetLocationMasterDeleteDialogComponent,
    AssetLocationMasterDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class VamaniportalAssetLocationMasterModule {}
