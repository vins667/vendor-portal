import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { IFabricSplFinishMaster } from 'app/shared/model/fabric-spl-finish-master.model';

@Component({
  selector: 'jhi-fabric-spl-finish-master-detail',
  templateUrl: './fabric-spl-finish-master-detail.component.html'
})
export class FabricSplFinishMasterDetailComponent implements OnInit {
  fabricSplFinishMaster: IFabricSplFinishMaster;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ fabricSplFinishMaster }) => {
      this.fabricSplFinishMaster = fabricSplFinishMaster;
    });
  }

  previousState() {
    window.history.back();
  }
}
