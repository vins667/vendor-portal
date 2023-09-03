import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { TdsDeclarationUpload } from 'app/shared/model/tds-declaration-upload.model';
import { JhiAlertService } from 'ng-jhipster';
import * as FileSaver from 'file-saver';
import { TdsDeclarationUploadDetailBean } from 'app/shared/model/tds-declaration-upload-detail-bean.model';
import { TdsYearMaster } from 'app/shared/model/tds-year-master.model';
import { TdsQueryService } from './tds-query.service';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'jhi-tds-declaration-upload-qry-update',
  templateUrl: './tds-declaration-upload-qry-update.component.html'
})
export class TdsDeclarationUploadQryUpdateComponent implements OnInit {
  @Input() tdsDeclarationUpload: TdsDeclarationUpload;
  @Input() tdsYearMaster: TdsYearMaster;
  constructor(
    protected tdsQueryService: TdsQueryService,
    protected route: ActivatedRoute,
    public activeModal: NgbActiveModal,
    protected jhiAlertService: JhiAlertService
  ) {}

  ngOnInit() {}

  close() {
    this.activeModal.dismiss('cancel');
  }

  download(tdsDeclarationUploadDetail: TdsDeclarationUploadDetailBean): any {
    this.tdsQueryService.download(tdsDeclarationUploadDetail.id).subscribe(
      res => {
        FileSaver.saveAs(res, `${tdsDeclarationUploadDetail.fileName}`);
      },
      res => {
        this.onError(res.message);
      }
    );
  }

  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }

  previousState() {
    window.history.back();
  }
}
