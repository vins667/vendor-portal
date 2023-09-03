import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IJobWorkFollowup } from './job-work-followup.model';

@Component({
  selector: 'jhi-job-work-followup-detail',
  templateUrl: './job-work-followup-detail.component.html'
})
export class JobWorkFollowupDetailComponent implements OnInit {
  jobWorkFollowup: IJobWorkFollowup;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ jobWorkFollowup }) => {
      this.jobWorkFollowup = jobWorkFollowup;
    });
  }

  previousState() {
    window.history.back();
  }
}
