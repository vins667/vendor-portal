import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { VamaniportalSharedModule } from 'app/shared/shared.module';
import {
  ReportTypeMasterComponent,
  ReportTypeMasterDetailComponent,
  ReportTypeMasterUpdateComponent,
  ReportTypeMasterDeletePopupComponent,
  ReportTypeMasterDeleteDialogComponent,
  reportTypeMasterRoute,
  reportTypeMasterPopupRoute
} from './';

const ENTITY_STATES = [...reportTypeMasterRoute, ...reportTypeMasterPopupRoute];

@NgModule({
  imports: [VamaniportalSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    ReportTypeMasterComponent,
    ReportTypeMasterDetailComponent,
    ReportTypeMasterUpdateComponent,
    ReportTypeMasterDeleteDialogComponent,
    ReportTypeMasterDeletePopupComponent
  ],
  entryComponents: [
    ReportTypeMasterComponent,
    ReportTypeMasterUpdateComponent,
    ReportTypeMasterDeleteDialogComponent,
    ReportTypeMasterDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class VamaniportalReportTypeMasterModule {}
