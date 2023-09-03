import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { IWorkerRecruitment } from 'app/shared/model/worker-recruitment.model';

@Component({
  selector: 'jhi-worker-recruitment-detail',
  templateUrl: './worker-recruitment-detail.component.html'
})
export class WorkerRecruitmentDetailComponent implements OnInit {
  workerRecruitment: IWorkerRecruitment;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ workerRecruitment }) => {
      this.workerRecruitment = workerRecruitment;
    });
  }

  previousState() {
    window.history.back();
  }
}
