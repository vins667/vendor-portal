import {Component, OnInit, OnDestroy} from '@angular/core';
import {NgbModal, NgbModalRef} from '@ng-bootstrap/ng-bootstrap';
import {NgbActiveModal} from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'jhi-news-details-popup-preview',
  templateUrl: './news-details-popup-preview.component.html'
})
export class NewsDetailsPopupPreviewComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;
  ckeditorContent: string;
  newsTitle: string;

  constructor(protected modalService: NgbModal, public activeModal: NgbActiveModal) {
  }

  ngOnDestroy(): void {
    this.ngbModalRef = null;
  }

  ngOnInit() {
  }

  close() {
    this.activeModal.dismiss('cancel');
  }
}
