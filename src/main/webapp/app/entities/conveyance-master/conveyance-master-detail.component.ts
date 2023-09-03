import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { IConveyanceMaster } from 'app/shared/model/conveyance-master.model';

@Component({
  selector: 'jhi-conveyance-master-detail',
  templateUrl: './conveyance-master-detail.component.html'
})
export class ConveyanceMasterDetailComponent implements OnInit {
  conveyanceMaster: IConveyanceMaster;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ conveyanceMaster }) => {
      this.conveyanceMaster = conveyanceMaster;
    });
  }

  previousState() {
    window.history.back();
  }
}
