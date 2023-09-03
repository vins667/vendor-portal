import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { IPreviousEmploymentDetails } from 'app/shared/model/previous-employment-details.model';

@Component({
  selector: 'jhi-previous-employment-details-detail',
  templateUrl: './previous-employment-details-detail.component.html'
})
export class PreviousEmploymentDetailsDetailComponent implements OnInit {
  previousEmploymentDetails: IPreviousEmploymentDetails;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ previousEmploymentDetails }) => {
      this.previousEmploymentDetails = previousEmploymentDetails;
    });
  }

  previousState() {
    window.history.back();
  }
}
