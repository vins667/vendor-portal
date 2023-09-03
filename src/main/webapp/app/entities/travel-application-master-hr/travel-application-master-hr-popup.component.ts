import { Component, OnInit, OnDestroy } from '@angular/core';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { ITravelMasterAttach, TravelMasterAttach } from 'app/shared/model/travel-master-attach.model';
import { TravelApplicationMasterHrService } from './travel-application-master-hr.service';
import { JhiAlertService, JhiEventManager } from 'ng-jhipster';
import { ActivatedRoute } from '@angular/router';
import { SnotifyService } from 'ng-snotify';
import { ITravelApplicationMaster } from 'app/shared/model/travel-application-master.model';
import { Observable } from 'rxjs';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { toastConfig } from 'app/core/toast/toast-config';
import * as FileSaver from 'file-saver';

@Component({
  selector: 'jhi-travel-application-master-hr-popup',
  templateUrl: './travel-application-master-hr-popup.component.html'
})
export class TravelApplicationMasterHrPopupComponent implements OnDestroy {
  protected ngbModalRef: NgbModalRef;
  travelApplicationMaster: ITravelApplicationMaster;
  travelMasterAttaches: ITravelMasterAttach[];
  attchType: string;
  attachFile: any;
  selectedFile: FileList;
  currentFileUpload: File;
  isSaving = false;
  isDownloading = false;

  constructor(
    protected travelApplicationMasterHrService: TravelApplicationMasterHrService,
    protected jhiAlertService: JhiAlertService,
    protected activatedRoute: ActivatedRoute,
    protected snotifyService: SnotifyService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  ngOnDestroy(): void {
    this.ngbModalRef = null;
  }

  close() {
    this.activeModal.dismiss('cancel');
  }

  selectFile(event) {
    this.selectedFile = event.target.files;
  }

  uploadDocument() {
    if (this.attchType && this.attchType.length > 0 && this.selectedFile != null) {
      this.isSaving = true;
      this.currentFileUpload = this.selectedFile.item(0);
      this.subscribeToSaveResponse(
        this.travelApplicationMasterHrService.createUpload(this.currentFileUpload, this.attchType, this.travelApplicationMaster.id)
      );
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITravelMasterAttach[]>>) {
    result.subscribe((res: HttpResponse<ITravelMasterAttach[]>) => this.onSaveSuccess(res), (res: HttpErrorResponse) => this.onSaveError());
  }

  protected onSaveSuccess(result: HttpResponse<ITravelMasterAttach[]>) {
    this.travelMasterAttaches = result.body;
    this.isSaving = false;
    this.attchType = undefined;
    this.attachFile = undefined;
    this.selectedFile = undefined;
    this.snotifyService.success('Save successfully!!!', '', toastConfig);
  }

  protected onSaveError() {
    this.isSaving = false;
  }

  downloadFile(travelAttach: ITravelMasterAttach) {
    if (travelAttach.id) {
      this.isDownloading = true;
      this.travelApplicationMasterHrService.download(travelAttach.id).subscribe(
        res => {
          FileSaver.saveAs(res, travelAttach.attachFile);
          this.isDownloading = false;
        },
        res => {
          this.isDownloading = false;
        }
      );
    }
  }
}
