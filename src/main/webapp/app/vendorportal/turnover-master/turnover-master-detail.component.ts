import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ITurnoverMaster } from 'app/shared/model/turnover-master.model';

@Component({
  selector: 'jhi-turnover-master-detail',
  templateUrl: './turnover-master-detail.component.html'
})
export class TurnoverMasterDetailComponent implements OnInit {
  turnoverMaster: ITurnoverMaster;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ turnoverMaster }) => {
      this.turnoverMaster = turnoverMaster;
    });
  }

  previousState() {
    window.history.back();
  }
}
