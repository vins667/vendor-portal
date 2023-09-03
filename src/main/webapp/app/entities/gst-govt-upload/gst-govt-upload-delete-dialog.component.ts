import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IGstGovtUpload } from 'app/shared/model/gst-govt-upload.model';
import { GstGovtUploadService } from './gst-govt-upload.service';

@Component({
  selector: 'jhi-gst-govt-upload-delete-dialog',
  templateUrl: './gst-govt-upload-delete-dialog.component.html'
})
export class GstGovtUploadDeleteDialogComponent {
  gstGovtUpload: IGstGovtUpload;

  constructor(
    protected gstGovtUploadService: GstGovtUploadService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.gstGovtUploadService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'gstGovtUploadListModification',
        content: 'Deleted an gstGovtUpload'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-gst-govt-upload-delete-popup',
  template: ''
})
export class GstGovtUploadDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ gstGovtUpload }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(GstGovtUploadDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.gstGovtUpload = gstGovtUpload;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/gst-govt-upload', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/gst-govt-upload', { outlets: { popup: null } }]);
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
