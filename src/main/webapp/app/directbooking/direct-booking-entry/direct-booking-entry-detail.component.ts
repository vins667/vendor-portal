import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IDirectBookingEntry } from 'app/shared/model/direct-booking-entry.model';

@Component({
  selector: 'jhi-direct-booking-entry-detail',
  templateUrl: './direct-booking-entry-detail.component.html'
})
export class DirectBookingEntryDetailComponent implements OnInit {
  directBookingEntry: IDirectBookingEntry | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ directBookingEntry }) => (this.directBookingEntry = directBookingEntry));
  }

  previousState(): void {
    window.history.back();
  }
}
