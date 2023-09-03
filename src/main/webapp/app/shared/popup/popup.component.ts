import { Component, Input, OnInit, ViewEncapsulation } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { DomSanitizer } from '@angular/platform-browser';

@Component({
  selector: 'jhi-popup-call',
  templateUrl: './popup.component.html',
  encapsulation: ViewEncapsulation.None
})
export class PopupComponent implements OnInit {
  @Input() content?: any;
  urlSafe: any;
  constructor(public activeModal: NgbActiveModal, protected sanitizer: DomSanitizer) {}

  ngOnInit(): void {
    this.urlSafe = this.sanitizer.bypassSecurityTrustResourceUrl(this.content);
  }

  cancel(): void {
    this.activeModal.dismiss();
  }
}
