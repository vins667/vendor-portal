import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { IEducationTypeMaster } from 'app/shared/model/education-type-master.model';

@Component({
  selector: 'jhi-education-type-master-detail',
  templateUrl: './education-type-master-detail.component.html'
})
export class EducationTypeMasterDetailComponent implements OnInit {
  educationTypeMaster: IEducationTypeMaster;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ educationTypeMaster }) => {
      this.educationTypeMaster = educationTypeMaster;
    });
  }

  previousState() {
    window.history.back();
  }
}
