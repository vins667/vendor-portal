import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { VamaniportalSharedModule } from 'app/shared/shared.module';
import {
  VendorBranchDetailsDetailComponent,
  VendorBranchDetailsUpdateComponent,
  VendorBranchDetailsDeletePopupComponent,
  VendorBranchDetailsDeleteDialogComponent,
  vendorBranchDetailsRoute,
  vendorBranchDetailsPopupRoute
} from './index';

const ENTITY_STATES = [...vendorBranchDetailsRoute, ...vendorBranchDetailsPopupRoute];

@NgModule({
  imports: [VamaniportalSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [VendorBranchDetailsDetailComponent, VendorBranchDetailsDeleteDialogComponent, VendorBranchDetailsDeletePopupComponent],
  entryComponents: [VendorBranchDetailsDeleteDialogComponent, VendorBranchDetailsDeletePopupComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class VendorportalVendorBranchDetailsModule {}
