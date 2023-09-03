import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { IRateMaster } from 'app/shared/model/rate-master.model';

@Component({
  selector: 'jhi-rate-master-detail',
  templateUrl: './rate-master-detail.component.html'
})
export class RateMasterDetailComponent implements OnInit {
  rateMaster: IRateMaster;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ rateMaster }) => {
      this.rateMaster = rateMaster;
    });
  }

  previousState() {
    window.history.back();
  }
}
