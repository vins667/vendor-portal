import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IDeliveryTermMaster } from 'app/shared/model/delivery-term-master.model';

@Component({
  selector: 'jhi-delivery-term-master-detail',
  templateUrl: './delivery-term-master-detail.component.html'
})
export class DeliveryTermMasterDetailComponent implements OnInit {
  deliveryTermMaster: IDeliveryTermMaster;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ deliveryTermMaster }) => {
      this.deliveryTermMaster = deliveryTermMaster;
    });
  }

  previousState() {
    window.history.back();
  }
}
