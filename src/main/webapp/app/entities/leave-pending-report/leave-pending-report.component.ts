import { Component, OnInit } from '@angular/core';
import * as FileSaver from 'file-saver';
import { LeavePendingReportService } from './leave-pending-report.service';
import { IFactoryMaster } from 'app/shared/model/factory-master.model';
import { FactoryMasterService } from 'app/entities/factory-master';
import { ILeaveSearch, LeaveSearch } from 'app/shared/model/leave-search.model';
import { SnotifyPosition, SnotifyService } from 'ng-snotify';
import { toastConfig } from 'app/core/toast/toast-config';
@Component({
  selector: 'jhi-leave-pending-report',
  templateUrl: './leave-pending-report.component.html'
})
export class LeavePendingReportComponent implements OnInit {
  currentAccount: any;
  search: ILeaveSearch;
  factoryMasters: IFactoryMaster[];
  isDownload = false;
  status: string;
  constructor(
    protected leavePendingReportService: LeavePendingReportService,
    protected factoryMasterService: FactoryMasterService,
    public snotifyService: SnotifyService
  ) {}

  ngOnInit(): void {
    this.search = new LeaveSearch();
    this.factoryMasterService.query().subscribe(factoryMasters => {
      this.factoryMasters = factoryMasters.body;
    });
  }

  validate(): any {
    if (!this.search.leaveStatus) {
      this.snotifyService.error("Status can't empty!", '', toastConfig);
      return false;
    } else {
      if (!this.search.factory) {
        this.snotifyService.error("Factory can't empty!", '', toastConfig);
        return false;
      } else {
        if (this.search.leaveStatus === 'C') {
          if (this.search.leaveDateFrom && this.search.leaveDateTo) {
            return true;
          } else {
            this.snotifyService.error("Date From and Date To can't empty!", '', toastConfig);
            return false;
          }
        } else {
          return true;
        }
      }
    }
  }

  generateReport(): void {
    if (this.validate()) {
      this.isDownload = true;
      this.leavePendingReportService.downloadPdf(this.search).subscribe(
        res => {
          FileSaver.saveAs(res, 'LeavePendingReport.xlsx');
          this.isDownload = false;
        },
        res => {
          this.isDownload = false;
        }
      );
    }
  }
}
