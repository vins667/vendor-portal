import { Component, OnInit } from '@angular/core';
import { ComputationDownloadService } from './computation-download.service';
import * as FileSaver from 'file-saver';
import { SnotifyService } from 'ng-snotify';
import { toastConfig } from 'app/core/toast/toast-config';

@Component({
  selector: 'jhi-forms-16',
  templateUrl: './computation-download.component.html'
})
export class ComputationDownloadComponent implements OnInit {
  isDownloading = false;

  constructor(protected computationDownloadService: ComputationDownloadService, private snotifyService: SnotifyService) {}

  ngOnInit(): void {
    this.isDownloading = false;
  }

  generateReport(): void {
    this.isDownloading = true;
    this.computationDownloadService.download().subscribe(
      res => {
        FileSaver.saveAs(res, 'COMPUTATION.pdf');
        this.isDownloading = false;
      },
      res => {
        this.isDownloading = false;
        this.snotifyService.error('Computation not available for download.', toastConfig);
      }
    );
  }
}
