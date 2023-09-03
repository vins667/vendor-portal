import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { OwlDateTimeModule, OwlNativeDateTimeModule } from 'ng-pick-datetime';
import { VamaniportalSharedModule } from 'app/shared/shared.module';
import {
  AssetMasterComponent,
  AssetMasterDetailComponent,
  AssetMasterUpdateComponent,
  AssetMasterDeletePopupComponent,
  AssetMasterDeleteDialogComponent,
  assetMasterRoute,
  assetMasterPopupRoute,
  AssetAuditSearchComponent,
  AssetFileUploadDetailsComponent
} from './';
import { ToastDefaults, SnotifyService, SnotifyModule } from 'ng-snotify';

const ENTITY_STATES = [...assetMasterRoute, ...assetMasterPopupRoute];

@NgModule({
  imports: [VamaniportalSharedModule, RouterModule.forChild(ENTITY_STATES), OwlDateTimeModule, OwlNativeDateTimeModule, SnotifyModule],
  declarations: [
    AssetMasterComponent,
    AssetMasterDetailComponent,
    AssetMasterUpdateComponent,
    AssetMasterDeleteDialogComponent,
    AssetMasterDeletePopupComponent,
    AssetFileUploadDetailsComponent,
    AssetAuditSearchComponent
  ],
  entryComponents: [
    AssetMasterComponent,
    AssetMasterUpdateComponent,
    AssetMasterDeleteDialogComponent,
    AssetMasterDeletePopupComponent,
    AssetFileUploadDetailsComponent,
    AssetAuditSearchComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
  providers: [{ provide: 'SnotifyToastConfig', useValue: ToastDefaults }, SnotifyService]
})
export class VamaniportalAssetMasterModule {}
