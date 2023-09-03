import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { CutBundleLockService } from './cut-bundle-lock.service';
import { ICutBundleLockId } from 'app/shared/model/cut-bundle-lock-id.model';
import { ICutBundleLock } from 'app/shared/model/cut-bundle-lock.model';

@Component({
  templateUrl: './cut-bundle-lock-delete-dialog.component.html'
})
export class CutBundleLockDeleteDialogComponent {
  cutBundleLock?: ICutBundleLock;

  constructor(
    protected cutBundleLockService: CutBundleLockService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: ICutBundleLockId): void {
    this.cutBundleLockService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'cutBundleLockListModification',
        content: 'Deleted an cutBundleLock'
      });
      this.activeModal.close();
    });
  }
}
