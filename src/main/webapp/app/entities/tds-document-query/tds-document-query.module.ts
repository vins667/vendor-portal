import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { TdsDocumentQueryComponent, tdsDeclarationUploadQryRoute } from './';
import { VamaniportalSharedModule } from 'app/shared/shared.module';
import { TdsDocumentQueryUpdateComponent } from './tds-document-query-update.component';
import { SnotifyModule } from 'ng-snotify';

const ENTITY_STATES = [...tdsDeclarationUploadQryRoute];

@NgModule({
  imports: [VamaniportalSharedModule, RouterModule.forChild(ENTITY_STATES), SnotifyModule],
  declarations: [TdsDocumentQueryComponent, TdsDocumentQueryUpdateComponent],
  entryComponents: [TdsDocumentQueryComponent, TdsDocumentQueryUpdateComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class VamaniportalTdsDocumentQueryModule {}
