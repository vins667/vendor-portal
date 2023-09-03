import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { VamaniportalSharedModule } from 'app/shared/shared.module';
import {
  AssetOwnershipMasterComponent,
  AssetOwnershipMasterDetailComponent,
  AssetOwnershipMasterUpdateComponent,
  AssetOwnershipMasterDeletePopupComponent,
  AssetOwnershipMasterDeleteDialogComponent,
  assetOwnershipMasterRoute,
  assetOwnershipMasterPopupRoute
} from './';

const ENTITY_STATES = [...assetOwnershipMasterRoute, ...assetOwnershipMasterPopupRoute];

@NgModule({
  imports: [VamaniportalSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    AssetOwnershipMasterComponent,
    AssetOwnershipMasterDetailComponent,
    AssetOwnershipMasterUpdateComponent,
    AssetOwnershipMasterDeleteDialogComponent,
    AssetOwnershipMasterDeletePopupComponent
  ],
  entryComponents: [
    AssetOwnershipMasterComponent,
    AssetOwnershipMasterUpdateComponent,
    AssetOwnershipMasterDeleteDialogComponent,
    AssetOwnershipMasterDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class VamaniportalAssetOwnershipMasterModule {}
