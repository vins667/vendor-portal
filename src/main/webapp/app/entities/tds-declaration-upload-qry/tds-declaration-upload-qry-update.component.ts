import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { TdsDeclarationUploadQryService } from './tds-declaration-upload-qry.service';
import { TdsDeclarationUploadSearch } from 'app/shared/model/tds-declaration-upload-search.model';
import { TdsDeclarationUpload } from 'app/shared/model/tds-declaration-upload.model';
import { JhiAlertService } from 'ng-jhipster';
import * as FileSaver from 'file-saver';
import { TdsDeclarationUploadDetailBean } from 'app/shared/model/tds-declaration-upload-detail-bean.model';
import { ITdsYearMaster } from 'app/shared/model/tds-year-master.model';

@Component({
  selector: 'jhi-tds-declaration-upload-qry-update',
  templateUrl: './tds-declaration-upload-qry-update.component.html'
})
export class TdsDeclarationUploadQryUpdateComponent implements OnInit {
  tdsDeclarationUpload: TdsDeclarationUpload;
  tdsYearMaster: ITdsYearMaster;
  constructor(
    protected tdsDeclarationUploadQryService: TdsDeclarationUploadQryService,
    protected route: ActivatedRoute,
    protected jhiAlertService: JhiAlertService
  ) {}

  ngOnInit() {
    this.route.params.subscribe(params => {
      const cardNo = params['cardNo'] ? params['cardNo'] : null;
      const year = params['financialYear'] ? params['financialYear'] : null;
      const tdsDeclarationUploadSearch = new TdsDeclarationUploadSearch();
      tdsDeclarationUploadSearch.cardNo = cardNo;
      tdsDeclarationUploadSearch.financialYear = year;
      if (cardNo && year) {
        /* this.tdsDeclarationUploadQryService.edit(tdsDeclarationUploadSearch).subscribe(tdsDeclarationUploads => {
          this.tdsDeclarationUpload = tdsDeclarationUploads.body;
        }); */
      }
    });
    this.tdsDeclarationUploadQryService.active().subscribe(tdsYearMaster => {
      this.tdsYearMaster = tdsYearMaster.body;
    });
  }

  download(tdsDeclarationUploadDetail: TdsDeclarationUploadDetailBean): any {
    /*this.tdsDeclarationUploadQryService.download(tdsDeclarationUploadDetail.id).subscribe(
      res => {
        FileSaver.saveAs(res, `${tdsDeclarationUploadDetail.fileName}`);
      },
      res => {
        this.onError(res.message);
      }
    );*/
  }

  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }

  previousState() {
    window.history.back();
  }
}
