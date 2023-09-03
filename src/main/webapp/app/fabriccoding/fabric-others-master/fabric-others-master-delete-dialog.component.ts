import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';
import { IFabricOthersMaster } from 'app/shared/model/fabric-others-master.model';
import { FabricOthersMasterService } from './fabric-others-master.service';

@Component({
  selector: 'jhi-fabric-others-master-delete-dialog',
  templateUrl: './fabric-others-master-delete-dialog.component.html'
})
export class FabricOthersMasterDeleteDialogComponent {
  fabricOthersMaster: IFabricOthersMaster;

  constructor(
    protected fabricOthersMasterService: FabricOthersMasterService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.fabricOthersMasterService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'fabricOthersMasterListModification',
        content: 'Deleted an fabricOthersMaster'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-fabric-others-master-delete-popup',
  template: ''
})
export class FabricOthersMasterDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ fabricOthersMaster }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(FabricOthersMasterDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.fabricOthersMaster = fabricOthersMaster;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/fabric-others-master', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/fabric-others-master', { outlets: { popup: null } }]);
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
