import { Component, Input, OnInit, ViewEncapsulation } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { DomSanitizer } from '@angular/platform-browser';
import { NgxExtendedPdfViewerService, pdfDefaultOptions } from 'ngx-extended-pdf-viewer';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
@Component({
  selector: 'jhi-popup-pdf-call',
  templateUrl: './popup-pdf.component.html',
  encapsulation: ViewEncapsulation.None
})
export class PopupPdfComponent implements OnInit {
  @Input() content?: any;
  urlSafe: any;
  constructor(
    public activeModal: NgbActiveModal,
    protected sanitizer: DomSanitizer,
    private ngxExtendedPdfViewerService: NgxExtendedPdfViewerService
  ) {
    pdfDefaultOptions.assetsFolder = 'bleeding-edge';
  }

  ngOnInit(): void {
    this.urlSafe = this.sanitizer.bypassSecurityTrustResourceUrl(this.content);
  }

  cancel(): void {
    this.activeModal.dismiss();
  }
}
