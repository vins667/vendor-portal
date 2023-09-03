import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IVcutSessionMaster } from 'app/shared/model/vcut-session-master.model';
import { VcutSessionMasterService } from './vcut-session-master.service';

@Component({
  selector: 'jhi-vcut-session-master-delete-dialog',
  templateUrl: './vcut-session-master-delete-dialog.component.html'
})
export class VcutSessionMasterDeleteDialogComponent {
  vcutSessionMaster: IVcutSessionMaster;

  constructor(
    protected vcutSessionMasterService: VcutSessionMasterService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.vcutSessionMasterService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'vcutSessionMasterListModification',
        content: 'Deleted an vcutSessionMaster'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-vcut-session-master-delete-popup',
  template: ''
})
export class VcutSessionMasterDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ vcutSessionMaster }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(VcutSessionMasterDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.vcutSessionMaster = vcutSessionMaster;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/vcut-session-master', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/vcut-session-master', { outlets: { popup: null } }]);
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
