import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITravelAccommodationDetails } from 'app/shared/model/travel-accommodation-details.model';

@Component({
  selector: 'jhi-travel-accommodation-details-detail',
  templateUrl: './travel-accommodation-details-detail.component.html'
})
export class TravelAccommodationDetailsDetailComponent implements OnInit {
  travelAccommodationDetails: ITravelAccommodationDetails;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ travelAccommodationDetails }) => {
      this.travelAccommodationDetails = travelAccommodationDetails;
    });
  }

  previousState() {
    window.history.back();
  }
}
