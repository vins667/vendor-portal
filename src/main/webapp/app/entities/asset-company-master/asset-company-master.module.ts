import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { VamaniportalSharedModule } from 'app/shared/shared.module';
import {
  AssetCompanyMasterComponent,
  AssetCompanyMasterDetailComponent,
  AssetCompanyMasterUpdateComponent,
  AssetCompanyMasterDeletePopupComponent,
  AssetCompanyMasterDeleteDialogComponent,
  assetCompanyMasterRoute,
  assetCompanyMasterPopupRoute
} from './';

const ENTITY_STATES = [...assetCompanyMasterRoute, ...assetCompanyMasterPopupRoute];

@NgModule({
  imports: [VamaniportalSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    AssetCompanyMasterComponent,
    AssetCompanyMasterDetailComponent,
    AssetCompanyMasterUpdateComponent,
    AssetCompanyMasterDeleteDialogComponent,
    AssetCompanyMasterDeletePopupComponent
  ],
  entryComponents: [
    AssetCompanyMasterComponent,
    AssetCompanyMasterUpdateComponent,
    AssetCompanyMasterDeleteDialogComponent,
    AssetCompanyMasterDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class VamaniportalAssetCompanyMasterModule {}
