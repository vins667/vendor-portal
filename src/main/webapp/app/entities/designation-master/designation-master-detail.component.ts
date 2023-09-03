import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { IDesignationMaster } from 'app/shared/model/designation-master.model';

@Component({
  selector: 'jhi-designation-master-detail',
  templateUrl: './designation-master-detail.component.html'
})
export class DesignationMasterDetailComponent implements OnInit {
  designationMaster: IDesignationMaster;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ designationMaster }) => {
      this.designationMaster = designationMaster;
    });
  }

  previousState() {
    window.history.back();
  }
}
