import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { IBankMaster } from 'app/shared/model/bank-master.model';

@Component({
  selector: 'jhi-bank-master-detail',
  templateUrl: './bank-master-detail.component.html'
})
export class BankMasterDetailComponent implements OnInit {
  bankMaster: IBankMaster;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ bankMaster }) => {
      this.bankMaster = bankMaster;
    });
  }

  previousState() {
    window.history.back();
  }
}
