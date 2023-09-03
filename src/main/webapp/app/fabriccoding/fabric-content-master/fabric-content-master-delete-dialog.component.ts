import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';
import { IFabricContentMaster } from 'app/shared/model/fabric-content-master.model';
import { FabricContentMasterService } from './fabric-content-master.service';

@Component({
  selector: 'jhi-fabric-content-master-delete-dialog',
  templateUrl: './fabric-content-master-delete-dialog.component.html'
})
export class FabricContentMasterDeleteDialogComponent {
  fabricContentMaster: IFabricContentMaster;

  constructor(
    protected fabricContentMasterService: FabricContentMasterService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.fabricContentMasterService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'fabricContentMasterListModification',
        content: 'Deleted an fabricContentMaster'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-fabric-content-master-delete-popup',
  template: ''
})
export class FabricContentMasterDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ fabricContentMaster }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(FabricContentMasterDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.fabricContentMaster = fabricContentMaster;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/fabric-content-master', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/fabric-content-master', { outlets: { popup: null } }]);
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
