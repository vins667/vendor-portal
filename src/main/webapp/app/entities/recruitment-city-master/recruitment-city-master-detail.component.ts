import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { IRecruitmentCityMaster } from 'app/shared/model/recruitment-city-master.model';

@Component({
  selector: 'jhi-recruitment-city-master-detail',
  templateUrl: './recruitment-city-master-detail.component.html'
})
export class RecruitmentCityMasterDetailComponent implements OnInit {
  recruitmentCityMaster: IRecruitmentCityMaster;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ recruitmentCityMaster }) => {
      this.recruitmentCityMaster = recruitmentCityMaster;
    });
  }

  previousState() {
    window.history.back();
  }
}
