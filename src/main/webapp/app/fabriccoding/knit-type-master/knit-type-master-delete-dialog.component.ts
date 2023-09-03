import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';
import { IKnitTypeMaster } from 'app/shared/model/knit-type-master.model';
import { KnitTypeMasterService } from './knit-type-master.service';

@Component({
  selector: 'jhi-knit-type-master-delete-dialog',
  templateUrl: './knit-type-master-delete-dialog.component.html'
})
export class KnitTypeMasterDeleteDialogComponent {
  knitTypeMaster: IKnitTypeMaster;

  constructor(
    protected knitTypeMasterService: KnitTypeMasterService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.knitTypeMasterService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'knitTypeMasterListModification',
        content: 'Deleted an knitTypeMaster'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-knit-type-master-delete-popup',
  template: ''
})
export class KnitTypeMasterDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ knitTypeMaster }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(KnitTypeMasterDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.knitTypeMaster = knitTypeMaster;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/knit-type-master', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/knit-type-master', { outlets: { popup: null } }]);
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
