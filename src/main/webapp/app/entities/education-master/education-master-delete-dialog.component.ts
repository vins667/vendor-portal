import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';
import { IEducationMaster } from 'app/shared/model/education-master.model';
import { EducationMasterService } from './education-master.service';

@Component({
  selector: 'jhi-education-master-delete-dialog',
  templateUrl: './education-master-delete-dialog.component.html'
})
export class EducationMasterDeleteDialogComponent {
  educationMaster: IEducationMaster;

  constructor(
    protected educationMasterService: EducationMasterService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.educationMasterService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'educationMasterListModification',
        content: 'Deleted an educationMaster'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-education-master-delete-popup',
  template: ''
})
export class EducationMasterDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ educationMaster }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(EducationMasterDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.educationMaster = educationMaster;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/education-master', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/education-master', { outlets: { popup: null } }]);
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
