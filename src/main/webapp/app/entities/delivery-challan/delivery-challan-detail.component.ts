import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { IDeliveryChallan } from 'app/shared/model/delivery-challan.model';

@Component({
  selector: 'jhi-delivery-challan-detail',
  templateUrl: './delivery-challan-detail.component.html'
})
export class DeliveryChallanDetailComponent implements OnInit {
  deliveryChallan: IDeliveryChallan;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ deliveryChallan }) => {
      this.deliveryChallan = deliveryChallan;
    });
  }

  previousState() {
    window.history.back();
  }
}
