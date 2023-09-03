import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { IWorkerJoinFlowMaster } from 'app/shared/model/worker-join-flow-master.model';

@Component({
  selector: 'jhi-worker-join-flow-master-detail',
  templateUrl: './worker-join-flow-master-detail.component.html'
})
export class WorkerJoinFlowMasterDetailComponent implements OnInit {
  workerJoinFlowMaster: IWorkerJoinFlowMaster;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ workerJoinFlowMaster }) => {
      this.workerJoinFlowMaster = workerJoinFlowMaster;
    });
  }

  previousState() {
    window.history.back();
  }
}
