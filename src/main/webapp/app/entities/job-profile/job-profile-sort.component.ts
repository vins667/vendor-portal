import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { IJobProfile } from 'app/shared/model/job-profile.model';
import { JobProfileService } from './job-profile.service';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { IMaster } from 'app/shared/model/master.modal';
import { Observable } from 'rxjs';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { IMessage } from 'app/shared/model/message.model';
import { toastConfig } from 'app/core/toast/toast-config';
import { SnotifyService } from 'ng-snotify';

@Component({
  selector: 'jhi-job-profile-sort',
  templateUrl: './job-profile-sort.component.html'
})
export class JobProfileSortComponent implements OnInit {
  jobProfiles: IJobProfile[];
  department: string;
  departments: IMaster[];

  constructor(
    protected activatedRoute: ActivatedRoute,
    protected jobProfileService: JobProfileService,
    public modal: NgbModal,
    private snotifyService: SnotifyService
  ) {}

  ngOnInit() {
    this.jobProfiles = [];
    this.jobProfileService.department().subscribe(departments => {
      this.departments = departments.body;
    });
  }

  getDesignations() {
    if (this.department !== undefined) {
      this.jobProfileService.find(Number(this.department)).subscribe(jobProfiles => {
        this.jobProfiles = jobProfiles.body;
        let ctr = -1;
        this.jobProfiles.forEach(jobProfilesd => {
          jobProfilesd.ordering = ++ctr;
        });
      });
    }
  }

  previousState() {
    window.history.back();
  }

  moveUp(value, index) {
    if (index > 0) {
      const tmp = this.jobProfiles[index - 1];
      this.jobProfiles[index - 1] = this.jobProfiles[index];
      this.jobProfiles[index] = tmp;

      let ctr = -1;
      this.jobProfiles.forEach(jobProfiles => {
        jobProfiles.ordering = ++ctr;
      });
    }
  }

  moveDown(value, index) {
    if (index < this.jobProfiles.length) {
      const tmp = this.jobProfiles[index + 1];
      this.jobProfiles[index + 1] = this.jobProfiles[index];
      this.jobProfiles[index] = tmp;

      let ctr = -1;
      this.jobProfiles.forEach(jobProfiles => {
        jobProfiles.ordering = ++ctr;
      });
    }
  }

  save() {
    this.subscribeToSaveResponse(this.jobProfileService.updateMultiple(this.jobProfiles));
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMessage>>) {
    result.subscribe((res: HttpResponse<IMessage>) => this.onSaveSuccess(res), (res: HttpErrorResponse) => this.onSaveError());
  }

  protected onSaveSuccess(result: HttpResponse<IMessage>) {
    this.modal.dismissAll();
    this.snotifyService.success('Save successfully!!!', '', toastConfig);
  }

  protected onSaveError() {}
}
