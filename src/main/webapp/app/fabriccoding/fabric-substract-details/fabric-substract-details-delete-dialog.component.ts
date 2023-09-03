import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';
import { IFabricSubstractDetails } from 'app/shared/model/fabric-substract-details.model';
import { FabricSubstractDetailsService } from './fabric-substract-details.service';

@Component({
  selector: 'jhi-fabric-substract-details-delete-dialog',
  templateUrl: './fabric-substract-details-delete-dialog.component.html'
})
export class FabricSubstractDetailsDeleteDialogComponent {
  fabricSubstractDetails: IFabricSubstractDetails;

  constructor(
    protected fabricSubstractDetailsService: FabricSubstractDetailsService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.fabricSubstractDetailsService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'fabricSubstractDetailsListModification',
        content: 'Deleted an fabricSubstractDetails'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-fabric-substract-details-delete-popup',
  template: ''
})
export class FabricSubstractDetailsDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ fabricSubstractDetails }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(FabricSubstractDetailsDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.fabricSubstractDetails = fabricSubstractDetails;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/fabric-substract-details', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/fabric-substract-details', { outlets: { popup: null } }]);
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
