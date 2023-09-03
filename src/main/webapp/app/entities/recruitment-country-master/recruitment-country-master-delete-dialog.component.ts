import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';
import { IRecruitmentCountryMaster } from 'app/shared/model/recruitment-country-master.model';
import { RecruitmentCountryMasterService } from './recruitment-country-master.service';

@Component({
  selector: 'jhi-recruitment-country-master-delete-dialog',
  templateUrl: './recruitment-country-master-delete-dialog.component.html'
})
export class RecruitmentCountryMasterDeleteDialogComponent {
  recruitmentCountryMaster: IRecruitmentCountryMaster;

  constructor(
    protected recruitmentCountryMasterService: RecruitmentCountryMasterService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.recruitmentCountryMasterService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'recruitmentCountryMasterListModification',
        content: 'Deleted an recruitmentCountryMaster'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-recruitment-country-master-delete-popup',
  template: ''
})
export class RecruitmentCountryMasterDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ recruitmentCountryMaster }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(RecruitmentCountryMasterDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.recruitmentCountryMaster = recruitmentCountryMaster;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/recruitment-country-master', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/recruitment-country-master', { outlets: { popup: null } }]);
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
