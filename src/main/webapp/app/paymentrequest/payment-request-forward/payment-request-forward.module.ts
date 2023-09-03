import { NgModule } from '@angular/core';
import { VamaniportalSharedModule } from 'app/shared/shared.module';
import { PaymentRequestForwardComponent } from './list/payment-request-forward.component';
import { PaymentRequestForwardUpdateComponent } from './update/payment-request-forward-update.component';
import { PaymentRequestForwardDeleteDialogComponent } from './delete/payment-request-forward-delete-dialog.component';
import { PaymentRequestForwardRoutingModule } from './route/payment-request-forward-routing.module';
import { PayReqEmployeeSearchComponent } from './update/employee-search.component';

@NgModule({
  imports: [VamaniportalSharedModule, PaymentRequestForwardRoutingModule],
  declarations: [
    PaymentRequestForwardComponent,
    PaymentRequestForwardUpdateComponent,
    PaymentRequestForwardDeleteDialogComponent,
    PayReqEmployeeSearchComponent
  ],
  entryComponents: [PaymentRequestForwardDeleteDialogComponent, PayReqEmployeeSearchComponent]
})
export class PaymentRequestForwardModule {}
