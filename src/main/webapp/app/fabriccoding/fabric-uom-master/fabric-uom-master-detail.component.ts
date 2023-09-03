import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { IFabricUomMaster } from 'app/shared/model/fabric-uom-master.model';

@Component({
  selector: 'jhi-fabric-uom-master-detail',
  templateUrl: './fabric-uom-master-detail.component.html'
})
export class FabricUomMasterDetailComponent implements OnInit {
  fabricUomMaster: IFabricUomMaster;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ fabricUomMaster }) => {
      this.fabricUomMaster = fabricUomMaster;
    });
  }

  previousState() {
    window.history.back();
  }
}
