import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { TdsDeclarationUploadQryComponent, tdsDeclarationUploadQryRoute } from './';
import { VamaniportalSharedModule } from 'app/shared/shared.module';
import { TdsDeclarationUploadQryUpdateComponent } from './tds-declaration-upload-qry-update.component';

const ENTITY_STATES = [...tdsDeclarationUploadQryRoute];

@NgModule({
  imports: [VamaniportalSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [TdsDeclarationUploadQryComponent, TdsDeclarationUploadQryUpdateComponent],
  entryComponents: [TdsDeclarationUploadQryComponent, TdsDeclarationUploadQryUpdateComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class VamaniportalTdsDeclarationUploadQryModule {}
