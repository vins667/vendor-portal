import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { VamaniportalSharedModule } from 'app/shared/shared.module';
import { PdfViewerModule } from 'ng2-pdf-viewer';
import {
  PoliciesViewComponent,
  PoliciesUpdateComponent,
  PoliciesDeleteDialogComponent,
  policiesEntryRoute,
  policiesEntryPopupRoute,
  PoliciesDeletePopupComponent,
  PoliciesSortComponent
} from './';

const ENTITY_STATES = [...policiesEntryRoute, ...policiesEntryPopupRoute];

@NgModule({
  imports: [VamaniportalSharedModule, RouterModule.forChild(ENTITY_STATES), PdfViewerModule],
  declarations: [
    PoliciesViewComponent,
    PoliciesUpdateComponent,
    PoliciesDeleteDialogComponent,
    PoliciesDeletePopupComponent,
    PoliciesSortComponent
  ],
  entryComponents: [
    PoliciesViewComponent,
    PoliciesUpdateComponent,
    PoliciesDeleteDialogComponent,
    PoliciesDeletePopupComponent,
    PoliciesSortComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class VamaniportalPoliciesEntryModule {}
