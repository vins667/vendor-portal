import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { VamaniportalSharedModule } from 'app/shared/shared.module';
import { FinPaymentAdviceComponent } from './fin-payment-advice.component';
import { finPaymentAdviceRoute } from './fin-payment-advice.route';
import { Ng2CompleterModule } from 'ng2-completer';
import { OwlDateTimeModule, OwlNativeDateTimeModule } from 'ng-pick-datetime';
import { SnotifyModule } from 'ng-snotify';
import { FinPaymentAdviceUploadComponent } from './fin-payment-advice-upload.component';

@NgModule({
  imports: [
    VamaniportalSharedModule,
    RouterModule.forChild(finPaymentAdviceRoute),
    Ng2CompleterModule,
    OwlDateTimeModule,
    OwlNativeDateTimeModule,
    SnotifyModule
  ],
  declarations: [FinPaymentAdviceComponent, FinPaymentAdviceUploadComponent],
  entryComponents: [FinPaymentAdviceUploadComponent]
})
export class VamaniportalFinPaymentAdviceModule {}
