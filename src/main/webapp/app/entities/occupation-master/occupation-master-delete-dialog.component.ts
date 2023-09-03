import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';
import { IOccupationMaster } from 'app/shared/model/occupation-master.model';
import { OccupationMasterService } from './occupation-master.service';

@Component({
  selector: 'jhi-occupation-master-delete-dialog',
  templateUrl: './occupation-master-delete-dialog.component.html'
})
export class OccupationMasterDeleteDialogComponent {
  occupationMaster: IOccupationMaster;

  constructor(
    protected occupationMasterService: OccupationMasterService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.occupationMasterService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'occupationMasterListModification',
        content: 'Deleted an occupationMaster'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-occupation-master-delete-popup',
  template: ''
})
export class OccupationMasterDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ occupationMaster }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(OccupationMasterDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.occupationMaster = occupationMaster;
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
