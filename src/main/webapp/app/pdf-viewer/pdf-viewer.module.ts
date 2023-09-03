import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { VamaniportalSharedModule } from 'app/shared/shared.module';
import { NdaComponent, NgbdModalComponent, NgbdViewModalComponent, NDA_ROUTE, NdaViewComponent } from './';
import { PdfViewerModule } from 'ng2-pdf-viewer';

@NgModule({
  imports: [VamaniportalSharedModule, PdfViewerModule, RouterModule.forChild(NDA_ROUTE)],
  declarations: [NgbdModalComponent, NgbdViewModalComponent, NdaComponent, NdaViewComponent],
  entryComponents: [NgbdModalComponent, NgbdViewModalComponent, NdaComponent, NdaViewComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class VamaniportalPdfViewerModule {}
