import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { IWorkerWorkFlowMaster } from 'app/shared/model/worker-work-flow-master.model';

@Component({
  selector: 'jhi-worker-work-flow-master-detail',
  templateUrl: './worker-work-flow-master-detail.component.html'
})
export class WorkerWorkFlowMasterDetailComponent implements OnInit {
  workerWorkFlowMaster: IWorkerWorkFlowMaster;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ workerWorkFlowMaster }) => {
      this.workerWorkFlowMaster = workerWorkFlowMaster;
    });
  }

  previousState() {
    window.history.back();
  }
}
