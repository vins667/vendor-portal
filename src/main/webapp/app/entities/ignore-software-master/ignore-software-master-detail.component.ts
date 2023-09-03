import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { IIgnoreSoftwareMaster } from 'app/shared/model/ignore-software-master.model';

@Component({
  selector: 'jhi-ignore-software-master-detail',
  templateUrl: './ignore-software-master-detail.component.html'
})
export class IgnoreSoftwareMasterDetailComponent implements OnInit {
  ignoreSoftwareMaster: IIgnoreSoftwareMaster;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ ignoreSoftwareMaster }) => {
      this.ignoreSoftwareMaster = ignoreSoftwareMaster;
    });
  }

  previousState() {
    window.history.back();
  }
}
