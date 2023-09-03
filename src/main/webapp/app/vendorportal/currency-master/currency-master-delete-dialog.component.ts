import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';
import { ICurrencyMaster } from 'app/shared/model/currency-master.model';
import { CurrencyMasterService } from './currency-master.service';

@Component({
  selector: 'jhi-currency-master-delete-dialog',
  templateUrl: './currency-master-delete-dialog.component.html'
})
export class CurrencyMasterDeleteDialogComponent {
  currencyMaster: ICurrencyMaster;

  constructor(
    protected currencyMasterService: CurrencyMasterService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.currencyMasterService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'currencyMasterListModification',
        content: 'Deleted an currencyMaster'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-currency-master-delete-popup',
  template: ''
})
export class CurrencyMasterDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ currencyMaster }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(CurrencyMasterDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.currencyMaster = currencyMaster;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/currency-master', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/currency-master', { outlets: { popup: null } }]);
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
