import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { IPayTermMaster } from 'app/shared/model/pay-term-master.model';

@Component({
  selector: 'jhi-pay-term-master-detail',
  templateUrl: './pay-term-master-detail.component.html'
})
export class PayTermMasterDetailComponent implements OnInit {
  payTermMaster: IPayTermMaster;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ payTermMaster }) => {
      this.payTermMaster = payTermMaster;
    });
  }

  previousState() {
    window.history.back();
  }
}
