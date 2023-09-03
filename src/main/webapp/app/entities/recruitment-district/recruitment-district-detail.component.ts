import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { IRecruitmentDistrict } from 'app/shared/model/recruitment-district.model';

@Component({
  selector: 'jhi-recruitment-district-detail',
  templateUrl: './recruitment-district-detail.component.html'
})
export class RecruitmentDistrictDetailComponent implements OnInit {
  recruitmentDistrict: IRecruitmentDistrict;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ recruitmentDistrict }) => {
      this.recruitmentDistrict = recruitmentDistrict;
    });
  }

  previousState() {
    window.history.back();
  }
}
