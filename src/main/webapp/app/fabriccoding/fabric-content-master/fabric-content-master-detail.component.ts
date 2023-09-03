import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { IFabricContentMaster } from 'app/shared/model/fabric-content-master.model';

@Component({
  selector: 'jhi-fabric-content-master-detail',
  templateUrl: './fabric-content-master-detail.component.html'
})
export class FabricContentMasterDetailComponent implements OnInit {
  fabricContentMaster: IFabricContentMaster;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ fabricContentMaster }) => {
      this.fabricContentMaster = fabricContentMaster;
    });
  }

  previousState() {
    window.history.back();
  }
}
