import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { IRecruitmentDocumentMaster } from 'app/shared/model/recruitment-document-master.model';

@Component({
  selector: 'jhi-recruitment-document-master-detail',
  templateUrl: './recruitment-document-master-detail.component.html'
})
export class RecruitmentDocumentMasterDetailComponent implements OnInit {
  recruitmentDocumentMaster: IRecruitmentDocumentMaster;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ recruitmentDocumentMaster }) => {
      this.recruitmentDocumentMaster = recruitmentDocumentMaster;
    });
  }

  previousState() {
    window.history.back();
  }
}
