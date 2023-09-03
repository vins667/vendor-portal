import { Component, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { INewsDetails } from 'app/shared/model/news-details.model';
import { NewsDetailsApprovalService } from './news-details-approval.service';
import { Observable } from 'rxjs';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { SnotifyService } from 'ng-snotify';
import { INewsDetailsAttach } from 'app/shared/model/news-details-attach.model';
import * as FileSaver from 'file-saver';
import { JhiAlertService } from 'ng-jhipster';
import { toastConfig } from 'app/core/toast/toast-config';

@Component({
  selector: 'jhi-news-details-approval-detail',
  templateUrl: './news-details-approval-detail.component.html'
})
export class NewsDetailsApprovalDetailComponent implements OnInit {
  newsDetails: INewsDetails;
  ckeditorContent: string;
  isSaving: boolean;

  @ViewChild('myckeditor', { static: false }) ckeditor: any;

  constructor(
    protected activatedRoute: ActivatedRoute,
    protected newsDetailsService: NewsDetailsApprovalService,
    protected snotifyService: SnotifyService,
    protected jhiAlertService: JhiAlertService
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ newsDetails }) => {
      this.newsDetails = newsDetails;
      this.ckeditorContent = '';
      this.newsDetails.newsDetailsBodies.forEach(newsDetailsBody => {
        this.ckeditorContent += newsDetailsBody.newsBody;
      });
    });
  }

  approvalNews() {
    this.isSaving = true;
    this.subscribeToSaveResponse(this.newsDetailsService.approve(this.newsDetails.id));
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<INewsDetails>>) {
    result.subscribe((res: HttpResponse<INewsDetails>) => this.onSaveSuccess(res), (res: HttpErrorResponse) => this.onSaveError());
  }

  protected onSaveSuccess(result: HttpResponse<INewsDetails>) {
    this.isSaving = false;
    this.newsDetails = result.body;
    this.snotifyService.success('Approve successfully!!!', '', toastConfig);
    // this.previousState();
  }

  download(newsDetailsAttach: INewsDetailsAttach): any {
    this.newsDetailsService.download(newsDetailsAttach.id).subscribe(
      res => {
        FileSaver.saveAs(res, `${newsDetailsAttach.attachDisplayFile}`);
      },
      res => {
        this.onError(res.message);
      }
    );
  }

  protected onSaveError() {
    this.isSaving = false;
  }

  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }

  previousState() {
    window.history.back();
  }
}
