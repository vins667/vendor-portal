import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { VamaniportalSharedModule } from 'app/shared/shared.module';
import { debitNoteEntryRoute } from './debit-note-entry.route';
import { Ng2CompleterModule } from 'ng2-completer';
import { SnotifyModule } from 'ng-snotify';
import { DebitNoteEntryComponent } from 'app/entities/debit-note-entry/debit-note-entry.component';
import { DebitNoteItemSelectionComponent } from 'app/entities/debit-note-entry/debit-note-item-selection.component';
const ENTITY_STATES = [...debitNoteEntryRoute];

@NgModule({
  imports: [VamaniportalSharedModule, RouterModule.forChild(ENTITY_STATES), Ng2CompleterModule, SnotifyModule],
  declarations: [DebitNoteEntryComponent, DebitNoteItemSelectionComponent],
  entryComponents: [DebitNoteEntryComponent, DebitNoteItemSelectionComponent]
})
export class VamaniportalDebitNoteEntryModule {}
