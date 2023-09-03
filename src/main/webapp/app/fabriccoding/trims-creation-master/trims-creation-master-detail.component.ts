import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ITrimsCreationMaster } from 'app/shared/model/trims-creation-master.model';

@Component({
  selector: 'jhi-trims-creation-master-detail',
  templateUrl: './trims-creation-master-detail.component.html'
})
export class TrimsCreationMasterDetailComponent implements OnInit {
  trimsCreationMaster: ITrimsCreationMaster;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ trimsCreationMaster }) => {
      this.trimsCreationMaster = trimsCreationMaster;
    });
  }

  previousState() {
    window.history.back();
  }
}
