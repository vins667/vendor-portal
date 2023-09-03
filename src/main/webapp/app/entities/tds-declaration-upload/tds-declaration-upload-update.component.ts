import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { JhiAlertService, JhiEventManager } from 'ng-jhipster';
import { TdsDeclarationUpload, ITdsDeclarationUpload } from 'app/shared/model/tds-declaration-upload.model';
import { TdsDeclarationUploadService } from './tds-declaration-upload.service';
import { TdsGroupDetailsService } from 'app/entities/tds-group-details';
import { TdsDeclarationService } from '../tds-declaration/tds-declaration.service';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { TdsDeclarationUploadPopupComponent } from './tds-declaration-upload-popup.component';
import { ITdsYearMaster } from 'app/shared/model/tds-year-master.model';
import { TdsGroupMaster } from 'app/shared/model/tds-group-master.model';
import * as FileSaver from 'file-saver';
import { SnotifyService, SnotifyPosition } from 'ng-snotify';
import { TdsDeclarationUploadDetailBean } from 'app/shared/model/tds-declaration-upload-detail-bean.model';

@Component({
  selector: 'jhi-tds-declaration-upload-update',
  templateUrl: './tds-declaration-upload-update.component.html'
})
export class TdsDeclarationUploadUpdateComponent implements OnInit {
  tdsDeclarationUpload: TdsDeclarationUpload;
  protected ngbModalRef: NgbModalRef;
  tdsYearMaster: ITdsYearMaster;
  currentFileUpload: File[] = [];
  constructor(
    protected jhiAlertService: JhiAlertService,
    protected tdsDeclarationService: TdsDeclarationService,
    protected tdsDeclarationUploadService: TdsDeclarationUploadService,
    protected tdsGroupDetailsService: TdsGroupDetailsService,
    protected activatedRoute: ActivatedRoute,
    protected modalService: NgbModal,
    protected snotifyService: SnotifyService,
    private eventManager: JhiEventManager
  ) {}

  ngOnInit() {
    this.registerChangeInAssetAuditDetails();
    this.tdsDeclarationUploadService.customQuery().subscribe((res: HttpResponse<ITdsDeclarationUpload>) => {
      this.tdsDeclarationUpload = res.body;
    });
    this.tdsDeclarationUploadService.active().subscribe(tdsYearMaster => {
      this.tdsYearMaster = tdsYearMaster.body;
    });
  }

  download(tdsDeclarationUploadDetail: TdsDeclarationUploadDetailBean): any {
    this.tdsDeclarationUploadService.download(tdsDeclarationUploadDetail.id).subscribe(
      res => {
        FileSaver.saveAs(res, `${tdsDeclarationUploadDetail.fileName}`);
      },
      res => {
        this.onError(res.message);
      }
    );
  }

  deleteRow(id, index) {
    this.snotifyService.confirm('Are you sure to delete file?', 'Confirm', {
      timeout: 25000,
      showProgressBar: false,
      closeOnClick: false,
      pauseOnHover: true,
      position: SnotifyPosition.centerTop,
      buttons: [
        { text: 'Yes', action: toast => this.delete(toast, id, index), bold: false },
        { text: 'No', action: toast => this.snotifyService.remove(toast.id) }
      ]
    });
  }

  delete(toast, id, index) {
    this.tdsDeclarationUploadService.delete(id).subscribe(any => {
      this.snotifyService.remove(toast.id);
      this.tdsDeclarationUpload.tdsGroupMasterbean[index].tdsDeclarationUploadDetailBean.splice(index, 1);
    });
    this.ngOnInit();
  }

  getUploadPopup(groupMaster: TdsGroupMaster) {
    this.ngbModalRef = this.modalService.open(TdsDeclarationUploadPopupComponent as Component, {
      size: 'lg',
      backdrop: 'static',
      windowClass: 'mediumModal'
    });
    this.tdsDeclarationUpload.tdsGroupMaster = groupMaster;
    this.ngbModalRef.componentInstance.tdsDeclarationUpload = this.tdsDeclarationUpload;
  }

  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }

  registerChangeInAssetAuditDetails() {
    this.eventManager.subscribe('selectedtdsDeclarationUpload', message => {
      this.ngOnInit();
    });
  }
}
