import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IVcutStylePlanUpload } from 'app/shared/model/vcut-style-plan-upload.model';
import { VcutStylePlanUploadService } from './vcut-style-plan-upload.service';

@Component({
  selector: 'jhi-vcut-style-plan-upload-delete-dialog',
  templateUrl: './vcut-style-plan-upload-delete-dialog.component.html'
})
export class VcutStylePlanUploadDeleteDialogComponent {
  vcutStylePlanUpload: IVcutStylePlanUpload;

  constructor(
    protected vcutStylePlanUploadService: VcutStylePlanUploadService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.vcutStylePlanUploadService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'vcutStylePlanUploadListModification',
        content: 'Deleted an vcutStylePlanUpload'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-vcut-style-plan-upload-delete-popup',
  template: ''
})
export class VcutStylePlanUploadDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ vcutStylePlanUpload }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(VcutStylePlanUploadDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.vcutStylePlanUpload = vcutStylePlanUpload;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/vcut-style-plan-upload', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/vcut-style-plan-upload', { outlets: { popup: null } }]);
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
