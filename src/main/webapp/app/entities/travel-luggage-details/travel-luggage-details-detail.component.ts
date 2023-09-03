import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITravelLuggageDetails } from 'app/shared/model/travel-luggage-details.model';

@Component({
  selector: 'jhi-travel-luggage-details-detail',
  templateUrl: './travel-luggage-details-detail.component.html'
})
export class TravelLuggageDetailsDetailComponent implements OnInit {
  travelLuggageDetails: ITravelLuggageDetails;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ travelLuggageDetails }) => {
      this.travelLuggageDetails = travelLuggageDetails;
    });
  }

  previousState() {
    window.history.back();
  }
}
