import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { IFabricCreationMaster } from 'app/shared/model/fabric-creation-master.model';

@Component({
  selector: 'jhi-fabric-creation-master-detail',
  templateUrl: './fabric-creation-master-detail.component.html'
})
export class FabricCreationMasterDetailComponent implements OnInit {
  fabricCreationMaster: IFabricCreationMaster;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ fabricCreationMaster }) => {
      this.fabricCreationMaster = fabricCreationMaster;
    });
  }

  previousState() {
    window.history.back();
  }
}
