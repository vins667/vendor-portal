import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IReportTypeMaster } from 'app/shared/model/report-type-master.model';

@Component({
  selector: 'jhi-report-type-master-detail',
  templateUrl: './report-type-master-detail.component.html'
})
export class ReportTypeMasterDetailComponent implements OnInit {
  reportTypeMaster: IReportTypeMaster;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ reportTypeMaster }) => {
      this.reportTypeMaster = reportTypeMaster;
    });
  }

  previousState() {
    window.history.back();
  }
}
