import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { VamaniportalSharedModule } from 'app/shared/shared.module';
import {
  TransactionNatureComponent,
  TransactionNatureDetailComponent,
  TransactionNatureUpdateComponent,
  TransactionNatureDeletePopupComponent,
  TransactionNatureDeleteDialogComponent,
  transactionNatureRoute,
  transactionNaturePopupRoute
} from './index';

const ENTITY_STATES = [...transactionNatureRoute, ...transactionNaturePopupRoute];

@NgModule({
  imports: [VamaniportalSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    TransactionNatureComponent,
    TransactionNatureDetailComponent,
    TransactionNatureUpdateComponent,
    TransactionNatureDeleteDialogComponent,
    TransactionNatureDeletePopupComponent
  ],
  entryComponents: [
    TransactionNatureComponent,
    TransactionNatureUpdateComponent,
    TransactionNatureDeleteDialogComponent,
    TransactionNatureDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class VendorportalTransactionNatureModule {}
