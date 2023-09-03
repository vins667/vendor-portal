import { Component, Input, OnInit, SecurityContext, ViewEncapsulation } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { DomSanitizer } from '@angular/platform-browser';

@Component({
  selector: 'jhi-popup-viwer-call',
  templateUrl: './popup-viewer.component.html',
  encapsulation: ViewEncapsulation.None
})
export class PopupViewerComponent implements OnInit {
  @Input() content?: any;
  urlSafe: any;
  constructor(public activeModal: NgbActiveModal, protected sanitizer: DomSanitizer) {}

  ngOnInit(): void {
    this.urlSafe = this.sanitizer.bypassSecurityTrustResourceUrl(this.content + '#toolbar=0');
  }

  cancel(): void {
    this.activeModal.dismiss();
  }

  printPdf() {
    const iframe = document.createElement('iframe');
    iframe.style.display = 'none';
    iframe.src = this.sanitizer.sanitize(SecurityContext.RESOURCE_URL, this.sanitizer.bypassSecurityTrustResourceUrl(this.content));
    document.body.appendChild(iframe);
    iframe.contentWindow.print();
  }
}
