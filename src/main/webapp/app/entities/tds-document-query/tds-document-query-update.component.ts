import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { TdsDocumentQueryService } from './tds-document-query.service';
import { TdsDeclarationUpload } from 'app/shared/model/tds-declaration-upload.model';
import { JhiAlertService } from 'ng-jhipster';
import * as FileSaver from 'file-saver';
import { TdsDeclarationUploadDetailBean } from 'app/shared/model/tds-declaration-upload-detail-bean.model';
import { ITdsYearMaster } from 'app/shared/model/tds-year-master.model';
import { TdsYearMasterService } from 'app/entities/tds-year-master/tds-year-master.service';
import { Observable } from 'rxjs';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { SnotifyService } from 'ng-snotify';
import { toastConfig } from 'app/core/toast/toast-config';

@Component({
  selector: 'jhi-tds-document-query-update',
  templateUrl: './tds-document-query-update.component.html'
})
export class TdsDocumentQueryUpdateComponent implements OnInit {
  tdsDeclarationUpload: TdsDeclarationUpload;
  tdsYearMaster: ITdsYearMaster;
  isDownload = false;
  constructor(
    protected tdsDocumentQueryService: TdsDocumentQueryService,
    protected tdsYearMasterService: TdsYearMasterService,
    protected route: ActivatedRoute,
    protected jhiAlertService: JhiAlertService,
    protected snotifyService: SnotifyService
  ) {}

  ngOnInit() {
    this.route.data.subscribe(({ tdsDeclarationUpload }) => {
      this.tdsDeclarationUpload = tdsDeclarationUpload;
      if (this.tdsDeclarationUpload && this.tdsDeclarationUpload.financialYear) {
        this.tdsYearMasterService.findByYear(Number(this.tdsDeclarationUpload.financialYear)).subscribe(finYear => {
          this.tdsYearMaster = finYear.body;
        });
      }
    });
  }

  download(tdsDeclarationUploadDetail: TdsDeclarationUploadDetailBean): any {
    this.isDownload = true;
    this.tdsDocumentQueryService.downloadById(tdsDeclarationUploadDetail.id).subscribe(
      res => {
        FileSaver.saveAs(res, `${tdsDeclarationUploadDetail.fileName}`);
        this.isDownload = false;
      },
      res => {
        this.onError(res.message);
        this.isDownload = false;
      }
    );
  }

  save(): void {
    this.isDownload = true;
    this.subscribeToSaveResponse(this.tdsDocumentQueryService.save(this.tdsDeclarationUpload));
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<TdsDeclarationUpload>>) {
    result.subscribe((res: HttpResponse<TdsDeclarationUpload>) => this.onSaveSuccess(res), (res: HttpErrorResponse) => this.onSaveError());
  }

  protected onSaveSuccess(res: HttpResponse<TdsDeclarationUpload>) {
    this.tdsDeclarationUpload = res.body;
    this.snotifyService.success('Save Successfully', '', toastConfig);
    this.isDownload = false;
  }

  protected onSaveError() {
    this.isDownload = false;
  }

  clickValidate(tdsDeclarationUploadDetail: TdsDeclarationUploadDetailBean, type: string): void {
    if (type === 'accept') {
      if (tdsDeclarationUploadDetail.accept) {
        if (tdsDeclarationUploadDetail.reject) {
          tdsDeclarationUploadDetail.reject = false;
        }
      }
    } else {
      if (tdsDeclarationUploadDetail.reject) {
        if (tdsDeclarationUploadDetail.accept) {
          tdsDeclarationUploadDetail.accept = false;
        }
      }
    }
  }

  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }

  previousState() {
    window.history.back();
  }
}
