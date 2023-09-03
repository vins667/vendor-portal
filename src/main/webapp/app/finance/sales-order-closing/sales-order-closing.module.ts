import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { VamaniportalSharedModule } from 'app/shared/shared.module';
import { SalesOrderClosingComponent } from './sales-order-closing.component';
import { SalesOrderClosingUpdateComponent } from './sales-order-closing-update.component';
import {
  SalesOrderClosingDeletePopupComponent,
  SalesOrderClosingDeleteDialogComponent
} from './sales-order-closing-delete-dialog.component';
import { salesOrderClosingRoute } from 'app/finance/sales-order-closing/sales-order-closing.route';
import { SnotifyModule } from 'ng-snotify';

@NgModule({
  imports: [VamaniportalSharedModule, RouterModule.forChild(salesOrderClosingRoute), SnotifyModule],
  declarations: [
    SalesOrderClosingComponent,
    SalesOrderClosingUpdateComponent,
    SalesOrderClosingDeleteDialogComponent,
    SalesOrderClosingDeletePopupComponent
  ],
  entryComponents: [SalesOrderClosingDeleteDialogComponent]
})
export class VamaniportalSalesOrderClosingModule {}
