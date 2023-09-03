import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { IYarnTypeMaster } from 'app/shared/model/yarn-type-master.model';

@Component({
  selector: 'jhi-yarn-type-master-detail',
  templateUrl: './yarn-type-master-detail.component.html'
})
export class YarnTypeMasterDetailComponent implements OnInit {
  yarnTypeMaster: IYarnTypeMaster;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ yarnTypeMaster }) => {
      this.yarnTypeMaster = yarnTypeMaster;
    });
  }

  previousState() {
    window.history.back();
  }
}
