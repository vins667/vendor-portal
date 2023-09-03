import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { IAssetDocumentMaster } from 'app/shared/model/asset-document-master.model';
import { AssetDocumentMasterService } from 'app/entities/asset-document-master';
import { SnotifyPosition, SnotifyService } from 'ng-snotify';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { IAssetFileUploadBean } from 'app/shared/model/asset-file-upload-bean.model';
import { JhiAlertService } from 'ng-jhipster';
import { AssetFileUploadMaster, IAssetFileUploadMaster } from 'app/shared/model/asset-file-upload-master.model';
import { toastConfig } from 'app/core/toast/toast-config';
import { Observable } from 'rxjs';
import { AssetMasterService } from './asset-master.service';
import * as FileSaver from 'file-saver';
import { AssetFileUploadDetails, IAssetFileUploadDetails } from 'app/shared/model/asset-file-upload-details.model';

@Component({
  selector: 'jhi-asset-file-upload-details',
  templateUrl: './asset-file-upload-details.component.html'
})
export class AssetFileUploadDetailsComponent implements OnInit {
  assetFileUploadBean: IAssetFileUploadBean;
  selectedFile: FileList;
  currentFileUpload: File;

  assetdocumentmasters: IAssetDocumentMaster[];
  assetFileUploadMaster: IAssetFileUploadMaster;

  constructor(
    public activeModal: NgbActiveModal,
    protected assetDocumentMasterService: AssetDocumentMasterService,
    protected assetMasterService: AssetMasterService,
    protected jhiAlertService: JhiAlertService,
    private snotifyService: SnotifyService
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  ngOnInit(): void {
    this.assetFileUploadMaster = new AssetFileUploadMaster();
    this.assetFileUploadMaster.assetSupplierMaster = this.assetFileUploadBean.assetMaster.assetSupplierMaster;
    this.assetFileUploadMaster.invoiceNumber = this.assetFileUploadBean.assetMaster.invoiceNumber;
    this.assetDocumentMasterService.query().subscribe(
      (res: HttpResponse<IAssetDocumentMaster[]>) => {
        this.assetdocumentmasters = res.body;
      },
      (res: HttpErrorResponse) => this.onError(res.message)
    );
  }

  selectFile(event) {
    this.selectedFile = event.target.files;
  }

  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }

  saveUpload() {
    if (this.selectedFile) {
      this.currentFileUpload = this.selectedFile.item(0);
      this.subscribeToSaveResponse(
        this.assetMasterService.createUpload(
          this.currentFileUpload,
          this.assetFileUploadMaster.invoiceNumber,
          this.assetFileUploadMaster.assetSupplierMaster.id,
          this.assetFileUploadMaster.assetDocumentMaster.id,
          this.assetFileUploadBean.assetMaster.id
        )
      );
    } else {
      this.snotifyService.error('Please choose file!!!', '', toastConfig);
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IAssetFileUploadBean>>) {
    result.subscribe((res: HttpResponse<IAssetFileUploadBean>) => this.onSaveSuccess(res), (res: HttpErrorResponse) => this.onSaveError());
  }

  protected onSaveSuccess(result: HttpResponse<IAssetFileUploadBean>) {
    this.assetFileUploadBean = result.body;
    this.snotifyService.success('Save successfully!!!', '', toastConfig);
  }

  download(assetFileUploadMaster: IAssetFileUploadMaster): any {
    this.assetMasterService.download(assetFileUploadMaster.id).subscribe(
      res => {
        FileSaver.saveAs(res, `${assetFileUploadMaster.displayFileName}`);
      },
      res => {
        this.onError(res.message);
      }
    );
  }

  delete(assetFileUploadDetail?: IAssetFileUploadDetails) {
    this.snotifyService.confirm('Are you sure to delete?', 'Confirm', {
      timeout: 25000,
      showProgressBar: false,
      closeOnClick: false,
      pauseOnHover: true,
      position: SnotifyPosition.centerTop,
      buttons: [
        { text: 'Yes', action: toast => this.assetDetail(toast, assetFileUploadDetail), bold: false },
        { text: 'No', action: toast => this.snotifyService.remove(toast.id) }
      ]
    });
  }

  assetDetail(toast, assetFileUploadDetail?: IAssetFileUploadDetails) {
    this.assetMasterService.deleteDetails(assetFileUploadDetail.id).subscribe(response => {
      this.snotifyService.remove(toast.id);
      this.assetFileUploadBean = response.body;
    });
  }

  applyAttach(assetFileUploadMaster: IAssetFileUploadMaster) {
    const assetFileUploadDetails = new AssetFileUploadDetails();
    assetFileUploadDetails.assetFileUploadMaster = assetFileUploadMaster;
    assetFileUploadDetails.assetMaster = this.assetFileUploadBean.assetMaster;
    this.subscribeToSaveResponse(this.assetMasterService.apply(assetFileUploadDetails));
  }

  protected onSaveError() {}
}
