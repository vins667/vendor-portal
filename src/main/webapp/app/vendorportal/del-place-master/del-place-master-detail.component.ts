import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { IDelPlaceMaster } from 'app/shared/model/del-place-master.model';

@Component({
  selector: 'jhi-del-place-master-detail',
  templateUrl: './del-place-master-detail.component.html'
})
export class DelPlaceMasterDetailComponent implements OnInit {
  delPlaceMaster: IDelPlaceMaster;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ delPlaceMaster }) => {
      this.delPlaceMaster = delPlaceMaster;
    });
  }

  previousState() {
    window.history.back();
  }
}
