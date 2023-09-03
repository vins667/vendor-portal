import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { VamaniportalSharedModule } from 'app/shared/shared.module';
import {
  VendorsComponent,
  TabsComponent,
  VendorContactUpdateComponent,
  VendorBankDetailsUpdateComponent,
  VendorBranchDetailsComponent,
  VendorNominationUpdateComponent,
  VendorAdditionalInfoUpdateComponent,
  VendorDocumentUpdateComponent,
  VendorUsersUpdateComponent,
  VendorTermsUpdateComponent,
  VendorWorkflowComponent,
  vendorsRoute,
  vendorsPopupRoute
} from './index';
import { VendorBranchDetailsUpdateComponent } from 'app/vendorportal/vendor-branch-details';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { SnotifyModule, SnotifyService, ToastDefaults } from 'ng-snotify';
import { DualListBoxModule } from 'ng2-dual-list-box';

const ENTITY_STATES = [...vendorsRoute, ...vendorsPopupRoute];

@NgModule({
  imports: [
    VamaniportalSharedModule,
    RouterModule.forChild(ENTITY_STATES),
    FormsModule,
    ReactiveFormsModule,
    SnotifyModule,
    DualListBoxModule.forRoot()
  ],
  declarations: [
    VendorsComponent,
    TabsComponent,
    VendorContactUpdateComponent,
    VendorBankDetailsUpdateComponent,
    VendorBranchDetailsComponent,
    VendorNominationUpdateComponent,
    VendorAdditionalInfoUpdateComponent,
    VendorDocumentUpdateComponent,
    VendorUsersUpdateComponent,
    VendorTermsUpdateComponent,
    VendorBranchDetailsUpdateComponent,
    VendorWorkflowComponent
  ],
  entryComponents: [
    VendorsComponent,
    VendorContactUpdateComponent,
    VendorBankDetailsUpdateComponent,
    VendorBranchDetailsComponent,
    VendorNominationUpdateComponent,
    VendorAdditionalInfoUpdateComponent,
    VendorDocumentUpdateComponent,
    VendorUsersUpdateComponent,
    VendorTermsUpdateComponent,
    VendorBranchDetailsUpdateComponent,
    VendorWorkflowComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
  providers: [{ provide: 'SnotifyToastConfig', useValue: ToastDefaults }, SnotifyService]
})
export class VamaniportalVendorsModule {}
