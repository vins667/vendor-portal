import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';
import { IMenuMaster } from 'app/shared/model/menu-master.model';
import { MenuMasterService } from './menu-master.service';

@Component({
  selector: 'jhi-menu-master-delete-dialog',
  templateUrl: './menu-master-delete-dialog.component.html'
})
export class MenuMasterDeleteDialogComponent {
  menuMaster: IMenuMaster;

  constructor(
    protected menuMasterService: MenuMasterService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.menuMasterService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'menuMasterListModification',
        content: 'Deleted an menuMaster'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-menu-master-delete-popup',
  template: ''
})
export class MenuMasterDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ menuMaster }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MenuMasterDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.menuMaster = menuMaster;
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
