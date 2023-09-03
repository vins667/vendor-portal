import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { VamaniportalSharedModule } from 'app/shared/shared.module';
import { PoDatatexApprovalComponent } from './po-datatex-approval.component';
import { PoDatatexApprovalDetailComponent } from './po-datatex-approval-detail.component';
import { PoDatatexApprovalUpdateComponent } from './po-datatex-approval-update.component';

import { poDatatexApprovalRoute } from './po-datatex-approval.route';

const ENTITY_STATES = [...poDatatexApprovalRoute];

@NgModule({
  imports: [VamaniportalSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [PoDatatexApprovalComponent, PoDatatexApprovalDetailComponent, PoDatatexApprovalUpdateComponent],
  entryComponents: [PoDatatexApprovalComponent, PoDatatexApprovalUpdateComponent]
})
export class VamaniportalPoDatatexApprovalModule {}
