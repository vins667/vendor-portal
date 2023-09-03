import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { VamaniportalSharedModule } from 'app/shared/shared.module';
import {
  OrganizationTypeComponent,
  OrganizationTypeDetailComponent,
  OrganizationTypeUpdateComponent,
  OrganizationTypeDeletePopupComponent,
  OrganizationTypeDeleteDialogComponent,
  organizationTypeRoute,
  organizationTypePopupRoute
} from './index';

const ENTITY_STATES = [...organizationTypeRoute, ...organizationTypePopupRoute];

@NgModule({
  imports: [VamaniportalSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    OrganizationTypeComponent,
    OrganizationTypeDetailComponent,
    OrganizationTypeUpdateComponent,
    OrganizationTypeDeleteDialogComponent,
    OrganizationTypeDeletePopupComponent
  ],
  entryComponents: [
    OrganizationTypeComponent,
    OrganizationTypeUpdateComponent,
    OrganizationTypeDeleteDialogComponent,
    OrganizationTypeDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class VendorportalOrganizationTypeModule {}
