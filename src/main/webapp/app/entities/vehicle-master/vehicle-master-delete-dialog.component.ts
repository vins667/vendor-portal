import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';
import { IVehicleMaster } from 'app/shared/model/vehicle-master.model';
import { VehicleMasterService } from './vehicle-master.service';

@Component({
  selector: 'jhi-vehicle-master-delete-dialog',
  templateUrl: './vehicle-master-delete-dialog.component.html'
})
export class VehicleMasterDeleteDialogComponent {
  vehicleMaster: IVehicleMaster;

  constructor(
    protected vehicleMasterService: VehicleMasterService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.vehicleMasterService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'vehicleMasterListModification',
        content: 'Deleted an vehicleMaster'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-vehicle-master-delete-popup',
  template: ''
})
export class VehicleMasterDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ vehicleMaster }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(VehicleMasterDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.vehicleMaster = vehicleMaster;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
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
