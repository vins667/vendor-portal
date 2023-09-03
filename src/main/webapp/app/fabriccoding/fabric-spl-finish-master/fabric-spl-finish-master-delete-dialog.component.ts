import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';
import { IFabricSplFinishMaster } from 'app/shared/model/fabric-spl-finish-master.model';
import { FabricSplFinishMasterService } from './fabric-spl-finish-master.service';

@Component({
  selector: 'jhi-fabric-spl-finish-master-delete-dialog',
  templateUrl: './fabric-spl-finish-master-delete-dialog.component.html'
})
export class FabricSplFinishMasterDeleteDialogComponent {
  fabricSplFinishMaster: IFabricSplFinishMaster;

  constructor(
    protected fabricSplFinishMasterService: FabricSplFinishMasterService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.fabricSplFinishMasterService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'fabricSplFinishMasterListModification',
        content: 'Deleted an fabricSplFinishMaster'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-fabric-spl-finish-master-delete-popup',
  template: ''
})
export class FabricSplFinishMasterDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ fabricSplFinishMaster }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(FabricSplFinishMasterDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.fabricSplFinishMaster = fabricSplFinishMaster;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/fabric-spl-finish-master', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/fabric-spl-finish-master', { outlets: { popup: null } }]);
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
