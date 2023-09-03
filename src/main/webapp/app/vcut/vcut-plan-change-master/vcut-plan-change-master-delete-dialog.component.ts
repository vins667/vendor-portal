import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';
import { IVcutPlanChangeMaster } from 'app/shared/model/vcut-plan-change-master.model';
import { VcutPlanChangeMasterService } from './vcut-plan-change-master.service';

@Component({
  selector: 'jhi-vcut-plan-change-master-delete-dialog',
  templateUrl: './vcut-plan-change-master-delete-dialog.component.html'
})
export class VcutPlanChangeMasterDeleteDialogComponent {
  vcutPlanChangeMaster: IVcutPlanChangeMaster;

  constructor(
    protected vcutPlanChangeMasterService: VcutPlanChangeMasterService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.vcutPlanChangeMasterService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'vcutPlanChangeMasterListModification',
        content: 'Deleted an vcutPlanChangeMaster'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-vcut-plan-change-master-delete-popup',
  template: ''
})
export class VcutPlanChangeMasterDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ vcutPlanChangeMaster }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(VcutPlanChangeMasterDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.vcutPlanChangeMaster = vcutPlanChangeMaster;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/vcut-plan-change-master', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/vcut-plan-change-master', { outlets: { popup: null } }]);
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
