import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { VamaniportalSharedModule } from 'app/shared/shared.module';
import { Form16Component, Form16Route, Form16PopupRoute } from './';
import { PdfViewerModule } from 'ng2-pdf-viewer';
import { SnotifyModule } from 'ng-snotify';

const ENTITY_STATES = [...Form16Route, ...Form16PopupRoute];

@NgModule({
  imports: [VamaniportalSharedModule, RouterModule.forChild(ENTITY_STATES), PdfViewerModule, SnotifyModule],
  declarations: [Form16Component],
  entryComponents: [Form16Component],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class VamaniportalForm16Module {}
