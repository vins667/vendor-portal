import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';
import { IBankMaster } from 'app/shared/model/bank-master.model';
import { BankMasterService } from './bank-master.service';

@Component({
  selector: 'jhi-bank-master-delete-dialog',
  templateUrl: './bank-master-delete-dialog.component.html'
})
export class BankMasterDeleteDialogComponent {
  bankMaster: IBankMaster;

  constructor(
    protected bankMasterService: BankMasterService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.bankMasterService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'bankMasterListModification',
        content: 'Deleted an bankMaster'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-bank-master-delete-popup',
  template: ''
})
export class BankMasterDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ bankMaster }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(BankMasterDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.bankMaster = bankMaster;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/bank-master', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/bank-master', { outlets: { popup: null } }]);
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
