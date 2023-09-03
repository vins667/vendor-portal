import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';
import { IKnitCreationMaster } from 'app/shared/model/knit-creation-master.model';
import { KnitCreationMasterService } from './knit-creation-master.service';

@Component({
  selector: 'jhi-knit-creation-master-delete-dialog',
  templateUrl: './knit-creation-master-delete-dialog.component.html'
})
export class KnitCreationMasterDeleteDialogComponent {
  knitCreationMaster: IKnitCreationMaster;

  constructor(
    protected knitCreationMasterService: KnitCreationMasterService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.knitCreationMasterService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'knitCreationMasterListModification',
        content: 'Deleted an knitCreationMaster'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-knit-creation-master-delete-popup',
  template: ''
})
export class KnitCreationMasterDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ knitCreationMaster }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(KnitCreationMasterDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.knitCreationMaster = knitCreationMaster;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/knit-creation-master', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/knit-creation-master', { outlets: { popup: null } }]);
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
