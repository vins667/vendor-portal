import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import {
  AuditGroupMasterComponent,
  AuditGroupMasterDetailComponent,
  AuditGroupMasterUpdateComponent,
  AuditGroupMasterDeletePopupComponent,
  AuditGroupMasterDeleteDialogComponent,
  auditGroupMasterRoute,
  auditGroupMasterPopupRoute
} from './';
import {VamaniportalSharedModule} from 'app/shared/shared.module';

const ENTITY_STATES = [...auditGroupMasterRoute, ...auditGroupMasterPopupRoute];

@NgModule({
  imports: [VamaniportalSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    AuditGroupMasterComponent,
    AuditGroupMasterDetailComponent,
    AuditGroupMasterUpdateComponent,
    AuditGroupMasterDeleteDialogComponent,
    AuditGroupMasterDeletePopupComponent
  ],
  entryComponents: [
    AuditGroupMasterComponent,
    AuditGroupMasterUpdateComponent,
    AuditGroupMasterDeleteDialogComponent,
    AuditGroupMasterDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class VamaniportalAuditGroupMasterModule {}
