import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITravelApplicationMaster } from 'app/shared/model/travel-application-master.model';

@Component({
  selector: 'jhi-travel-application-master-detail',
  templateUrl: './travel-application-master-detail.component.html'
})
export class TravelApplicationMasterDetailComponent implements OnInit {
  travelApplicationMaster: ITravelApplicationMaster;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ travelApplicationMaster }) => {
      this.travelApplicationMaster = travelApplicationMaster;
    });
  }

  previousState() {
    window.history.back();
  }
}
