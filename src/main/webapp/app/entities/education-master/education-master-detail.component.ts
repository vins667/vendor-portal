import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { IEducationMaster } from 'app/shared/model/education-master.model';

@Component({
  selector: 'jhi-education-master-detail',
  templateUrl: './education-master-detail.component.html'
})
export class EducationMasterDetailComponent implements OnInit {
  educationMaster: IEducationMaster;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ educationMaster }) => {
      this.educationMaster = educationMaster;
    });
  }

  previousState() {
    window.history.back();
  }
}
