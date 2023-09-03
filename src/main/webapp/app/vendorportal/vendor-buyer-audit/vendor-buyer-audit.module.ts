import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { VamaniportalSharedModule } from 'app/shared/shared.module';
import { VendorBuyerAuditComponent } from './vendor-buyer-audit.component';
import { VendorBuyerAuditDetailComponent } from './vendor-buyer-audit-detail.component';
import { VendorBuyerAuditUpdateComponent } from './vendor-buyer-audit-update.component';
import { VendorBuyerAuditDeletePopupComponent, VendorBuyerAuditDeleteDialogComponent } from './vendor-buyer-audit-delete-dialog.component';
import { vendorBuyerAuditRoute, vendorBuyerAuditPopupRoute } from './vendor-buyer-audit.route';
import { VendorBuyerMasterSearchComponent } from './vendor-buyer-master-search.component';
import { MatTableModule } from '@angular/material/table';
import { MatInputModule } from '@angular/material/input';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatSortModule } from '@angular/material/sort';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatTabsModule } from '@angular/material';
import { CdkTableModule } from '@angular/cdk/table';

const ENTITY_STATES = [...vendorBuyerAuditRoute, ...vendorBuyerAuditPopupRoute];

@NgModule({
  imports: [
    VamaniportalSharedModule,
    RouterModule.forChild(ENTITY_STATES),
    MatInputModule,
    MatTableModule,
    MatPaginatorModule,
    MatSortModule,
    MatProgressSpinnerModule,
    MatTabsModule,
    CdkTableModule
  ],
  declarations: [
    VendorBuyerAuditComponent,
    VendorBuyerAuditDetailComponent,
    VendorBuyerAuditUpdateComponent,
    VendorBuyerAuditDeleteDialogComponent,
    VendorBuyerAuditDeletePopupComponent,
    VendorBuyerMasterSearchComponent
  ],
  entryComponents: [
    VendorBuyerAuditComponent,
    VendorBuyerAuditUpdateComponent,
    VendorBuyerAuditDeleteDialogComponent,
    VendorBuyerAuditDeletePopupComponent,
    VendorBuyerMasterSearchComponent
  ]
})
export class VamaniportalVendorBuyerAuditModule {}
