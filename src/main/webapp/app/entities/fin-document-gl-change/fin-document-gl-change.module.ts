import { debitNoteEntryRoute } from '../debit-note-entry/debit-note-entry.route';
import { VamaniportalSharedModule } from '../../shared/shared.module';
import { RouterModule } from '@angular/router';
import { Ng2CompleterModule } from 'ng2-completer';
import { SnotifyModule } from 'ng-snotify';
import { FinDocumentGlChangeComponent } from './fin-document-gl-change.component';
import { NgModule } from '@angular/core';
import { finDocumentGlChangeRoute } from 'app/entities/fin-document-gl-change/fin-document-gl-change.route';

const ENTITY_STATES = [...finDocumentGlChangeRoute];
@NgModule({
  imports: [VamaniportalSharedModule, RouterModule.forChild(ENTITY_STATES), Ng2CompleterModule, SnotifyModule],
  declarations: [FinDocumentGlChangeComponent],
  entryComponents: [FinDocumentGlChangeComponent]
})
export class VamaniportalFinDocumentGlChangeComponentModule {}
