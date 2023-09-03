import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';
import { ICategoryMaster } from 'app/shared/model/category-master.model';
import { CategoryMasterService } from './category-master.service';

@Component({
  selector: 'jhi-category-master-delete-dialog',
  templateUrl: './category-master-delete-dialog.component.html'
})
export class CategoryMasterDeleteDialogComponent {
  categoryMaster: ICategoryMaster;

  constructor(
    protected categoryMasterService: CategoryMasterService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.categoryMasterService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'categoryMasterListModification',
        content: 'Deleted an categoryMaster'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-category-master-delete-popup',
  template: ''
})
export class CategoryMasterDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ categoryMaster }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(CategoryMasterDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.categoryMaster = categoryMaster;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/category-master', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/category-master', { outlets: { popup: null } }]);
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
