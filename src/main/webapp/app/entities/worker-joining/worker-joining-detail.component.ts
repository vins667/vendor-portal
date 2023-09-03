import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { IWorkerJoining } from 'app/shared/model/worker-joining.model';

@Component({
  selector: 'jhi-worker-joining-detail',
  templateUrl: './worker-joining-detail.component.html'
})
export class WorkerJoiningDetailComponent implements OnInit {
  workerJoining: IWorkerJoining;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ workerJoining }) => {
      this.workerJoining = workerJoining;
    });
  }

  previousState() {
    window.history.back();
  }
}
