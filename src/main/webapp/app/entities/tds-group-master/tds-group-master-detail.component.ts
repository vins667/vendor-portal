import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ITdsGroupMaster } from 'app/shared/model/tds-group-master.model';

@Component({
  selector: 'jhi-tds-group-master-detail',
  templateUrl: './tds-group-master-detail.component.html'
})
export class TdsGroupMasterDetailComponent implements OnInit {
  tdsGroupMaster: ITdsGroupMaster;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ tdsGroupMaster }) => {
      this.tdsGroupMaster = tdsGroupMaster;
    });
  }

  previousState() {
    window.history.back();
  }
}
