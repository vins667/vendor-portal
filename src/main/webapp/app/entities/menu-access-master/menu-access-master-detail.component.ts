import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { IMenuAccessMaster } from 'app/shared/model/menu-access-master.model';

@Component({
  selector: 'jhi-menu-access-master-detail',
  templateUrl: './menu-access-master-detail.component.html'
})
export class MenuAccessMasterDetailComponent implements OnInit {
  menuAccessMaster: IMenuAccessMaster;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ menuAccessMaster }) => {
      this.menuAccessMaster = menuAccessMaster;
    });
  }

  previousState() {
    window.history.back();
  }
}
