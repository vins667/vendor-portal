import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { VamaniportalSharedModule } from 'app/shared/shared.module';
import {
  AssetAuditDetailsComponent,
  AssetAuditDetailsDetailComponent,
  AssetAuditDetailsUpdateComponent,
  AssetAuditDetailsDeletePopupComponent,
  AssetAuditDetailsDeleteDialogComponent,
  assetAuditDetailsRoute,
  assetAuditDetailsPopupRoute
} from './';

const ENTITY_STATES = [...assetAuditDetailsRoute, ...assetAuditDetailsPopupRoute];

@NgModule({
  imports: [VamaniportalSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    AssetAuditDetailsComponent,
    AssetAuditDetailsDetailComponent,
    AssetAuditDetailsUpdateComponent,
    AssetAuditDetailsDeleteDialogComponent,
    AssetAuditDetailsDeletePopupComponent
  ],
  entryComponents: [
    AssetAuditDetailsComponent,
    AssetAuditDetailsUpdateComponent,
    AssetAuditDetailsDeleteDialogComponent,
    AssetAuditDetailsDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class VamaniportalAssetAuditDetailsModule {}
