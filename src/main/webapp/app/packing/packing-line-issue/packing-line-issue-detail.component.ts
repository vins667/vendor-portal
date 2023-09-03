import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICutPlanIssueStitch } from 'app/shared/model/cut-plan-issue-stitch.model';

@Component({
  selector: 'jhi-packing-line-issue-detail',
  templateUrl: './packing-line-issue-detail.component.html'
})
export class PackingLineIssueDetailComponent implements OnInit {
  cutPlanIssueStitch: ICutPlanIssueStitch | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ cutPlanIssueStitch }) => (this.cutPlanIssueStitch = cutPlanIssueStitch));
  }

  previousState(): void {
    window.history.back();
  }
}
