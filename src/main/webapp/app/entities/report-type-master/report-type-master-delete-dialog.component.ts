import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IReportTypeMaster } from 'app/shared/model/report-type-master.model';
import { ReportTypeMasterService } from './report-type-master.service';

@Component({
  selector: 'jhi-report-type-master-delete-dialog',
  templateUrl: './report-type-master-delete-dialog.component.html'
})
export class ReportTypeMasterDeleteDialogComponent {
  reportTypeMaster: IReportTypeMaster;

  constructor(
    protected reportTypeMasterService: ReportTypeMasterService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.reportTypeMasterService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'reportTypeMasterListModification',
        content: 'Deleted an reportTypeMaster'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-report-type-master-delete-popup',
  template: ''
})
export class ReportTypeMasterDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ reportTypeMaster }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(ReportTypeMasterDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.reportTypeMaster = reportTypeMaster;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/report-type-master', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/report-type-master', { outlets: { popup: null } }]);
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
