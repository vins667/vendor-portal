import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IVcutStyleImage } from 'app/shared/model/vcut-style-image.model';
import { VcutStyleImageService } from './vcut-style-image.service';

@Component({
  selector: 'jhi-vcut-style-image-delete-dialog',
  templateUrl: './vcut-style-image-delete-dialog.component.html'
})
export class VcutStyleImageDeleteDialogComponent {
  vcutStyleImage: IVcutStyleImage;

  constructor(
    protected vcutStyleImageService: VcutStyleImageService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.vcutStyleImageService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'vcutStyleImageListModification',
        content: 'Deleted an vcutStyleImage'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-vcut-style-image-delete-popup',
  template: ''
})
export class VcutStyleImageDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ vcutStyleImage }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(VcutStyleImageDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.vcutStyleImage = vcutStyleImage;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/vcut-style-image', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/vcut-style-image', { outlets: { popup: null } }]);
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
