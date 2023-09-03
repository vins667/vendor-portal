import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';
import { ITdsGroupDetails } from 'app/shared/model/tds-group-details.model';
import { TdsGroupDetailsService } from './tds-group-details.service';

@Component({
  selector: 'jhi-tds-group-details-delete-dialog',
  templateUrl: './tds-group-details-delete-dialog.component.html'
})
export class TdsGroupDetailsDeleteDialogComponent {
  tdsGroupDetails: ITdsGroupDetails;

  constructor(
    protected tdsGroupDetailsService: TdsGroupDetailsService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.tdsGroupDetailsService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'tdsGroupDetailsListModification',
        content: 'Deleted an tdsGroupDetails'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-tds-group-details-delete-popup',
  template: ''
})
export class TdsGroupDetailsDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ tdsGroupDetails }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(TdsGroupDetailsDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.tdsGroupDetails = tdsGroupDetails;
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
