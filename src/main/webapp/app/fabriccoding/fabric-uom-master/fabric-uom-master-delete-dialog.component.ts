import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';
import { IFabricUomMaster } from 'app/shared/model/fabric-uom-master.model';
import { FabricUomMasterService } from './fabric-uom-master.service';

@Component({
  selector: 'jhi-fabric-uom-master-delete-dialog',
  templateUrl: './fabric-uom-master-delete-dialog.component.html'
})
export class FabricUomMasterDeleteDialogComponent {
  fabricUomMaster: IFabricUomMaster;

  constructor(
    protected fabricUomMasterService: FabricUomMasterService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.fabricUomMasterService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'fabricUomMasterListModification',
        content: 'Deleted an fabricUomMaster'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-fabric-uom-master-delete-popup',
  template: ''
})
export class FabricUomMasterDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ fabricUomMaster }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(FabricUomMasterDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.fabricUomMaster = fabricUomMaster;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/fabric-uom-master', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/fabric-uom-master', { outlets: { popup: null } }]);
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
