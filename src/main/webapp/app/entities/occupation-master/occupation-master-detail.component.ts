import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { IOccupationMaster } from 'app/shared/model/occupation-master.model';

@Component({
  selector: 'jhi-occupation-master-detail',
  templateUrl: './occupation-master-detail.component.html'
})
export class OccupationMasterDetailComponent implements OnInit {
  occupationMaster: IOccupationMaster;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ occupationMaster }) => {
      this.occupationMaster = occupationMaster;
    });
  }

  previousState() {
    window.history.back();
  }
}
