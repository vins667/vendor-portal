import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ICategoryMaster } from 'app/shared/model/category-master.model';

@Component({
  selector: 'jhi-category-master-detail',
  templateUrl: './category-master-detail.component.html'
})
export class CategoryMasterDetailComponent implements OnInit {
  categoryMaster: ICategoryMaster;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ categoryMaster }) => {
      this.categoryMaster = categoryMaster;
    });
  }

  previousState() {
    window.history.back();
  }
}
