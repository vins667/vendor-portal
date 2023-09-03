import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { IMMRMaster } from 'app/shared/model/mmr-master.model';

@Component({
  selector: 'jhi-mmr-master-detail',
  templateUrl: './mmr-master-detail.component.html'
})
export class MMRMasterDetailComponent implements OnInit {
  mMRMaster: IMMRMaster;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mMRMaster }) => {
      this.mMRMaster = mMRMaster;
    });
  }

  previousState() {
    window.history.back();
  }
}
