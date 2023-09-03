import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { IKnitProcessMaster } from 'app/shared/model/knit-process-master.model';

@Component({
  selector: 'jhi-knit-process-master-detail',
  templateUrl: './knit-process-master-detail.component.html'
})
export class KnitProcessMasterDetailComponent implements OnInit {
  knitProcessMaster: IKnitProcessMaster;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ knitProcessMaster }) => {
      this.knitProcessMaster = knitProcessMaster;
    });
  }

  previousState() {
    window.history.back();
  }
}
