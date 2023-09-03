import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { IFabricSubstractDetails } from 'app/shared/model/fabric-substract-details.model';

@Component({
  selector: 'jhi-fabric-substract-details-detail',
  templateUrl: './fabric-substract-details-detail.component.html'
})
export class FabricSubstractDetailsDetailComponent implements OnInit {
  fabricSubstractDetails: IFabricSubstractDetails;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ fabricSubstractDetails }) => {
      this.fabricSubstractDetails = fabricSubstractDetails;
    });
  }

  previousState() {
    window.history.back();
  }
}
