import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { IWorkerForwardTypeMaster } from 'app/shared/model/worker-forward-type-master.model';

@Component({
  selector: 'jhi-worker-forward-type-master-detail',
  templateUrl: './worker-forward-type-master-detail.component.html'
})
export class WorkerForwardTypeMasterDetailComponent implements OnInit {
  workerForwardTypeMaster: IWorkerForwardTypeMaster;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ workerForwardTypeMaster }) => {
      this.workerForwardTypeMaster = workerForwardTypeMaster;
    });
  }

  previousState() {
    window.history.back();
  }
}
