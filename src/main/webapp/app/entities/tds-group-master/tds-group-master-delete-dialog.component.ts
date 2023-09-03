import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';
import { ITdsGroupMaster } from 'app/shared/model/tds-group-master.model';
import { TdsGroupMasterService } from './tds-group-master.service';

@Component({
  selector: 'jhi-tds-group-master-delete-dialog',
  templateUrl: './tds-group-master-delete-dialog.component.html'
})
export class TdsGroupMasterDeleteDialogComponent {
  tdsGroupMaster: ITdsGroupMaster;

  constructor(
    protected tdsGroupMasterService: TdsGroupMasterService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.tdsGroupMasterService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'tdsGroupMasterListModification',
        content: 'Deleted an tdsGroupMaster'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-tds-group-master-delete-popup',
  template: ''
})
export class TdsGroupMasterDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ tdsGroupMaster }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(TdsGroupMasterDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.tdsGroupMaster = tdsGroupMaster;
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
