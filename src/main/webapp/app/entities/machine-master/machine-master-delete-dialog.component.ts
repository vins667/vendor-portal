import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';
import { IMachineMaster } from 'app/shared/model/machine-master.model';
import { MachineMasterService } from './machine-master.service';

@Component({
  selector: 'jhi-machine-master-delete-dialog',
  templateUrl: './machine-master-delete-dialog.component.html'
})
export class MachineMasterDeleteDialogComponent {
  machineMaster: IMachineMaster;

  constructor(
    protected machineMasterService: MachineMasterService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.machineMasterService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'machineMasterListModification',
        content: 'Deleted an machineMaster'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-machine-master-delete-popup',
  template: ''
})
export class MachineMasterDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ machineMaster }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MachineMasterDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.machineMaster = machineMaster;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/machine-master', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/machine-master', { outlets: { popup: null } }]);
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
