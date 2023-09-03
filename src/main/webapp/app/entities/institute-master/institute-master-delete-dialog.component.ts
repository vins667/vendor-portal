import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';
import { IInstituteMaster } from 'app/shared/model/institute-master.model';
import { InstituteMasterService } from './institute-master.service';

@Component({
  selector: 'jhi-institute-master-delete-dialog',
  templateUrl: './institute-master-delete-dialog.component.html'
})
export class InstituteMasterDeleteDialogComponent {
  instituteMaster: IInstituteMaster;

  constructor(
    protected instituteMasterService: InstituteMasterService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.instituteMasterService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'instituteMasterListModification',
        content: 'Deleted an instituteMaster'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-institute-master-delete-popup',
  template: ''
})
export class InstituteMasterDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ instituteMaster }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(InstituteMasterDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.instituteMaster = instituteMaster;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/institute-master', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/institute-master', { outlets: { popup: null } }]);
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
