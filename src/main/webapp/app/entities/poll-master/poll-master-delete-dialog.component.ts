import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';
import { IPollMaster } from 'app/shared/model/poll-master.model';
import { PollMasterService } from './poll-master.service';

@Component({
  selector: 'jhi-poll-master-delete-dialog',
  templateUrl: './poll-master-delete-dialog.component.html'
})
export class PollMasterDeleteDialogComponent {
  pollMaster: IPollMaster;

  constructor(
    protected pollMasterService: PollMasterService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.pollMasterService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'pollMasterListModification',
        content: 'Deleted an pollMaster'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-poll-master-delete-popup',
  template: ''
})
export class PollMasterDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ pollMaster }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(PollMasterDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.pollMaster = pollMaster;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
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
