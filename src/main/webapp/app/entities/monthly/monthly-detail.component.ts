import { Component, Input, OnInit } from '@angular/core';
import { MonthlyService } from './monthly.service';
import * as FileSaver from 'file-saver';
import { SnotifyService } from 'ng-snotify';
import { HttpResponse } from '@angular/common/http';
import { IMessage } from 'app/shared/model/message.model';
import { toastConfig } from 'app/core/toast/toast-config';
import { Monthly } from 'app/shared/model/monthly.model';

@Component({
  selector: 'jhi-monthly-details-detail',
  templateUrl: './monthly-detail.component.html'
})
export class MonthlyDetailComponent implements OnInit {
  @Input() monthly: Monthly;
  isDownloading = false;

  constructor(private monthlyService: MonthlyService, private snotifyService: SnotifyService) {}

  ngOnInit() {
    this.isDownloading = false;
  }

  generateReport(monthYear: string) {
    if (monthYear) {
      this.isDownloading = true;
      this.monthlyService.downloadPdf(monthYear).subscribe(
        res => {
          FileSaver.saveAs(res, 'SalaryReport.pdf');
          this.isDownloading = false;
        },
        res => {
          this.isDownloading = false;
        }
      );
    }
  }

  postMail(monthYear: string) {
    if (monthYear) {
      this.isDownloading = true;
      this.monthlyService.postMail(monthYear).subscribe(
        (res: HttpResponse<IMessage>) => {
          if (res.body.type === 'success') {
            this.snotifyService.success(res.body.msg, '', toastConfig);
          } else {
            this.snotifyService.error(res.body.msg, '', toastConfig);
          }
          this.isDownloading = false;
        },
        res => {
          this.isDownloading = false;
        }
      );
    }
  }
}
