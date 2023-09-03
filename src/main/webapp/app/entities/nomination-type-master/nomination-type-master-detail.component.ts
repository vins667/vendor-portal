import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { INominationTypeMaster } from 'app/shared/model/nomination-type-master.model';

@Component({
  selector: 'jhi-nomination-type-master-detail',
  templateUrl: './nomination-type-master-detail.component.html'
})
export class NominationTypeMasterDetailComponent implements OnInit {
  nominationTypeMaster: INominationTypeMaster;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ nominationTypeMaster }) => {
      this.nominationTypeMaster = nominationTypeMaster;
    });
  }

  previousState() {
    window.history.back();
  }
}
