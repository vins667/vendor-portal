import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { IJobProfile } from 'app/shared/model/job-profile.model';

@Component({
  selector: 'jhi-induction-profile-detail',
  templateUrl: './induction-detail.component.html'
})
export class InductionDetailComponent implements OnInit {
  jobProfiles: IJobProfile[];
  page = 1;
  totalPages: number;
  isLoaded = false;

  constructor(protected activatedRoute: ActivatedRoute) {}

  afterLoadComplete(pdfData: any) {
    this.totalPages = pdfData.numPages;
    this.isLoaded = true;
  }

  nextPage() {
    this.page++;
  }

  prevPage() {
    this.page--;
  }

  changePage() {
    this.page = 1;
  }

  ngOnInit() {
    this.jobProfiles = [];
    this.activatedRoute.data.subscribe(({ jobProfiles }) => {
      this.jobProfiles = jobProfiles;
      this.jobProfiles.forEach(jobProfile => {
        jobProfile.url = jobProfile.filePath + jobProfile.fileName;
      });
    });
  }

  previousState() {
    window.history.back();
  }
}
