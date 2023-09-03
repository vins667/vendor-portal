import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ITdsGroupDetails } from 'app/shared/model/tds-group-details.model';

@Component({
  selector: 'jhi-tds-group-details-detail',
  templateUrl: './tds-group-details-detail.component.html'
})
export class TdsGroupDetailsDetailComponent implements OnInit {
  tdsGroupDetails: ITdsGroupDetails;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ tdsGroupDetails }) => {
      this.tdsGroupDetails = tdsGroupDetails;
    });
  }

  previousState() {
    window.history.back();
  }
}
