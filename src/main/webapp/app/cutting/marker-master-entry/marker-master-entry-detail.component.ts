import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMarkerMasterEntry } from 'app/shared/model/marker-master-entry.model';

@Component({
  selector: 'jhi-marker-master-entry-detail',
  templateUrl: './marker-master-entry-detail.component.html'
})
export class MarkerMasterEntryDetailComponent implements OnInit {
  markerMasterEntry: IMarkerMasterEntry;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ markerMasterEntry }) => {
      this.markerMasterEntry = markerMasterEntry;
    });
  }

  previousState() {
    window.history.back();
  }
}
