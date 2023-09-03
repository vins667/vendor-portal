import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { VamaniportalSharedModule } from 'app/shared/shared.module';
import { AssetAuditSoftwareCompareComponent, assetAuditSoftwareCompareRoute, assetAuditSoftwareComparePopupRoute } from './';

const ENTITY_STATES = [...assetAuditSoftwareCompareRoute, ...assetAuditSoftwareComparePopupRoute];

@NgModule({
  imports: [VamaniportalSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [AssetAuditSoftwareCompareComponent],
  entryComponents: [AssetAuditSoftwareCompareComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class VamaniportalAssetAuditSoftwareCompareModule {}
