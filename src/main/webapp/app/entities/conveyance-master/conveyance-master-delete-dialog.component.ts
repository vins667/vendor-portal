import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';
import { IConveyanceMaster } from 'app/shared/model/conveyance-master.model';
import { ConveyanceMasterService } from './conveyance-master.service';

@Component({
  selector: 'jhi-conveyance-master-delete-dialog',
  templateUrl: './conveyance-master-delete-dialog.component.html'
})
export class ConveyanceMasterDeleteDialogComponent {
  conveyanceMaster: IConveyanceMaster;

  constructor(
    protected conveyanceMasterService: ConveyanceMasterService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.conveyanceMasterService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'conveyanceMasterListModification',
        content: 'Deleted an conveyanceMaster'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-conveyance-master-delete-popup',
  template: ''
})
export class ConveyanceMasterDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ conveyanceMaster }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(ConveyanceMasterDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.conveyanceMaster = conveyanceMaster;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/conveyance-master', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/conveyance-master', { outlets: { popup: null } }]);
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
