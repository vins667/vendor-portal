import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { IFabricSubstractMaster } from 'app/shared/model/fabric-substract-master.model';

@Component({
  selector: 'jhi-fabric-substract-master-detail',
  templateUrl: './fabric-substract-master-detail.component.html'
})
export class FabricSubstractMasterDetailComponent implements OnInit {
  fabricSubstractMaster: IFabricSubstractMaster;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ fabricSubstractMaster }) => {
      this.fabricSubstractMaster = fabricSubstractMaster;
    });
  }

  previousState() {
    window.history.back();
  }
}
