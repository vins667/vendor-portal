import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';
import { IYarnCountMaster } from 'app/shared/model/yarn-count-master.model';
import { YarnCountMasterService } from './yarn-count-master.service';

@Component({
  selector: 'jhi-yarn-count-master-delete-dialog',
  templateUrl: './yarn-count-master-delete-dialog.component.html'
})
export class YarnCountMasterDeleteDialogComponent {
  yarnCountMaster: IYarnCountMaster;

  constructor(
    protected yarnCountMasterService: YarnCountMasterService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.yarnCountMasterService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'yarnCountMasterListModification',
        content: 'Deleted an yarnCountMaster'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-yarn-count-master-delete-popup',
  template: ''
})
export class YarnCountMasterDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ yarnCountMaster }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(YarnCountMasterDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.yarnCountMaster = yarnCountMaster;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/yarn-count-master', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/yarn-count-master', { outlets: { popup: null } }]);
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
