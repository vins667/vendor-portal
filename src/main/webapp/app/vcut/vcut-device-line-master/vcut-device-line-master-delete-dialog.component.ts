import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IVcutDeviceLineMaster } from 'app/shared/model/vcut-device-line-master.model';
import { VcutDeviceLineMasterService } from './vcut-device-line-master.service';

@Component({
  selector: 'jhi-vcut-device-line-master-delete-dialog',
  templateUrl: './vcut-device-line-master-delete-dialog.component.html'
})
export class VcutDeviceLineMasterDeleteDialogComponent {
  vcutDeviceLineMaster: IVcutDeviceLineMaster;

  constructor(
    protected vcutDeviceLineMasterService: VcutDeviceLineMasterService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.vcutDeviceLineMasterService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'vcutDeviceLineMasterListModification',
        content: 'Deleted an vcutDeviceLineMaster'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-vcut-device-line-master-delete-popup',
  template: ''
})
export class VcutDeviceLineMasterDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ vcutDeviceLineMaster }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(VcutDeviceLineMasterDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.vcutDeviceLineMaster = vcutDeviceLineMaster;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/vcut-device-line-master', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/vcut-device-line-master', { outlets: { popup: null } }]);
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
