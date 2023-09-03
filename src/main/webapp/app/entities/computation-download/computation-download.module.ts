import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { VamaniportalSharedModule } from 'app/shared/shared.module';
import { ComputationDownloadComponent, ComputationDownloadRoute, ComputationDownloadPopupRoute } from './';
import { PdfViewerModule } from 'ng2-pdf-viewer';
import { SnotifyModule } from 'ng-snotify';

const ENTITY_STATES = [...ComputationDownloadRoute, ...ComputationDownloadPopupRoute];

@NgModule({
  imports: [VamaniportalSharedModule, RouterModule.forChild(ENTITY_STATES), PdfViewerModule, SnotifyModule],
  declarations: [ComputationDownloadComponent],
  entryComponents: [ComputationDownloadComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class VamaniportalComputationDownloadModule {}
