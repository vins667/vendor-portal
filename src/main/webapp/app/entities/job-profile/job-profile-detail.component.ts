import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { IJobProfile } from 'app/shared/model/job-profile.model';
import { IJobProfileGroup, JobProfileGroup } from 'app/shared/model/job-profile-group.model';

@Component({
  selector: 'jhi-job-profile-detail',
  templateUrl: './job-profile-detail.component.html'
})
export class JobProfileDetailComponent implements OnInit {
  jobProfiles: IJobProfile[];
  page = 1;
  totalPages: number;
  isLoaded = false;
  jobProfilesGroup: IJobProfileGroup[];

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
    this.jobProfilesGroup = [];
    this.activatedRoute.data.subscribe(({ jobProfiles }) => {
      this.jobProfiles = jobProfiles;
      const jobProfilesGroup = new Set();
      this.jobProfiles.forEach(jobProfile => {
        jobProfilesGroup.add(jobProfile.departmentDesc);
      });
      jobProfilesGroup.forEach(value => {
        const profileGroup = new JobProfileGroup();
        profileGroup.department = value;
        const profiles = [];
        this.jobProfiles.forEach(jobProfile => {
          if (jobProfile.departmentDesc === value) {
            profiles.push(jobProfile);
          }
        });
        profileGroup.jobProfiles = [];
        profileGroup.jobProfiles = profiles;
        this.jobProfilesGroup.push(profileGroup);
      });
    });
  }

  previousState() {
    window.history.back();
  }
}
