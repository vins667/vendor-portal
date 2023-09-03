import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ITrimsTemplateMaster } from 'app/shared/model/trims-template-master.model';

@Component({
  selector: 'jhi-trims-template-master-detail',
  templateUrl: './trims-template-master-detail.component.html'
})
export class TrimsTemplateMasterDetailComponent implements OnInit {
  trimsTemplateMaster: ITrimsTemplateMaster;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ trimsTemplateMaster }) => {
      this.trimsTemplateMaster = trimsTemplateMaster;
    });
  }

  previousState() {
    window.history.back();
  }
}
