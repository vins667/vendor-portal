import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';
import { IRecruitmentCityMaster } from 'app/shared/model/recruitment-city-master.model';
import { RecruitmentCityMasterService } from './recruitment-city-master.service';

@Component({
  selector: 'jhi-recruitment-city-master-delete-dialog',
  templateUrl: './recruitment-city-master-delete-dialog.component.html'
})
export class RecruitmentCityMasterDeleteDialogComponent {
  recruitmentCityMaster: IRecruitmentCityMaster;

  constructor(
    protected recruitmentCityMasterService: RecruitmentCityMasterService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.recruitmentCityMasterService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'recruitmentCityMasterListModification',
        content: 'Deleted an recruitmentCityMaster'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-recruitment-city-master-delete-popup',
  template: ''
})
export class RecruitmentCityMasterDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ recruitmentCityMaster }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(RecruitmentCityMasterDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.recruitmentCityMaster = recruitmentCityMaster;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/recruitment-city-master', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/recruitment-city-master', { outlets: { popup: null } }]);
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
