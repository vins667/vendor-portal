import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IDirectBookingEntry } from 'app/shared/model/direct-booking-entry.model';
import { DirectBookingEntryService } from './direct-booking-entry.service';

@Component({
  templateUrl: './direct-booking-entry-delete-dialog.component.html'
})
export class DirectBookingEntryDeleteDialogComponent {
  directBookingEntry?: IDirectBookingEntry;

  constructor(
    protected directBookingEntryService: DirectBookingEntryService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.directBookingEntryService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'directBookingEntryListModification',
        content: 'Deleted an assetMaster'
      });
      this.activeModal.close();
    });
  }
}
