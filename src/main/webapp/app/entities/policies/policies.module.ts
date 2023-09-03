import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { VamaniportalSharedModule } from 'app/shared/shared.module';
import { PdfViewerModule } from 'ng2-pdf-viewer';
import { PoliciesComponent, policiesRoute, policiesPopupRoute } from './';

const ENTITY_STATES = [...policiesRoute, ...policiesPopupRoute];

@NgModule({
  imports: [VamaniportalSharedModule, RouterModule.forChild(ENTITY_STATES), PdfViewerModule],
  declarations: [PoliciesComponent],
  entryComponents: [PoliciesComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class VamaniportalPoliciesModule {}
