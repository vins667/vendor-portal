import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITravelForexDetails } from 'app/shared/model/travel-forex-details.model';

@Component({
  selector: 'jhi-travel-forex-details-detail',
  templateUrl: './travel-forex-details-detail.component.html'
})
export class TravelForexDetailsDetailComponent implements OnInit {
  travelForexDetails: ITravelForexDetails;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ travelForexDetails }) => {
      this.travelForexDetails = travelForexDetails;
    });
  }

  previousState() {
    window.history.back();
  }
}
