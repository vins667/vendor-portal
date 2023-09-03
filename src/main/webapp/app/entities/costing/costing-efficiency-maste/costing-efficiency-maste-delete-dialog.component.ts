import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICostingEfficiencyMaste } from 'app/shared/model/costing-efficiency-maste.model';
import { CostingEfficiencyMasteService } from './costing-efficiency-maste.service';

@Component({
  selector: 'jhi-costing-efficiency-maste-delete-dialog',
  templateUrl: './costing-efficiency-maste-delete-dialog.component.html'
})
export class CostingEfficiencyMasteDeleteDialogComponent {
  costingEfficiencyMaste: ICostingEfficiencyMaste;

  constructor(
    protected costingEfficiencyMasteService: CostingEfficiencyMasteService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.costingEfficiencyMasteService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'costingEfficiencyMasteListModification',
        content: 'Deleted an costingEfficiencyMaste'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-costing-efficiency-maste-delete-popup',
  template: ''
})
export class CostingEfficiencyMasteDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ costingEfficiencyMaste }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(CostingEfficiencyMasteDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.costingEfficiencyMaste = costingEfficiencyMaste;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/costing-efficiency-maste', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/costing-efficiency-maste', { outlets: { popup: null } }]);
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
