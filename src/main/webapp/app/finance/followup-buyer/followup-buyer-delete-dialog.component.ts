import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IFollowupBuyer } from './followup-buyer.model';
import { FollowupBuyerService } from './followup-buyer.service';

@Component({
  selector: 'jhi-followup-buyer-delete-dialog',
  templateUrl: './followup-buyer-delete-dialog.component.html'
})
export class FollowupBuyerDeleteDialogComponent {
  followupBuyer: IFollowupBuyer;

  constructor(
    protected followupBuyerService: FollowupBuyerService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.followupBuyerService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'followupBuyerListModification',
        content: 'Deleted an followupBuyer'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-followup-buyer-delete-popup',
  template: ''
})
export class FollowupBuyerDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ followupBuyer }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(FollowupBuyerDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.followupBuyer = followupBuyer;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/followup-buyers', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/followup-buyers', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          }
        );
      }, 0);
    });
  }

  ngOnDestroy() {
    this.ngbModalRef = null;
  }
}
