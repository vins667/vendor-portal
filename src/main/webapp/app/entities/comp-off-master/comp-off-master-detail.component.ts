import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ICompOffMaster } from 'app/shared/model/comp-off-master.model';

@Component({
  selector: 'jhi-comp-off-master-detail',
  templateUrl: './comp-off-master-detail.component.html'
})
export class CompOffMasterDetailComponent implements OnInit {
  compOffMaster: ICompOffMaster;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ compOffMaster }) => {
      this.compOffMaster = compOffMaster;
    });
  }

  previousState() {
    window.history.back();
  }
}
