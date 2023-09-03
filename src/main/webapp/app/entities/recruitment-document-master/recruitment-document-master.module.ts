import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { VamaniportalSharedModule } from 'app/shared/shared.module';
import {
  RecruitmentDocumentMasterComponent,
  RecruitmentDocumentMasterDetailComponent,
  RecruitmentDocumentMasterUpdateComponent,
  RecruitmentDocumentMasterDeletePopupComponent,
  RecruitmentDocumentMasterDeleteDialogComponent,
  recruitmentDocumentMasterRoute,
  recruitmentDocumentMasterPopupRoute
} from './';

const ENTITY_STATES = [...recruitmentDocumentMasterRoute, ...recruitmentDocumentMasterPopupRoute];

@NgModule({
  imports: [VamaniportalSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    RecruitmentDocumentMasterComponent,
    RecruitmentDocumentMasterDetailComponent,
    RecruitmentDocumentMasterUpdateComponent,
    RecruitmentDocumentMasterDeleteDialogComponent,
    RecruitmentDocumentMasterDeletePopupComponent
  ],
  entryComponents: [
    RecruitmentDocumentMasterComponent,
    RecruitmentDocumentMasterUpdateComponent,
    RecruitmentDocumentMasterDeleteDialogComponent,
    RecruitmentDocumentMasterDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class VamaniportalRecruitmentDocumentMasterModule {}
