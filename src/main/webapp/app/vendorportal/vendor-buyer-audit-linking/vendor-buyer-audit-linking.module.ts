import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { VamaniportalSharedModule } from 'app/shared/shared.module';
import { DualListBoxModule } from 'ng2-dual-list-box';
import {
  VendorBuyerAuditLinkingComponent,
  VendorBuyerAuditLinkingDetailComponent,
  VendorBuyerAuditLinkingUpdateComponent,
  VendorBuyerAuditLinkingDeletePopupComponent,
  VendorBuyerAuditLinkingDeleteDialogComponent,
  VendorMasterSearchComponent,
  vendorBuyerAuditLinkingRoute,
  vendorBuyerAuditLinkingPopupRoute
} from './';
import { ReactiveFormsModule } from '@angular/forms';

const ENTITY_STATES = [...vendorBuyerAuditLinkingRoute, ...vendorBuyerAuditLinkingPopupRoute];

@NgModule({
  imports: [VamaniportalSharedModule, ReactiveFormsModule, RouterModule.forChild(ENTITY_STATES), DualListBoxModule.forRoot()],
  declarations: [
    VendorBuyerAuditLinkingComponent,
    VendorBuyerAuditLinkingDetailComponent,
    VendorBuyerAuditLinkingUpdateComponent,
    VendorBuyerAuditLinkingDeleteDialogComponent,
    VendorBuyerAuditLinkingDeletePopupComponent,
    VendorMasterSearchComponent
  ],
  entryComponents: [
    VendorBuyerAuditLinkingComponent,
    VendorBuyerAuditLinkingUpdateComponent,
    VendorBuyerAuditLinkingDeleteDialogComponent,
    VendorBuyerAuditLinkingDeletePopupComponent,
    VendorMasterSearchComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class VamaniportalVendorBuyerAuditLinkingModule {}
