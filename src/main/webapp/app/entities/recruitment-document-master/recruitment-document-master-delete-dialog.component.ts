import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';
import { IRecruitmentDocumentMaster } from 'app/shared/model/recruitment-document-master.model';
import { RecruitmentDocumentMasterService } from './recruitment-document-master.service';

@Component({
  selector: 'jhi-recruitment-document-master-delete-dialog',
  templateUrl: './recruitment-document-master-delete-dialog.component.html'
})
export class RecruitmentDocumentMasterDeleteDialogComponent {
  recruitmentDocumentMaster: IRecruitmentDocumentMaster;

  constructor(
    protected recruitmentDocumentMasterService: RecruitmentDocumentMasterService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.recruitmentDocumentMasterService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'recruitmentDocumentMasterListModification',
        content: 'Deleted an recruitmentDocumentMaster'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-recruitment-document-master-delete-popup',
  template: ''
})
export class RecruitmentDocumentMasterDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ recruitmentDocumentMaster }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(RecruitmentDocumentMasterDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.recruitmentDocumentMaster = recruitmentDocumentMaster;
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
