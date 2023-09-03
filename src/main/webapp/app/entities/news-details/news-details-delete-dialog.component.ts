import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';
import { INewsDetails } from 'app/shared/model/news-details.model';
import { NewsDetailsService } from './news-details.service';

@Component({
  selector: 'jhi-news-details-delete-dialog',
  templateUrl: './news-details-delete-dialog.component.html'
})
export class NewsDetailsDeleteDialogComponent {
  newsDetails: INewsDetails;

  constructor(
    protected newsDetailsService: NewsDetailsService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.newsDetailsService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'newsDetailsListModification',
        content: 'Deleted an newsDetails'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-news-details-delete-popup',
  template: ''
})
export class NewsDetailsDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ newsDetails }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(NewsDetailsDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.newsDetails = newsDetails;
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
