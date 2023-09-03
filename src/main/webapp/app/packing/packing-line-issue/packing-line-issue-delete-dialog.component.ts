import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICutPlanIssueStitch } from 'app/shared/model/cut-plan-issue-stitch.model';
import { PackingLineIssueService } from './packing-line-issue.service';

@Component({
  templateUrl: './packing-line-issue-delete-dialog.component.html'
})
export class PackingLineIssueDeleteDialogComponent {
  cutPlanIssueStitch?: ICutPlanIssueStitch;

  constructor(
    protected cutPlanIssueStitchService: PackingLineIssueService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.cutPlanIssueStitchService.delete(id).subscribe(() => {
      this.eventManager.broadcast('cutPlanIssueStitchListModification');
      this.activeModal.close();
    });
  }
}
