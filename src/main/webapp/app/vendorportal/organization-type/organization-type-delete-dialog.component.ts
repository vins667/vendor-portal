import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';
import { IOrganizationType } from 'app/shared/model/organization-type.model';
import { OrganizationTypeService } from './organization-type.service';

@Component({
  selector: 'jhi-organization-type-delete-dialog',
  templateUrl: './organization-type-delete-dialog.component.html'
})
export class OrganizationTypeDeleteDialogComponent {
  organizationType: IOrganizationType;

  constructor(
    protected organizationTypeService: OrganizationTypeService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.organizationTypeService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'organizationTypeListModification',
        content: 'Deleted an organizationType'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-organization-type-delete-popup',
  template: ''
})
export class OrganizationTypeDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ organizationType }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(OrganizationTypeDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.organizationType = organizationType;
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
