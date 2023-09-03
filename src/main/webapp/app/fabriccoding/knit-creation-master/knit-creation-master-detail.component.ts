import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { IKnitCreationMaster } from 'app/shared/model/knit-creation-master.model';

@Component({
  selector: 'jhi-knit-creation-master-detail',
  templateUrl: './knit-creation-master-detail.component.html'
})
export class KnitCreationMasterDetailComponent implements OnInit {
  knitCreationMaster: IKnitCreationMaster;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ knitCreationMaster }) => {
      this.knitCreationMaster = knitCreationMaster;
    });
  }

  previousState() {
    window.history.back();
  }
}
