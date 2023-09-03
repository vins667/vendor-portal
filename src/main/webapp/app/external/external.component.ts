import {Component, OnInit} from '@angular/core';
import {DomSanitizer, SafeResourceUrl} from '@angular/platform-browser';

@Component({
  selector: 'jhi-external',
  templateUrl: './external.component.html'
})
export class ExternalComponent implements OnInit {
  srcUrl: any;
  urlSafe: SafeResourceUrl;

  constructor(public sanitizer: DomSanitizer) {
  }

  ngOnInit() {
    this.srcUrl = 'http://localhost:4200';
    this.urlSafe = this.sanitizer.bypassSecurityTrustResourceUrl(this.srcUrl);
  }
}
