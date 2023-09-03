import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITravelFlightDetails } from 'app/shared/model/travel-flight-details.model';

@Component({
  selector: 'jhi-travel-flight-details-detail',
  templateUrl: './travel-flight-details-detail.component.html'
})
export class TravelFlightDetailsDetailComponent implements OnInit {
  travelFlightDetails: ITravelFlightDetails;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ travelFlightDetails }) => {
      this.travelFlightDetails = travelFlightDetails;
    });
  }

  previousState() {
    window.history.back();
  }
}
