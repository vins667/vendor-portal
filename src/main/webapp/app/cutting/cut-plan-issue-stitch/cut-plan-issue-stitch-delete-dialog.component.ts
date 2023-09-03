import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICutPlanIssueStitch } from 'app/shared/model/cut-plan-issue-stitch.model';
import { CutPlanIssueStitchService } from './cut-plan-issue-stitch.service';

@Component({
  templateUrl: './cut-plan-issue-stitch-delete-dialog.component.html'
})
export class CutPlanIssueStitchDeleteDialogComponent {
  cutPlanIssueStitch?: ICutPlanIssueStitch;

  constructor(
    protected cutPlanIssueStitchService: CutPlanIssueStitchService,
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
