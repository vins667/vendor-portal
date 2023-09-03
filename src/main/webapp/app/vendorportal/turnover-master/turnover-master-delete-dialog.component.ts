import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';
import { ITurnoverMaster } from 'app/shared/model/turnover-master.model';
import { TurnoverMasterService } from './turnover-master.service';

@Component({
  selector: 'jhi-turnover-master-delete-dialog',
  templateUrl: './turnover-master-delete-dialog.component.html'
})
export class TurnoverMasterDeleteDialogComponent {
  turnoverMaster: ITurnoverMaster;

  constructor(
    protected turnoverMasterService: TurnoverMasterService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.turnoverMasterService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'turnoverMasterListModification',
        content: 'Deleted an turnoverMaster'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-turnover-master-delete-popup',
  template: ''
})
export class TurnoverMasterDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ turnoverMaster }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(TurnoverMasterDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.turnoverMaster = turnoverMaster;
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
