import { Component, OnInit } from '@angular/core';
import { Form16Service } from './form-16.service';
import * as FileSaver from 'file-saver';
import { SnotifyService } from 'ng-snotify';
import { toastConfig } from 'app/core/toast/toast-config';

@Component({
  selector: 'jhi-forms-16',
  templateUrl: './form-16.component.html'
})
export class Form16Component implements OnInit {
  isDownloading = false;

  constructor(protected form16Service: Form16Service, private snotifyService: SnotifyService) {}

  ngOnInit(): void {
    this.isDownloading = false;
  }

  generateReport(finYear: number): void {
    this.isDownloading = true;
    this.form16Service.download(finYear).subscribe(
      res => {
        FileSaver.saveAs(res, 'FORM16.pdf');
        this.isDownloading = false;
      },
      res => {
        this.isDownloading = false;
        this.snotifyService.error('FORM 16 not available for download.', toastConfig);
      }
    );
  }
}
