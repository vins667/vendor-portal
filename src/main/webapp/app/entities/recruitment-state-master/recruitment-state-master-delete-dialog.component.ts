import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';
import { IRecruitmentStateMaster } from 'app/shared/model/recruitment-state-master.model';
import { RecruitmentStateMasterService } from './recruitment-state-master.service';

@Component({
  selector: 'jhi-recruitment-state-master-delete-dialog',
  templateUrl: './recruitment-state-master-delete-dialog.component.html'
})
export class RecruitmentStateMasterDeleteDialogComponent {
  recruitmentStateMaster: IRecruitmentStateMaster;

  constructor(
    protected recruitmentStateMasterService: RecruitmentStateMasterService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.recruitmentStateMasterService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'recruitmentStateMasterListModification',
        content: 'Deleted an recruitmentStateMaster'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-recruitment-state-master-delete-popup',
  template: ''
})
export class RecruitmentStateMasterDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ recruitmentStateMaster }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(RecruitmentStateMasterDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.recruitmentStateMaster = recruitmentStateMaster;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/recruitment-state-master', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/recruitment-state-master', { outlets: { popup: null } }]);
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
