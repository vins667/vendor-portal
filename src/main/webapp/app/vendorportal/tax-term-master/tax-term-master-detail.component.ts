import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ITaxTermMaster } from 'app/shared/model/tax-term-master.model';

@Component({
  selector: 'jhi-tax-term-master-detail',
  templateUrl: './tax-term-master-detail.component.html'
})
export class TaxTermMasterDetailComponent implements OnInit {
  taxTermMaster: ITaxTermMaster;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ taxTermMaster }) => {
      this.taxTermMaster = taxTermMaster;
    });
  }

  previousState() {
    window.history.back();
  }
}
