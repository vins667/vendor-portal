import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ITdsYearMaster } from 'app/shared/model/tds-year-master.model';

@Component({
  selector: 'jhi-tds-year-master-detail',
  templateUrl: './tds-year-master-detail.component.html'
})
export class TdsYearMasterDetailComponent implements OnInit {
  tdsYearMaster: ITdsYearMaster;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ tdsYearMaster }) => {
      this.tdsYearMaster = tdsYearMaster;
    });
  }

  previousState() {
    window.history.back();
  }
}
