import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';
import { IScriptDetailsUpload } from 'app/entities/script-details-upload/script-details-upload.model';
import { ScriptDetailsUploadService } from 'app/entities/script-details-upload/script-details-upload.service';

@Component({
  selector: 'jhi-script-details-upload-delete-dialog',
  templateUrl: './script-details-upload-delete-dialog.component.html'
})
export class ScriptDetailsUploadDeleteDialogComponent {
  scriptDetailsUpload: IScriptDetailsUpload;

  constructor(
    protected scriptDetailsUploadService: ScriptDetailsUploadService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}
  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.scriptDetailsUploadService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'scriptDetailsUploadListModification',
        content: 'Deleted an bankRealisationCertificateUpload'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-script-details-upload-delete-popup',
  template: ''
})
export class ScriptDetailsUploadPopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ scriptDetailsUpload }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(ScriptDetailsUploadDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.scriptDetailsUpload = scriptDetailsUpload;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/script-uploads', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/script-uploads', { outlets: { popup: null } }]);
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
