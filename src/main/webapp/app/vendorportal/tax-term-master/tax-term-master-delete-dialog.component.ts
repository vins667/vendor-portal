import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';
import { ITaxTermMaster } from 'app/shared/model/tax-term-master.model';
import { TaxTermMasterService } from './tax-term-master.service';

@Component({
  selector: 'jhi-tax-term-master-delete-dialog',
  templateUrl: './tax-term-master-delete-dialog.component.html'
})
export class TaxTermMasterDeleteDialogComponent {
  taxTermMaster: ITaxTermMaster;

  constructor(
    protected taxTermMasterService: TaxTermMasterService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.taxTermMasterService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'taxTermMasterListModification',
        content: 'Deleted an taxTermMaster'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-tax-term-master-delete-popup',
  template: ''
})
export class TaxTermMasterDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ taxTermMaster }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(TaxTermMasterDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.taxTermMaster = taxTermMaster;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/tax-term-master', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/tax-term-master', { outlets: { popup: null } }]);
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
