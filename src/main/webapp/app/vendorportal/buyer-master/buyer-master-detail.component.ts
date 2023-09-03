import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { IBuyerMaster } from 'app/shared/model/buyer-master.model';

@Component({
  selector: 'jhi-buyer-master-detail',
  templateUrl: './buyer-master-detail.component.html'
})
export class BuyerMasterDetailComponent implements OnInit {
  buyerMaster: IBuyerMaster;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ buyerMaster }) => {
      this.buyerMaster = buyerMaster;
    });
  }

  previousState() {
    window.history.back();
  }
}
