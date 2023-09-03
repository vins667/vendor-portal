import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { IFabricOthersMaster } from 'app/shared/model/fabric-others-master.model';

@Component({
  selector: 'jhi-fabric-others-master-detail',
  templateUrl: './fabric-others-master-detail.component.html'
})
export class FabricOthersMasterDetailComponent implements OnInit {
  fabricOthersMaster: IFabricOthersMaster;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ fabricOthersMaster }) => {
      this.fabricOthersMaster = fabricOthersMaster;
    });
  }

  previousState() {
    window.history.back();
  }
}
