import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { VamaniportalSharedModule } from 'app/shared/shared.module';
import {
  BankMasterComponent,
  BankMasterDetailComponent,
  BankMasterUpdateComponent,
  BankMasterDeletePopupComponent,
  BankMasterDeleteDialogComponent,
  bankMasterRoute,
  bankMasterPopupRoute
} from './';

const ENTITY_STATES = [...bankMasterRoute, ...bankMasterPopupRoute];

@NgModule({
  imports: [VamaniportalSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    BankMasterComponent,
    BankMasterDetailComponent,
    BankMasterUpdateComponent,
    BankMasterDeleteDialogComponent,
    BankMasterDeletePopupComponent
  ],
  entryComponents: [BankMasterComponent, BankMasterUpdateComponent, BankMasterDeleteDialogComponent, BankMasterDeletePopupComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class VamaniportalBankMasterModule {}
