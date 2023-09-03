import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';
import { IKnitProcessMaster } from 'app/shared/model/knit-process-master.model';
import { KnitProcessMasterService } from './knit-process-master.service';

@Component({
  selector: 'jhi-knit-process-master-delete-dialog',
  templateUrl: './knit-process-master-delete-dialog.component.html'
})
export class KnitProcessMasterDeleteDialogComponent {
  knitProcessMaster: IKnitProcessMaster;

  constructor(
    protected knitProcessMasterService: KnitProcessMasterService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.knitProcessMasterService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'knitProcessMasterListModification',
        content: 'Deleted an knitProcessMaster'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-knit-process-master-delete-popup',
  template: ''
})
export class KnitProcessMasterDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ knitProcessMaster }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(KnitProcessMasterDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.knitProcessMaster = knitProcessMaster;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/knit-process-master', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/knit-process-master', { outlets: { popup: null } }]);
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
