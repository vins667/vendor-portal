import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { IQuotationMaster } from 'app/shared/model/quotation-master.model';

@Component({
  selector: 'jhi-quotation-master-detail',
  templateUrl: './quotation-master-detail.component.html'
})
export class QuotationMasterDetailComponent implements OnInit {
  quotationMaster: IQuotationMaster;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ quotationMaster }) => {
      this.quotationMaster = quotationMaster;
    });
  }

  previousState() {
    window.history.back();
  }
}
