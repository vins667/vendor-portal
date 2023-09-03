import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICutPlanIssueStitch } from 'app/shared/model/cut-plan-issue-stitch.model';
import { StitchLineIssueService } from './stitch-line-issue.service';

@Component({
  templateUrl: './stitch-line-issue-delete-dialog.component.html'
})
export class StitchLineIssueDeleteDialogComponent {
  cutPlanIssueStitch?: ICutPlanIssueStitch;

  constructor(
    protected cutPlanIssueStitchService: StitchLineIssueService,
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
