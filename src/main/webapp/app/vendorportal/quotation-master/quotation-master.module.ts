import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { VamaniportalSharedModule } from 'app/shared/shared.module';
import {
  QuotationMasterComponent,
  QuotationMasterDetailComponent,
  QuotationMasterUpdateComponent,
  QuotationMasterDeletePopupComponent,
  QuotationMasterDeleteDialogComponent,
  quotationMasterRoute,
  quotationMasterPopupRoute
} from './';

const ENTITY_STATES = [...quotationMasterRoute, ...quotationMasterPopupRoute];

@NgModule({
  imports: [VamaniportalSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    QuotationMasterComponent,
    QuotationMasterDetailComponent,
    QuotationMasterUpdateComponent,
    QuotationMasterDeleteDialogComponent,
    QuotationMasterDeletePopupComponent
  ],
  entryComponents: [
    QuotationMasterComponent,
    QuotationMasterUpdateComponent,
    QuotationMasterDeleteDialogComponent,
    QuotationMasterDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class VendorportalQuotationMasterModule {}
