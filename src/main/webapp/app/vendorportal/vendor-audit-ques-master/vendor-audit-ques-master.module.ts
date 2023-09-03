import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { VamaniportalSharedModule } from 'app/shared/shared.module';
import {
    VendorAuditQuesMasterComponent,
    VendorAuditQuesMasterDetailComponent,
    VendorAuditQuesMasterUpdateComponent,
    VendorAuditQuesMasterDeletePopupComponent,
    VendorAuditQuesMasterDeleteDialogComponent,
    vendorAuditQuesMasterRoute,
    vendorAuditQuesMasterPopupRoute
} from './';

const ENTITY_STATES = [...vendorAuditQuesMasterRoute, ...vendorAuditQuesMasterPopupRoute];

@NgModule({
    imports: [VamaniportalSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        VendorAuditQuesMasterComponent,
        VendorAuditQuesMasterDetailComponent,
        VendorAuditQuesMasterUpdateComponent,
        VendorAuditQuesMasterDeleteDialogComponent,
        VendorAuditQuesMasterDeletePopupComponent
    ],
    entryComponents: [
        VendorAuditQuesMasterComponent,
        VendorAuditQuesMasterUpdateComponent,
        VendorAuditQuesMasterDeleteDialogComponent,
        VendorAuditQuesMasterDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class VamaniportalVendorAuditQuesMasterModule {}
