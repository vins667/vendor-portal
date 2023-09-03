import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { IOperationMaster } from 'app/shared/model/operation-master.model';

@Component({
  selector: 'jhi-operation-master-detail',
  templateUrl: './operation-master-detail.component.html'
})
export class OperationMasterDetailComponent implements OnInit {
  operationMaster: IOperationMaster;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ operationMaster }) => {
      this.operationMaster = operationMaster;
    });
  }

  previousState() {
    window.history.back();
  }
}
