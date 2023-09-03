import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ITermsConditionMaster } from 'app/shared/model/terms-condition-master.model';

@Component({
  selector: 'jhi-terms-condition-master-detail',
  templateUrl: './terms-condition-master-detail.component.html'
})
export class TermsConditionMasterDetailComponent implements OnInit {
  termsConditionMaster: ITermsConditionMaster;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ termsConditionMaster }) => {
      this.termsConditionMaster = termsConditionMaster;
    });
  }

  previousState() {
    window.history.back();
  }
}
