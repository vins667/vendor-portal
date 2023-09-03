import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { IRelationMaster } from 'app/shared/model/relation-master.model';

@Component({
  selector: 'jhi-relation-master-detail',
  templateUrl: './relation-master-detail.component.html'
})
export class RelationMasterDetailComponent implements OnInit {
  relationMaster: IRelationMaster;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ relationMaster }) => {
      this.relationMaster = relationMaster;
    });
  }

  previousState() {
    window.history.back();
  }
}
