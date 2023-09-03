import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IStitchIssuePack } from 'app/shared/model/stitch-issue-pack.model';

@Component({
  selector: 'jhi-stitch-issue-pack-detail',
  templateUrl: './stitch-issue-pack-detail.component.html'
})
export class StitchIssuePackDetailComponent implements OnInit {
  stitchIssuePack: IStitchIssuePack | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ stitchIssuePack }) => (this.stitchIssuePack = stitchIssuePack));
  }

  previousState(): void {
    window.history.back();
  }
}
