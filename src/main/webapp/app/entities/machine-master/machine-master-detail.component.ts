import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { IMachineMaster } from 'app/shared/model/machine-master.model';

@Component({
  selector: 'jhi-machine-master-detail',
  templateUrl: './machine-master-detail.component.html'
})
export class MachineMasterDetailComponent implements OnInit {
  machineMaster: IMachineMaster;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ machineMaster }) => {
      this.machineMaster = machineMaster;
    });
  }

  previousState() {
    window.history.back();
  }
}
