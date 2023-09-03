import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IGstVoplUpload } from 'app/shared/model/gst-vopl-upload.model';
import { GstVoplUploadService } from './gst-vopl-upload.service';

@Component({
  selector: 'jhi-gst-vopl-upload-delete-dialog',
  templateUrl: './gst-vopl-upload-delete-dialog.component.html'
})
export class GstVoplUploadDeleteDialogComponent {
  gstVoplUpload: IGstVoplUpload;

  constructor(
    protected gstVoplUploadService: GstVoplUploadService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.gstVoplUploadService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'gstVoplUploadListModification',
        content: 'Deleted an gstVoplUpload'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-gst-vopl-upload-delete-popup',
  template: ''
})
export class GstVoplUploadDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ gstVoplUpload }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(GstVoplUploadDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.gstVoplUpload = gstVoplUpload;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/gst-vopl-upload', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/gst-vopl-upload', { outlets: { popup: null } }]);
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
