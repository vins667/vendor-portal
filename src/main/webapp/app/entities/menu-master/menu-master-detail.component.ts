import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { IMenuMaster } from 'app/shared/model/menu-master.model';

@Component({
  selector: 'jhi-menu-master-detail',
  templateUrl: './menu-master-detail.component.html'
})
export class MenuMasterDetailComponent implements OnInit {
  menuMaster: IMenuMaster;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ menuMaster }) => {
      this.menuMaster = menuMaster;
    });
  }

  previousState() {
    window.history.back();
  }
}
