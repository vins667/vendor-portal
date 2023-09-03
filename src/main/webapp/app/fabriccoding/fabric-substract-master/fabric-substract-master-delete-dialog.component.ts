import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';
import { IFabricSubstractMaster } from 'app/shared/model/fabric-substract-master.model';
import { FabricSubstractMasterService } from './fabric-substract-master.service';

@Component({
  selector: 'jhi-fabric-substract-master-delete-dialog',
  templateUrl: './fabric-substract-master-delete-dialog.component.html'
})
export class FabricSubstractMasterDeleteDialogComponent {
  fabricSubstractMaster: IFabricSubstractMaster;

  constructor(
    protected fabricSubstractMasterService: FabricSubstractMasterService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.fabricSubstractMasterService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'fabricSubstractMasterListModification',
        content: 'Deleted an fabricSubstractMaster'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-fabric-substract-master-delete-popup',
  template: ''
})
export class FabricSubstractMasterDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ fabricSubstractMaster }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(FabricSubstractMasterDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.fabricSubstractMaster = fabricSubstractMaster;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/fabric-substract-master', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/fabric-substract-master', { outlets: { popup: null } }]);
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
