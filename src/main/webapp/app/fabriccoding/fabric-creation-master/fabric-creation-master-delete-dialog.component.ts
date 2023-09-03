import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';
import { IFabricCreationMaster } from 'app/shared/model/fabric-creation-master.model';
import { FabricCreationMasterService } from './fabric-creation-master.service';

@Component({
  selector: 'jhi-fabric-creation-master-delete-dialog',
  templateUrl: './fabric-creation-master-delete-dialog.component.html'
})
export class FabricCreationMasterDeleteDialogComponent {
  fabricCreationMaster: IFabricCreationMaster;

  constructor(
    protected fabricCreationMasterService: FabricCreationMasterService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.fabricCreationMasterService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'fabricCreationMasterListModification',
        content: 'Deleted an fabricCreationMaster'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-fabric-creation-master-delete-popup',
  template: ''
})
export class FabricCreationMasterDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ fabricCreationMaster }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(FabricCreationMasterDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.fabricCreationMaster = fabricCreationMaster;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/fabric-creation-master', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/fabric-creation-master', { outlets: { popup: null } }]);
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
