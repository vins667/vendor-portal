import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { IPollMaster } from 'app/shared/model/poll-master.model';

@Component({
  selector: 'jhi-poll-master-detail',
  templateUrl: './poll-master-detail.component.html'
})
export class PollMasterDetailComponent implements OnInit {
  pollMaster: IPollMaster;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ pollMaster }) => {
      this.pollMaster = pollMaster;
    });
  }

  previousState() {
    window.history.back();
  }
}
