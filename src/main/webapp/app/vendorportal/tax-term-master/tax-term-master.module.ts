import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { VamaniportalSharedModule } from 'app/shared/shared.module';
import {
  TaxTermMasterComponent,
  TaxTermMasterDetailComponent,
  TaxTermMasterUpdateComponent,
  TaxTermMasterDeletePopupComponent,
  TaxTermMasterDeleteDialogComponent,
  taxTermMasterRoute,
  taxTermMasterPopupRoute
} from './';

const ENTITY_STATES = [...taxTermMasterRoute, ...taxTermMasterPopupRoute];

@NgModule({
  imports: [VamaniportalSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    TaxTermMasterComponent,
    TaxTermMasterDetailComponent,
    TaxTermMasterUpdateComponent,
    TaxTermMasterDeleteDialogComponent,
    TaxTermMasterDeletePopupComponent
  ],
  entryComponents: [
    TaxTermMasterComponent,
    TaxTermMasterUpdateComponent,
    TaxTermMasterDeleteDialogComponent,
    TaxTermMasterDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class VendorportalTaxTermMasterModule {}
