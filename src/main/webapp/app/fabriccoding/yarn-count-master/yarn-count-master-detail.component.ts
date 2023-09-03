import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { IYarnCountMaster } from 'app/shared/model/yarn-count-master.model';

@Component({
  selector: 'jhi-yarn-count-master-detail',
  templateUrl: './yarn-count-master-detail.component.html'
})
export class YarnCountMasterDetailComponent implements OnInit {
  yarnCountMaster: IYarnCountMaster;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ yarnCountMaster }) => {
      this.yarnCountMaster = yarnCountMaster;
    });
  }

  previousState() {
    window.history.back();
  }
}
