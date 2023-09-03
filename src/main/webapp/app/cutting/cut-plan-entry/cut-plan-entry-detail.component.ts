import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICutPlanEntry } from 'app/shared/model/cut-plan-entry.model';

@Component({
  selector: 'jhi-cut-plan-entry-detail',
  templateUrl: './cut-plan-entry-detail.component.html'
})
export class CutPlanEntryDetailComponent implements OnInit {
  cutPlanEntry: ICutPlanEntry;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ cutPlanEntry }) => {
      this.cutPlanEntry = cutPlanEntry;
    });
  }

  previousState() {
    window.history.back();
  }
}
