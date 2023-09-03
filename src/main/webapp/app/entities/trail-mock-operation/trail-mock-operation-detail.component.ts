import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ITrailMockOperation } from 'app/shared/model/trail-mock-operation.model';

@Component({
  selector: 'jhi-trail-mock-operation-detail',
  templateUrl: './trail-mock-operation-detail.component.html'
})
export class TrailMockOperationDetailComponent implements OnInit {
  trailMockOperation: ITrailMockOperation;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ trailMockOperation }) => {
      this.trailMockOperation = trailMockOperation;
    });
  }

  previousState() {
    window.history.back();
  }
}
