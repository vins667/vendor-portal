import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { IRecruitmentStateMaster } from 'app/shared/model/recruitment-state-master.model';

@Component({
  selector: 'jhi-recruitment-state-master-detail',
  templateUrl: './recruitment-state-master-detail.component.html'
})
export class RecruitmentStateMasterDetailComponent implements OnInit {
  recruitmentStateMaster: IRecruitmentStateMaster;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ recruitmentStateMaster }) => {
      this.recruitmentStateMaster = recruitmentStateMaster;
    });
  }

  previousState() {
    window.history.back();
  }
}
