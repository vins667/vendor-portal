import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IFollowupBuyer } from './followup-buyer.model';

@Component({
  selector: 'jhi-followup-buyer-detail',
  templateUrl: './followup-buyer-detail.component.html'
})
export class FollowupBuyerDetailComponent implements OnInit {
  followupBuyer: IFollowupBuyer;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ followupBuyer }) => {
      this.followupBuyer = followupBuyer;
    });
  }

  previousState() {
    window.history.back();
  }
}
