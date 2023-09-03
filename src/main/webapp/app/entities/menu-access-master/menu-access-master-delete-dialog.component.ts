import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';
import { IMenuAccessMaster } from 'app/shared/model/menu-access-master.model';
import { MenuAccessMasterService } from './menu-access-master.service';

@Component({
  selector: 'jhi-menu-access-master-delete-dialog',
  templateUrl: './menu-access-master-delete-dialog.component.html'
})
export class MenuAccessMasterDeleteDialogComponent {
  menuAccessMaster: IMenuAccessMaster;

  constructor(
    protected menuAccessMasterService: MenuAccessMasterService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.menuAccessMasterService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'menuAccessMasterListModification',
        content: 'Deleted an menuAccessMaster'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-menu-access-master-delete-popup',
  template: ''
})
export class MenuAccessMasterDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ menuAccessMaster }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MenuAccessMasterDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.menuAccessMaster = menuAccessMaster;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
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
