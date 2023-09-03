import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';
import { IYarnTypeMaster } from 'app/shared/model/yarn-type-master.model';
import { YarnTypeMasterService } from './yarn-type-master.service';

@Component({
  selector: 'jhi-yarn-type-master-delete-dialog',
  templateUrl: './yarn-type-master-delete-dialog.component.html'
})
export class YarnTypeMasterDeleteDialogComponent {
  yarnTypeMaster: IYarnTypeMaster;

  constructor(
    protected yarnTypeMasterService: YarnTypeMasterService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.yarnTypeMasterService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'yarnTypeMasterListModification',
        content: 'Deleted an yarnTypeMaster'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-yarn-type-master-delete-popup',
  template: ''
})
export class YarnTypeMasterDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ yarnTypeMaster }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(YarnTypeMasterDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.yarnTypeMaster = yarnTypeMaster;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/yarn-type-master', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/yarn-type-master', { outlets: { popup: null } }]);
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
