import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';
import { IIgnoreSoftwareMaster } from 'app/shared/model/ignore-software-master.model';
import { IgnoreSoftwareMasterService } from './ignore-software-master.service';

@Component({
  selector: 'jhi-ignore-software-master-delete-dialog',
  templateUrl: './ignore-software-master-delete-dialog.component.html'
})
export class IgnoreSoftwareMasterDeleteDialogComponent {
  ignoreSoftwareMaster: IIgnoreSoftwareMaster;

  constructor(
    protected ignoreSoftwareMasterService: IgnoreSoftwareMasterService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.ignoreSoftwareMasterService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'ignoreSoftwareMasterListModification',
        content: 'Deleted an ignoreSoftwareMaster'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-ignore-software-master-delete-popup',
  template: ''
})
export class IgnoreSoftwareMasterDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ ignoreSoftwareMaster }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(IgnoreSoftwareMasterDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.ignoreSoftwareMaster = ignoreSoftwareMaster;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/ignore-software-master', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/ignore-software-master', { outlets: { popup: null } }]);
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
