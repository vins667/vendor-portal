import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { VamaniportalSharedModule } from 'app/shared/shared.module';
import { BillRegisterImportComponent } from './bill-register-import.component';
import { BillRegisterImportUpdateComponent } from './bill-register-import-update.component';
import { BillRegisterImportDeleteDialogComponent } from './bill-register-import-delete-dialog.component';
import { billRegisterRoute } from './bill-register-import.route';
import { Ng2CompleterModule } from 'ng2-completer';
import { OwlDateTimeModule, OwlNativeDateTimeModule } from 'ng-pick-datetime';
import { SnotifyModule } from 'ng-snotify';
import { PlantInvoiceSelectionComponent } from './plant-invoice-selection.component';
import { AngularMultiSelectModule } from 'angular2-multiselect-dropdown';
@NgModule({
  imports: [
    VamaniportalSharedModule,
    RouterModule.forChild(billRegisterRoute),
    Ng2CompleterModule,
    OwlDateTimeModule,
    OwlNativeDateTimeModule,
    SnotifyModule,
    AngularMultiSelectModule
  ],
  declarations: [
    BillRegisterImportComponent,
    BillRegisterImportUpdateComponent,
    BillRegisterImportDeleteDialogComponent,
    PlantInvoiceSelectionComponent
  ],
  entryComponents: [BillRegisterImportDeleteDialogComponent, PlantInvoiceSelectionComponent]
})
export class VamaniportalBillRegisterImportModule {}
