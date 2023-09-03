import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { VamaniportalSharedModule } from 'app/shared/shared.module';
import {
  CurrencyMasterComponent,
  CurrencyMasterDetailComponent,
  CurrencyMasterUpdateComponent,
  CurrencyMasterDeletePopupComponent,
  CurrencyMasterDeleteDialogComponent,
  currencyMasterRoute,
  currencyMasterPopupRoute
} from './';

const ENTITY_STATES = [...currencyMasterRoute, ...currencyMasterPopupRoute];

@NgModule({
  imports: [VamaniportalSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    CurrencyMasterComponent,
    CurrencyMasterDetailComponent,
    CurrencyMasterUpdateComponent,
    CurrencyMasterDeleteDialogComponent,
    CurrencyMasterDeletePopupComponent
  ],
  entryComponents: [
    CurrencyMasterComponent,
    CurrencyMasterUpdateComponent,
    CurrencyMasterDeleteDialogComponent,
    CurrencyMasterDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class VendorportalCurrencyMasterModule {}
