import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';
import { ILanguageMaster } from 'app/shared/model/language-master.model';
import { LanguageMasterService } from './language-master.service';

@Component({
  selector: 'jhi-language-master-delete-dialog',
  templateUrl: './language-master-delete-dialog.component.html'
})
export class LanguageMasterDeleteDialogComponent {
  languageMaster: ILanguageMaster;

  constructor(
    protected languageMasterService: LanguageMasterService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.languageMasterService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'languageMasterListModification',
        content: 'Deleted an languageMaster'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-language-master-delete-popup',
  template: ''
})
export class LanguageMasterDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ languageMaster }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(LanguageMasterDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.languageMaster = languageMaster;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/language-master', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/language-master', { outlets: { popup: null } }]);
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
