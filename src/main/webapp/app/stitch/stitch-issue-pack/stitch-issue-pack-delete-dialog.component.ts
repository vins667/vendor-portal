import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IStitchIssuePack } from 'app/shared/model/stitch-issue-pack.model';
import { StitchIssuePackService } from './stitch-issue-pack.service';

@Component({
  templateUrl: './stitch-issue-pack-delete-dialog.component.html'
})
export class StitchIssuePackDeleteDialogComponent {
  stitchIssuePack?: IStitchIssuePack;

  constructor(
    protected stitchIssuePackService: StitchIssuePackService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.stitchIssuePackService.delete(id).subscribe(() => {
      this.eventManager.broadcast('stitchIssuePackListModification');
      this.activeModal.close();
    });
  }
}
