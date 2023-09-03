import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { VamaniportalSharedModule } from 'app/shared/shared.module';
import { bankReconciliationRoute } from './bank-reconciliation.route';
import { Ng2CompleterModule } from 'ng2-completer';
import { SnotifyModule } from 'ng-snotify';
import { BankReconciliationComponent } from 'app/entities/bank-reconciliation/bank-reconciliation.component';
import { OwlDateTimeModule, OwlNativeDateTimeModule } from 'ng-pick-datetime';
import { TextMaskModule } from 'angular2-text-mask';
const ENTITY_STATES = [...bankReconciliationRoute];

@NgModule({
  imports: [
    VamaniportalSharedModule,
    RouterModule.forChild(ENTITY_STATES),
    Ng2CompleterModule,
    OwlDateTimeModule,
    OwlNativeDateTimeModule,
    SnotifyModule,
    TextMaskModule
  ],
  declarations: [BankReconciliationComponent],
  entryComponents: []
})
export class VamaniportalBankReconciliationModule {}
