import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';
import { IRecruitmentDistrict } from 'app/shared/model/recruitment-district.model';
import { RecruitmentDistrictService } from './recruitment-district.service';

@Component({
  selector: 'jhi-recruitment-district-delete-dialog',
  templateUrl: './recruitment-district-delete-dialog.component.html'
})
export class RecruitmentDistrictDeleteDialogComponent {
  recruitmentDistrict: IRecruitmentDistrict;

  constructor(
    protected recruitmentDistrictService: RecruitmentDistrictService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.recruitmentDistrictService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'recruitmentDistrictListModification',
        content: 'Deleted an recruitmentDistrict'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-recruitment-district-delete-popup',
  template: ''
})
export class RecruitmentDistrictDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ recruitmentDistrict }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(RecruitmentDistrictDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.recruitmentDistrict = recruitmentDistrict;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/recruitment-district', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/recruitment-district', { outlets: { popup: null } }]);
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
