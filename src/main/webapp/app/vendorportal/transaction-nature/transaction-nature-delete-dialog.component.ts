import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';
import { ITransactionNature } from 'app/shared/model/transaction-nature.model';
import { TransactionNatureService } from './transaction-nature.service';

@Component({
  selector: 'jhi-transaction-nature-delete-dialog',
  templateUrl: './transaction-nature-delete-dialog.component.html'
})
export class TransactionNatureDeleteDialogComponent {
  transactionNature: ITransactionNature;

  constructor(
    protected transactionNatureService: TransactionNatureService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.transactionNatureService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'transactionNatureListModification',
        content: 'Deleted an transactionNature'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-transaction-nature-delete-popup',
  template: ''
})
export class TransactionNatureDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ transactionNature }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(TransactionNatureDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.transactionNature = transactionNature;
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
