import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { IRecruitmentCountryMaster } from 'app/shared/model/recruitment-country-master.model';

@Component({
  selector: 'jhi-recruitment-country-master-detail',
  templateUrl: './recruitment-country-master-detail.component.html'
})
export class RecruitmentCountryMasterDetailComponent implements OnInit {
  recruitmentCountryMaster: IRecruitmentCountryMaster;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ recruitmentCountryMaster }) => {
      this.recruitmentCountryMaster = recruitmentCountryMaster;
    });
  }

  previousState() {
    window.history.back();
  }
}
