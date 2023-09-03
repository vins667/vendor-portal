import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { IKnitTypeMaster } from 'app/shared/model/knit-type-master.model';

@Component({
  selector: 'jhi-knit-type-master-detail',
  templateUrl: './knit-type-master-detail.component.html'
})
export class KnitTypeMasterDetailComponent implements OnInit {
  knitTypeMaster: IKnitTypeMaster;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ knitTypeMaster }) => {
      this.knitTypeMaster = knitTypeMaster;
    });
  }

  previousState() {
    window.history.back();
  }
}
