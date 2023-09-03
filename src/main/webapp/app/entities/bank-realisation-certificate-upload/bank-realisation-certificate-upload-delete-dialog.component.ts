import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';
import { IBankRealisationCertificateUpload } from 'app/entities/bank-realisation-certificate-upload/bank-realisation-certificate-upload.model';
import { BankRealisationCertificateUploadService } from 'app/entities/bank-realisation-certificate-upload/bank-realisation-certificate-upload.service';

@Component({
  selector: 'jhi-bank-realisation-certificate-upload-delete-dialog',
  templateUrl: './bank-realisation-certificate-upload-delete-dialog.component.html'
})
export class BankRealisationCertificateUploadDeleteDialogComponent {
  bankRealisationCertificateUpload: IBankRealisationCertificateUpload;

  constructor(
    protected bankRealisationCertificateUploadService: BankRealisationCertificateUploadService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.bankRealisationCertificateUploadService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'bankRealisationCertificateUploadListModification',
        content: 'Deleted an bankRealisationCertificateUpload'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-bank-realisation-certificate-upload-delete-popup',
  template: ''
})
export class BankRealisationCertificateUploadPopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ bankRealisationCertificateUpload }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(BankRealisationCertificateUploadDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.bankRealisationCertificateUpload = bankRealisationCertificateUpload;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/bank-realisation-certificate-upload', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/bank-realisation-certificate-upload', { outlets: { popup: null } }]);
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
