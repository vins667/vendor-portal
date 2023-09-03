import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { VamaniportalSharedModule } from 'app/shared/shared.module';
import { FormsDownloadComponent, formsDownloadRoute, formsDownloadPopupRoute } from './';
import { PdfViewerModule } from 'ng2-pdf-viewer';

const ENTITY_STATES = [...formsDownloadRoute, ...formsDownloadPopupRoute];

@NgModule({
  imports: [VamaniportalSharedModule, RouterModule.forChild(ENTITY_STATES), PdfViewerModule],
  declarations: [FormsDownloadComponent],
  entryComponents: [FormsDownloadComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class VamaniportalFormsDownloadModule {}
