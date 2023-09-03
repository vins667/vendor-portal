import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { VamaniportalSharedModule } from 'app/shared/shared.module';
import { BillRegisterComponent } from './bill-register.component';
import { BillRegisterUpdateComponent } from './bill-register-update.component';
import { BillRegisterDeleteDialogComponent } from './bill-register-delete-dialog.component';
import { billRegisterRoute } from './bill-register.route';
import { Ng2CompleterModule } from 'ng2-completer';
import { OwlDateTimeModule, OwlNativeDateTimeModule } from 'ng-pick-datetime';
import { SnotifyModule } from 'ng-snotify';
import { PlantInvoiceSelectionComponent } from './plant-invoice-selection.component';

@NgModule({
  imports: [
    VamaniportalSharedModule,
    RouterModule.forChild(billRegisterRoute),
    Ng2CompleterModule,
    OwlDateTimeModule,
    OwlNativeDateTimeModule,
    SnotifyModule
  ],
  declarations: [BillRegisterComponent, BillRegisterUpdateComponent, BillRegisterDeleteDialogComponent, PlantInvoiceSelectionComponent],
  entryComponents: [BillRegisterDeleteDialogComponent, PlantInvoiceSelectionComponent]
})
export class VamaniportalBillRegisterModule {}
